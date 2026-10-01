package athl.logistics.athl_logistics.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

// Une des 3 cartes "Nos 3 métiers" de la page À propos — image et texte propres à cette
// page (voir AboutPageContent), pas liés à ServiceOffering même si la carte se ressemble.
@Data
@NoArgsConstructor
@Entity
@Table(name = "about_pillars")
public class AboutPillar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "about_page_id", nullable = false)
    private AboutPageContent aboutPage;

    @Column(name = "title_fr")
    private String titleFr;
    @Column(name = "title_en")
    private String titleEn;

    private String image;

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

    @Column(name = "sort_order")
    private int sortOrder;
}
