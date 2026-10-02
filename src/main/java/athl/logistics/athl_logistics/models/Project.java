package athl.logistics.athl_logistics.models;

import athl.logistics.athl_logistics.models.enums.ProjectStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * Réalisation affichée sur /projets (groupée par métier) et sa fiche détail /projets/:slug.
 * `slug` est généré une seule fois par le backend à la création (voir ProjectServiceImpl) et
 * reste ensuite immuable, comme ServiceOffering.slug. `featured` pilote la vignette "à la une"
 * de l'accueil (indépendamment du regroupement par métier).
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, updatable = false)
    private String slug;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id", nullable = false)
    private ServiceOffering service;

    @NotBlank(message = "Le titre en français est requis")
    @Column(name = "title_fr", nullable = false)
    private String titleFr;

    @Column(name = "title_en")
    private String titleEn;

    @Column(name = "location_fr")
    private String locationFr;

    @Column(name = "location_en")
    private String locationEn;

    @Column(name = "typology_fr")
    private String typologyFr;

    @Column(name = "typology_en")
    private String typologyEn;

    private String year;

    @Column(name = "description_fr", columnDefinition = "TEXT")
    private String descriptionFr;

    @Column(name = "description_en", columnDefinition = "TEXT")
    private String descriptionEn;

    /** Vignette (grille + repli 1ère image de la galerie sur la fiche détail). */
    private String image;

    @ElementCollection
    @CollectionTable(name = "project_gallery", joinColumns = @JoinColumn(name = "project_id"))
    @Column(name = "image_url")
    @OrderColumn(name = "sort_order")
    private List<String> gallery = new ArrayList<>();

    private boolean featured;

    @Column(name = "sort_order")
    private int sortOrder;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProjectStatus status = ProjectStatus.DRAFT;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt;
}
