package athl.logistics.athl_logistics.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

// Un des 3 compteurs propres à un onglet du bloc "Bienvenue chez ATHL" (ex: "Chantiers
// livrés" pour Construction, "Véhicules gérés" pour Mobilité). Libellé éditable car il
// change de sens d'un onglet à l'autre.
@Data
@NoArgsConstructor
@Entity
@Table(name = "about_workforce_stats")
public class AboutWorkforceStat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tab_id", nullable = false)
    private AboutWorkforceTab tab;

    @Column(name = "label_fr")
    private String labelFr;
    @Column(name = "label_en")
    private String labelEn;

    private double value;
    private int decimals;
    private String suffix;

    @Column(name = "sort_order")
    private int sortOrder;
}
