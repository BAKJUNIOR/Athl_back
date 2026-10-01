package athl.logistics.athl_logistics.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

// Un des 4 rôles terrain affichés dans "Nos équipes terrain" (ex: Ouvriers qualifiés).
// Icône fixe côté front, liée à la position — seul le libellé est éditable.
@Data
@NoArgsConstructor
@Entity
@Table(name = "about_ground_roles")
public class AboutGroundRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "about_page_id", nullable = false)
    private AboutPageContent aboutPage;

    @Column(name = "label_fr")
    private String labelFr;
    @Column(name = "label_en")
    private String labelEn;

    @Column(name = "sort_order")
    private int sortOrder;
}
