package athl.logistics.athl_logistics.service.dto;

import athl.logistics.athl_logistics.models.HomePageContent;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

// Contenu complet de la page d'accueil — lecture (front public) et édition (BO).
@Data
@NoArgsConstructor
public class HomePageContentDTO {
    private String heroTitleLine1Fr;
    private String heroTitleLine1En;
    private String heroTitleLine2Fr;
    private String heroTitleLine2En;
    private String heroSubtitleFr;
    private String heroSubtitleEn;
    private List<String> heroImages;

    private String pillarConstructionLeadFr;
    private String pillarConstructionLeadEn;
    private String pillarConstructionImage;

    private String pillarMobilityLeadFr;
    private String pillarMobilityLeadEn;
    private String pillarMobilityImage;

    private String pillarImportLeadFr;
    private String pillarImportLeadEn;
    private String pillarImportImage;

    public HomePageContentDTO(HomePageContent entity) {
        this.heroTitleLine1Fr = entity.getHeroTitleLine1Fr();
        this.heroTitleLine1En = entity.getHeroTitleLine1En();
        this.heroTitleLine2Fr = entity.getHeroTitleLine2Fr();
        this.heroTitleLine2En = entity.getHeroTitleLine2En();
        this.heroSubtitleFr = entity.getHeroSubtitleFr();
        this.heroSubtitleEn = entity.getHeroSubtitleEn();
        this.heroImages = entity.getHeroImages();

        this.pillarConstructionLeadFr = entity.getPillarConstructionLeadFr();
        this.pillarConstructionLeadEn = entity.getPillarConstructionLeadEn();
        this.pillarConstructionImage = entity.getPillarConstructionImage();

        this.pillarMobilityLeadFr = entity.getPillarMobilityLeadFr();
        this.pillarMobilityLeadEn = entity.getPillarMobilityLeadEn();
        this.pillarMobilityImage = entity.getPillarMobilityImage();

        this.pillarImportLeadFr = entity.getPillarImportLeadFr();
        this.pillarImportLeadEn = entity.getPillarImportLeadEn();
        this.pillarImportImage = entity.getPillarImportImage();
    }
}
