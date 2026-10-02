package athl.logistics.athl_logistics.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Contenu propre à la page d'accueil (/) : le bandeau d'en-tête (titre sur 2 lignes, sous-titre,
 * galerie d'images de fond qui défilent) et les 3 cartes "pilier" affichées juste en dessous.
 * Texte et image des piliers sont propres à cette page — pas liés à ServiceOffering ni à
 * AboutPillar même si les 3 métiers décrits sont les mêmes (voir AboutPageContent pour les
 * cartes de la page À propos, qui ont leur propre contenu). Icône et titre des piliers restent
 * fixes côté front (3 emplacements par position), seuls le texte et l'image sont éditables ici.
 * Table singleton comme SiteContact/AboutPageContent : une seule ligne, id figé à 1.
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "home_page_content")
public class HomePageContent {

    @Id
    private Long id = 1L;

    // ----- Bandeau d'en-tête -----
    @Column(name = "hero_title_line1_fr")
    private String heroTitleLine1Fr;
    @Column(name = "hero_title_line1_en")
    private String heroTitleLine1En;
    @Column(name = "hero_title_line2_fr")
    private String heroTitleLine2Fr;
    @Column(name = "hero_title_line2_en")
    private String heroTitleLine2En;
    @Column(name = "hero_subtitle_fr", columnDefinition = "TEXT")
    private String heroSubtitleFr;
    @Column(name = "hero_subtitle_en", columnDefinition = "TEXT")
    private String heroSubtitleEn;

    @ElementCollection
    @CollectionTable(name = "home_hero_images", joinColumns = @JoinColumn(name = "home_page_id"))
    @Column(name = "image_url")
    @OrderColumn(name = "sort_order")
    private List<String> heroImages = new ArrayList<>();

    // ----- Carte pilier "Construction & rénovation" -----
    @Column(name = "pillar_construction_lead_fr", columnDefinition = "TEXT")
    private String pillarConstructionLeadFr;
    @Column(name = "pillar_construction_lead_en", columnDefinition = "TEXT")
    private String pillarConstructionLeadEn;
    @Column(name = "pillar_construction_image")
    private String pillarConstructionImage;

    // ----- Carte pilier "Mobilité, VTC & Livraison" -----
    @Column(name = "pillar_mobility_lead_fr", columnDefinition = "TEXT")
    private String pillarMobilityLeadFr;
    @Column(name = "pillar_mobility_lead_en", columnDefinition = "TEXT")
    private String pillarMobilityLeadEn;
    @Column(name = "pillar_mobility_image")
    private String pillarMobilityImage;

    // ----- Carte pilier "Import & logistique" -----
    @Column(name = "pillar_import_lead_fr", columnDefinition = "TEXT")
    private String pillarImportLeadFr;
    @Column(name = "pillar_import_lead_en", columnDefinition = "TEXT")
    private String pillarImportLeadEn;
    @Column(name = "pillar_import_image")
    private String pillarImportImage;
}
