package athl.logistics.athl_logistics.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "about_commitments")
public class AboutCommitment {

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

    @Column(name = "text_fr", columnDefinition = "TEXT")
    private String textFr;
    @Column(name = "text_en", columnDefinition = "TEXT")
    private String textEn;

    @Column(name = "sort_order")
    private int sortOrder;
}
