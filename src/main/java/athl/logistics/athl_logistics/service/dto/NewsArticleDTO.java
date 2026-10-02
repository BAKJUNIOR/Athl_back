package athl.logistics.athl_logistics.service.dto;

import athl.logistics.athl_logistics.models.NewsArticle;
import athl.logistics.athl_logistics.models.enums.NewsStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;

// Détail complet d'une actualité — édition (BO) et fiche détail /actualites/:slug (front).
@Data
@NoArgsConstructor
public class NewsArticleDTO {
    private Long id;
    private String slug;
    private String image;
    private LocalDate date;
    private boolean featured;
    private String categoryFr;
    private String categoryEn;
    private String titleFr;
    private String titleEn;
    private String excerptFr;
    private String excerptEn;
    private String bodyFr;
    private String bodyEn;
    private String quoteTextFr;
    private String quoteTextEn;
    private String quoteNameFr;
    private String quoteNameEn;
    private String quoteRoleFr;
    private String quoteRoleEn;
    private String facebookUrl;
    private String linkedinUrl;
    private String youtubeUrl;
    private NewsStatus status;
    private Instant updatedAt;

    public NewsArticleDTO(NewsArticle entity) {
        this.id = entity.getId();
        this.slug = entity.getSlug();
        this.image = entity.getImage();
        this.date = entity.getDate();
        this.featured = entity.isFeatured();
        this.categoryFr = entity.getCategoryFr();
        this.categoryEn = entity.getCategoryEn();
        this.titleFr = entity.getTitleFr();
        this.titleEn = entity.getTitleEn();
        this.excerptFr = entity.getExcerptFr();
        this.excerptEn = entity.getExcerptEn();
        this.bodyFr = entity.getBodyFr();
        this.bodyEn = entity.getBodyEn();
        this.quoteTextFr = entity.getQuoteTextFr();
        this.quoteTextEn = entity.getQuoteTextEn();
        this.quoteNameFr = entity.getQuoteNameFr();
        this.quoteNameEn = entity.getQuoteNameEn();
        this.quoteRoleFr = entity.getQuoteRoleFr();
        this.quoteRoleEn = entity.getQuoteRoleEn();
        this.facebookUrl = entity.getFacebookUrl();
        this.linkedinUrl = entity.getLinkedinUrl();
        this.youtubeUrl = entity.getYoutubeUrl();
        this.status = entity.getStatus();
        this.updatedAt = entity.getUpdatedAt();
    }
}
