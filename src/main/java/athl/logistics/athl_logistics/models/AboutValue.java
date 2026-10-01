package athl.logistics.athl_logistics.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

// Une des 4 valeurs affichées dans "Ce qui nous engage" (ex: Talent). Icône et numéro (01-04)
// fixes côté front, liés à la position — seuls libellé et texte sont éditables.
@Data
@NoArgsConstructor
@Entity
@Table(name = "about_values")
public class AboutValue {

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

    @Column(name = "text_fr", columnDefinition = "TEXT")
    private String textFr;
    @Column(name = "text_en", columnDefinition = "TEXT")
    private String textEn;

    @Column(name = "sort_order")
    private int sortOrder;
}
