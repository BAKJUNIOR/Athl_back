package athl.logistics.athl_logistics.web.resource.share;

import athl.logistics.athl_logistics.service.NewsArticleService;
import athl.logistics.athl_logistics.service.ProjectService;
import athl.logistics.athl_logistics.service.dto.NewsArticleDTO;
import athl.logistics.athl_logistics.service.dto.ProjectDTO;
import athl.logistics.athl_logistics.web.errors.AccountResourceException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import java.nio.charset.StandardCharsets;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/share")
@RequiredArgsConstructor
public class ShareController {

    private static final String SITE_NAME = "ATHL - Africa Talent Habitat & Logistique";
    private static final String DEFAULT_DESCRIPTION = "ATHL — Africa Talent Habitat & Logistique : construction, "
            + "rénovation, VTC & livraison, importation de matériaux. Abidjan, Côte d'Ivoire.";
    private static final String DEFAULT_IMAGE_PATH = "/images/og-default.jpg";
    private static final int DESCRIPTION_MAX_LENGTH = 200;
    private static final String CLOUDINARY_UPLOAD = "/image/upload/";
    // Format recommandé par Facebook/LinkedIn ; WhatsApp ignore les images trop lourdes, d'où q_auto.
    private static final String CLOUDINARY_OG_TRANSFORM = "c_fill,g_auto,w_1200,h_630,q_auto,f_jpg/";

    private final ProjectService projectService;
    private final NewsArticleService newsArticleService;

    // Repli quand la requête n'arrive pas par un de nos domaines (ex. appel direct via l'API).
    @Value("${app.site-url}")
    private String siteUrl;

    // Domaines du site vitrine : l'aperçu reprend celui sur lequel le lien a été partagé, pour
    // suivre automatiquement le passage de site. à www. sans redéploiement.
    @Value("${app.site-hosts}")
    private List<String> siteHosts;

    @GetMapping(value = "/projets/{slug}", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> project(@PathVariable String slug, HttpServletRequest request) {
        String baseUrl = baseUrl(request);
        String pageUrl = baseUrl + "/projets/" + slug;
        try {
            ProjectDTO project = projectService.getBySlug(slug);
            String image = project.getImage();
            if (isBlank(image) && project.getGallery() != null && !project.getGallery().isEmpty()) {
                image = project.getGallery().get(0);
            }
            return html(project.getTitleFr(), project.getDescriptionFr(), image, baseUrl, pageUrl, "article");
        } catch (AccountResourceException e) {
            return html(null, null, null, baseUrl, pageUrl, "website");
        }
    }

    @GetMapping(value = "/actualites/{slug}", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> news(@PathVariable String slug, HttpServletRequest request) {
        String baseUrl = baseUrl(request);
        String pageUrl = baseUrl + "/actualites/" + slug;
        try {
            NewsArticleDTO article = newsArticleService.getBySlug(slug);
            String description = isBlank(article.getExcerptFr()) ? article.getBodyFr() : article.getExcerptFr();
            return html(article.getTitleFr(), description, article.getImage(), baseUrl, pageUrl, "article");
        } catch (AccountResourceException e) {
            return html(null, null, null, baseUrl, pageUrl, "website");
        }
    }

    private ResponseEntity<String> html(String title, String description, String image, String baseUrl,
                                        String pageUrl, String type) {
        String t = escape(isBlank(title) ? SITE_NAME : title.trim());
        String d = escape(summarize(isBlank(description) ? DEFAULT_DESCRIPTION : description));
        String imageUrl = ogImage(image, baseUrl);
        String img = escape(imageUrl);
        // Dimensions annoncées seulement quand on les connaît (image par défaut ou recadrée par
        // Cloudinary) : WhatsApp en a parfois besoin pour afficher la vignette.
        String imageSize = imageUrl.endsWith(DEFAULT_IMAGE_PATH) || imageUrl.contains(CLOUDINARY_OG_TRANSFORM)
                ? """
                <meta property="og:image:type" content="image/jpeg">
                <meta property="og:image:width" content="1200">
                <meta property="og:image:height" content="630">
                """
                : "";
        String url = escape(pageUrl);
        String body = """
                <!doctype html>
                <html lang="fr">
                <head>
                <meta charset="utf-8">
                <title>%1$s</title>
                <meta name="description" content="%2$s">
                <link rel="canonical" href="%4$s">
                <meta property="og:type" content="%5$s">
                <meta property="og:site_name" content="%6$s">
                <meta property="og:locale" content="fr_FR">
                <meta property="og:title" content="%1$s">
                <meta property="og:description" content="%2$s">
                <meta property="og:url" content="%4$s">
                <meta property="og:image" content="%3$s">
                <meta property="og:image:secure_url" content="%3$s">
                <meta property="og:image:alt" content="%1$s">
                %7$s<meta name="twitter:card" content="summary_large_image">
                <meta name="twitter:title" content="%1$s">
                <meta name="twitter:description" content="%2$s">
                <meta name="twitter:image" content="%3$s">
                </head>
                <body>
                <h1>%1$s</h1>
                <p>%2$s</p>
                <p><a href="%4$s">%4$s</a></p>
                </body>
                </html>
                """.formatted(t, d, img, url, type, escape(SITE_NAME), imageSize);
        return ResponseEntity.ok().contentType(new MediaType(MediaType.TEXT_HTML, StandardCharsets.UTF_8)).body(body);
    }

    private static String ogImage(String image, String baseUrl) {
        if (isBlank(image)) {
            return baseUrl + DEFAULT_IMAGE_PATH;
        }
        String url = image.trim();
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            return baseUrl + (url.startsWith("/") ? "" : "/") + url;
        }
        int idx = url.indexOf(CLOUDINARY_UPLOAD);
        if (url.contains("res.cloudinary.com") && idx >= 0) {
            int insertAt = idx + CLOUDINARY_UPLOAD.length();
            return url.substring(0, insertAt) + CLOUDINARY_OG_TRANSFORM + url.substring(insertAt);
        }
        return url;
    }

    private String baseUrl(HttpServletRequest request) {
        String host = request.getServerName();
        return host != null && siteHosts.contains(host.toLowerCase()) ? "https://" + host.toLowerCase() : siteUrl;
    }

    private static String summarize(String text) {
        String paragraph = text.trim().split("\\R\\s*\\R", 2)[0].replaceAll("\\s+", " ").trim();
        if (paragraph.length() <= DESCRIPTION_MAX_LENGTH) {
            return paragraph;
        }
        String cut = paragraph.substring(0, DESCRIPTION_MAX_LENGTH);
        int lastSpace = cut.lastIndexOf(' ');
        return (lastSpace > 0 ? cut.substring(0, lastSpace) : cut) + "…";
    }

    private static String escape(String value) {
        return HtmlUtils.htmlEscape(value, "UTF-8");
    }

    private static boolean isBlank(String value) {
        return value == null || value.isBlank();
    }
}
