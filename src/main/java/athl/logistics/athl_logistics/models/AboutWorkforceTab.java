package athl.logistics.athl_logistics.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

// Un des onglets du bloc "Bienvenue chez ATHL" (affiché sur l'accueil ET À propos, même
// donnée aux deux endroits — voir WorkforceTabsComponent côté front). Texte et images propres
// à ce bloc, distincts de AboutPillar ("Nos 3 métiers") même si le sujet de fond se ressemble.
@Data
@NoArgsConstructor
@Entity
@Table(name = "about_workforce_tabs")
public class AboutWorkforceTab {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "about_page_id", nullable = false)
    private AboutPageContent aboutPage;

    private String number;

    @Column(name = "title_fr")
    private String titleFr;
    @Column(name = "title_en")
    private String titleEn;

    private String image;
    @Column(name = "hero_image")
    private String heroImage;

    @Column(name = "lead_fr", columnDefinition = "TEXT")
    private String leadFr;
    @Column(name = "lead_en", columnDefinition = "TEXT")
    private String leadEn;

    @Column(name = "bullet1_fr")
    private String bullet1Fr;
    @Column(name = "bullet1_en")
    private String bullet1En;
    @Column(name = "bullet2_fr")
    private String bullet2Fr;
    @Column(name = "bullet2_en")
    private String bullet2En;
    @Column(name = "bullet3_fr")
    private String bullet3Fr;
    @Column(name = "bullet3_en")
    private String bullet3En;
    @Column(name = "bullet4_fr")
    private String bullet4Fr;
    @Column(name = "bullet4_en")
    private String bullet4En;

    @OneToMany(mappedBy = "tab", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @OrderBy("sortOrder ASC")
    private List<AboutWorkforceStat> stats = new ArrayList<>();

    @Column(name = "sort_order")
    private int sortOrder;
}
