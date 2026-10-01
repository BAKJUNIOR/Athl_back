package athl.logistics.athl_logistics.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Contenu propre à la page /a-propos du site vitrine : le bloc "Bienvenue chez ATHL"
 * (affiché aussi sur l'accueil, même donnée aux deux endroits), les en-têtes de section,
 * les 3 cartes "Nos 3 métiers" (texte et image indépendants de Service, même si la carte
 * se ressemble visuellement), le bloc "Nos équipes terrain" et "Ce qui nous engage". Ne
 * contient PAS la grille des membres (reste sur TeamMember, partagée avec /equipe).
 * Table singleton comme SiteContact : une seule ligne, id figé à 1. Seedée entièrement par
 * Liquibase (voir db/changelog), pas par DataSeeder.
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "about_page_content")
public class AboutPageContent {

    @Id
    private Long id = 1L;

    // ----- Bloc "Bienvenue chez ATHL" (onglets) — affiché sur l'accueil ET À propos, même
    // donnée aux deux endroits (voir WorkforceTabsComponent côté front). -----
    @Column(name = "workforce_eyebrow_fr")
    private String workforceEyebrowFr;
    @Column(name = "workforce_eyebrow_en")
    private String workforceEyebrowEn;
    @Column(name = "workforce_title_fr")
    private String workforceTitleFr;
    @Column(name = "workforce_title_en")
    private String workforceTitleEn;
    @Column(name = "workforce_lead_fr", columnDefinition = "TEXT")
    private String workforceLeadFr;
    @Column(name = "workforce_lead_en", columnDefinition = "TEXT")
    private String workforceLeadEn;

    @OneToMany(mappedBy = "aboutPage", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @OrderBy("sortOrder ASC")
    private List<AboutWorkforceTab> workforceTabs = new ArrayList<>();

    // ----- En-tête de page -----
    @Column(name = "hero_eyebrow_fr")
    private String heroEyebrowFr;
    @Column(name = "hero_eyebrow_en")
    private String heroEyebrowEn;
    @Column(name = "hero_title_fr")
    private String heroTitleFr;
    @Column(name = "hero_title_en")
    private String heroTitleEn;
    @Column(name = "hero_lead_fr", columnDefinition = "TEXT")
    private String heroLeadFr;
    @Column(name = "hero_lead_en", columnDefinition = "TEXT")
    private String heroLeadEn;

    // ----- En-tête "Nos 3 métiers" -----
    @Column(name = "pillars_eyebrow_fr")
    private String pillarsEyebrowFr;
    @Column(name = "pillars_eyebrow_en")
    private String pillarsEyebrowEn;
    @Column(name = "pillars_title_fr")
    private String pillarsTitleFr;
    @Column(name = "pillars_title_en")
    private String pillarsTitleEn;
    @Column(name = "pillars_lead_fr", columnDefinition = "TEXT")
    private String pillarsLeadFr;
    @Column(name = "pillars_lead_en", columnDefinition = "TEXT")
    private String pillarsLeadEn;

    @OneToMany(mappedBy = "aboutPage", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @OrderBy("sortOrder ASC")
    private List<AboutPillar> pillars = new ArrayList<>();

    // ----- En-tête "Nos équipes" -----
    @Column(name = "team_eyebrow_fr")
    private String teamEyebrowFr;
    @Column(name = "team_eyebrow_en")
    private String teamEyebrowEn;
    @Column(name = "team_title_fr")
    private String teamTitleFr;
    @Column(name = "team_title_en")
    private String teamTitleEn;
    @Column(name = "team_lead_fr", columnDefinition = "TEXT")
    private String teamLeadFr;
    @Column(name = "team_lead_en", columnDefinition = "TEXT")
    private String teamLeadEn;

    // ----- "Nos équipes terrain" -----
    @Column(name = "ground_title_fr")
    private String groundTitleFr;
    @Column(name = "ground_title_en")
    private String groundTitleEn;
    @Column(name = "ground_lead_fr", columnDefinition = "TEXT")
    private String groundLeadFr;
    @Column(name = "ground_lead_en", columnDefinition = "TEXT")
    private String groundLeadEn;
    @Column(name = "ground_cta_label_fr")
    private String groundCtaLabelFr;
    @Column(name = "ground_cta_label_en")
    private String groundCtaLabelEn;

    @ElementCollection
    @CollectionTable(name = "about_ground_images", joinColumns = @JoinColumn(name = "about_page_id"))
    @Column(name = "image_url")
    @OrderColumn(name = "sort_order")
    private List<String> groundImages = new ArrayList<>();

    @OneToMany(mappedBy = "aboutPage", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @OrderBy("sortOrder ASC")
    private List<AboutGroundRole> groundRoles = new ArrayList<>();

    @OneToMany(mappedBy = "aboutPage", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @OrderBy("sortOrder ASC")
    private List<AboutCommitment> commitments = new ArrayList<>();

    // ----- "Ce qui nous engage" -----
    @Column(name = "values_title_fr")
    private String valuesTitleFr;
    @Column(name = "values_title_en")
    private String valuesTitleEn;
    @Column(name = "values_background_image")
    private String valuesBackgroundImage;

    @OneToMany(mappedBy = "aboutPage", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @OrderBy("sortOrder ASC")
    private List<AboutValue> values = new ArrayList<>();
}
