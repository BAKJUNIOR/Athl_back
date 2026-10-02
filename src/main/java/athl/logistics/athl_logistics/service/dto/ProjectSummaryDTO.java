package athl.logistics.athl_logistics.service.dto;

import athl.logistics.athl_logistics.models.Project;
import athl.logistics.athl_logistics.models.enums.ProjectStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

// Version allégée pour la grille /projets (groupée par métier) : pas de galerie ni de
// description, seule la fiche détail (ProjectDTO, via /slug/{slug}) les charge.
@Data
@NoArgsConstructor
public class ProjectSummaryDTO {
    private Long id;
    private String slug;
    private String serviceSlug;
    private String titleFr;
    private String titleEn;
    private String locationFr;
    private String locationEn;
    private String year;
    private String image;
    private boolean featured;
    private int sortOrder;
    private ProjectStatus status;

    public ProjectSummaryDTO(Project entity) {
        this.id = entity.getId();
        this.slug = entity.getSlug();
        this.serviceSlug = entity.getService().getSlug();
        this.titleFr = entity.getTitleFr();
        this.titleEn = entity.getTitleEn();
        this.locationFr = entity.getLocationFr();
        this.locationEn = entity.getLocationEn();
        this.year = entity.getYear();
        this.image = entity.getImage();
        this.featured = entity.isFeatured();
        this.sortOrder = entity.getSortOrder();
        this.status = entity.getStatus();
    }
}
