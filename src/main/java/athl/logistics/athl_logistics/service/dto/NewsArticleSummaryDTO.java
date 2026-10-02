package athl.logistics.athl_logistics.service.dto;

import athl.logistics.athl_logistics.models.NewsArticle;
import athl.logistics.athl_logistics.models.enums.NewsStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

// Version allégée pour la liste /actualites : pas de corps ni de citation, seule la fiche
// détail (NewsArticleDTO, via /slug/{slug}) les charge.
@Data
@NoArgsConstructor
public class NewsArticleSummaryDTO {
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
    private NewsStatus status;

    public NewsArticleSummaryDTO(NewsArticle entity) {
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
        this.status = entity.getStatus();
    }
}
