package athl.logistics.athl_logistics.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

// Un logo du bandeau "Nos partenaires" (ex: Toyota, DHL...). Nom de marque tiers, pas de
// FR/EN — seul le logo (image) est propre à chaque ligne.
@Data
@NoArgsConstructor
@Entity
@Table(name = "partners")
public class Partner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "section_id", nullable = false)
    private PartnersSection section;

    private String name;
    private String logo;

    @Column(name = "sort_order")
    private int sortOrder;
}
