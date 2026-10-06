package athl.logistics.athl_logistics.web.resource.share;

import athl.logistics.athl_logistics.service.NewsArticleService;
import athl.logistics.athl_logistics.service.ProjectService;
import athl.logistics.athl_logistics.service.dto.NewsArticleDTO;
import athl.logistics.athl_logistics.service.dto.ProjectDTO;
import athl.logistics.athl_logistics.web.errors.AccountResourceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ShareControllerTest {

    private final ProjectService projectService = mock(ProjectService.class);
    private final NewsArticleService newsArticleService = mock(NewsArticleService.class);
    private final ShareController controller = new ShareController(projectService, newsArticleService);

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(controller, "siteUrl", "https://site.athl-logistique.com");
        ReflectionTestUtils.setField(controller, "siteHosts",
                List.of("athl-logistique.com", "www.athl-logistique.com", "site.athl-logistique.com"));
    }

    private static MockHttpServletRequest requestFrom(String host) {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setServerName(host);
        return request;
    }

    @Test
    void newsUsesExcerptAndCloudinaryCroppedImage() {
        NewsArticleDTO article = new NewsArticleDTO();
        article.setTitleFr("ATHL renforce sa flotte Mobilité & VTC");
        article.setExcerptFr("Face à la demande croissante de nos clients.");
        article.setBodyFr("Corps de l'article.");
        article.setImage("https://res.cloudinary.com/demo/image/upload/v1/news/vtc.jpg");
        when(newsArticleService.getBySlug("flotte-mobilite-vtc")).thenReturn(article);

        String html = controller.news("flotte-mobilite-vtc", requestFrom("site.athl-logistique.com")).getBody();

        assertThat(html)
                .contains("<meta property=\"og:title\" content=\"ATHL renforce sa flotte Mobilité &amp; VTC\">")
                .contains("<meta property=\"og:description\" content=\"Face à la demande croissante de nos clients.\">")
                .contains("<meta property=\"og:image\" content=\"https://res.cloudinary.com/demo/image/upload/c_fill,g_auto,w_1200,h_630,q_auto,f_jpg/v1/news/vtc.jpg\">")
                .contains("<meta property=\"og:url\" content=\"https://site.athl-logistique.com/actualites/flotte-mobilite-vtc\">")
                .contains("<meta property=\"og:image:width\" content=\"1200\">")
                .contains("<meta property=\"og:image:height\" content=\"630\">");
    }

    @Test
    void projectFallsBackToGalleryAndFirstParagraph() {
        ProjectDTO project = new ProjectDTO();
        project.setTitleFr("Villa duplex N'Guessan");
        project.setDescriptionFr("Premier paragraphe.\n\nSecond paragraphe.");
        project.setGallery(List.of("images/proj-1.png"));
        when(projectService.getBySlug("villa-duplex-nguessan")).thenReturn(project);

        String html = controller.project("villa-duplex-nguessan", requestFrom("site.athl-logistique.com")).getBody();

        assertThat(html)
                .contains("content=\"Premier paragraphe.\"")
                .doesNotContain("Second paragraphe")
                .contains("<meta property=\"og:image\" content=\"https://site.athl-logistique.com/images/proj-1.png\">")
                .doesNotContain("og:image:width");
    }

    @Test
    void longDescriptionIsCutOnAWord() {
        NewsArticleDTO article = new NewsArticleDTO();
        article.setTitleFr("Titre");
        article.setBodyFr("mot ".repeat(100));
        when(newsArticleService.getBySlug("long")).thenReturn(article);

        String html = controller.news("long", requestFrom("site.athl-logistique.com")).getBody();

        assertThat(html).contains("mot mot…\">");
    }

    @Test
    void unknownSlugGivesGenericPreview() {
        when(projectService.getBySlug("inconnu"))
                .thenThrow(new AccountResourceException("Projet introuvable.", HttpStatus.NOT_FOUND));

        String html = controller.project("inconnu", requestFrom("site.athl-logistique.com")).getBody();

        assertThat(html)
                .contains("<meta property=\"og:title\" content=\"ATHL - Africa Talent Habitat &amp; Logistique\">")
                .contains("<meta property=\"og:image\" content=\"https://site.athl-logistique.com/images/og-default.jpg\">");
    }

    @Test
    void wwwDomainIsUsedAutomatically() {
        when(newsArticleService.getBySlug("inconnu"))
                .thenThrow(new AccountResourceException("Actualité introuvable.", HttpStatus.NOT_FOUND));

        String html = controller.news("inconnu", requestFrom("www.athl-logistique.com")).getBody();

        assertThat(html)
                .contains("<meta property=\"og:url\" content=\"https://www.athl-logistique.com/actualites/inconnu\">")
                .contains("<meta property=\"og:image\" content=\"https://www.athl-logistique.com/images/og-default.jpg\">");
    }

    @Test
    void unknownHostFallsBackToSiteUrl() {
        when(newsArticleService.getBySlug("inconnu"))
                .thenThrow(new AccountResourceException("Actualité introuvable.", HttpStatus.NOT_FOUND));

        String html = controller.news("inconnu", requestFrom("evil.example.com")).getBody();

        assertThat(html)
                .contains("<meta property=\"og:url\" content=\"https://site.athl-logistique.com/actualites/inconnu\">")
                .doesNotContain("evil.example.com");
    }
}
