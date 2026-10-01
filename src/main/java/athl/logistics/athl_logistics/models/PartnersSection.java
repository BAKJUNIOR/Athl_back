package athl.logistics.athl_logistics.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Bandeau "Nos partenaires" affiché au-dessus du footer sur la plupart des pages du site
 * vitrine (masqué sur /contact et /actualites — voir PartnersComponent côté front).
 * Table singleton comme SiteContact/AboutPageContent : une seule ligne, id figé à 1.
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "partners_section")
public class PartnersSection {

    @Id
    private Long id = 1L;

    @Column(name = "eyebrow_fr")
    private String eyebrowFr;
    @Column(name = "eyebrow_en")
    private String eyebrowEn;

    @Column(name = "title_fr")
    private String titleFr;
    @Column(name = "title_en")
    private String titleEn;

    @Column(name = "subtitle_fr", columnDefinition = "TEXT")
    private String subtitleFr;
    @Column(name = "subtitle_en", columnDefinition = "TEXT")
    private String subtitleEn;

    @Column(name = "cta_label_fr")
    private String ctaLabelFr;
    @Column(name = "cta_label_en")
    private String ctaLabelEn;

    @OneToMany(mappedBy = "section", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @OrderBy("sortOrder ASC")
    private List<Partner> partners = new ArrayList<>();
}
