package athl.logistics.athl_logistics.models;

import athl.logistics.athl_logistics.models.enums.NewsStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.time.LocalDate;

/**
 * Actualité affichée sur /actualites et sa fiche détail /actualites/:slug.
 * `slug` est généré une seule fois par le backend à la création (voir NewsArticleServiceImpl) et
 * reste ensuite immuable, comme Project.slug et ServiceOffering.slug. `bodyFr`/`bodyEn` stockent
 * le texte complet avec des paragraphes séparés par un double saut de ligne, comme
 * Project.descriptionFr/En — c'est le front qui les redécoupe pour l'affichage. La citation est
 * optionnelle (tous les champs quote* à null si l'article n'en a pas).
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "news_articles")
public class NewsArticle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, updatable = false)
    private String slug;

    private String image;

    @Column(nullable = false)
    private LocalDate date;

    private boolean featured;

    @Column(name = "category_fr")
    private String categoryFr;

    @Column(name = "category_en")
    private String categoryEn;

    @NotBlank(message = "Le titre en français est requis")
    @Column(name = "title_fr", nullable = false)
    private String titleFr;

    @Column(name = "title_en")
    private String titleEn;

    @Column(name = "excerpt_fr", columnDefinition = "TEXT")
    private String excerptFr;

    @Column(name = "excerpt_en", columnDefinition = "TEXT")
    private String excerptEn;

    @Column(name = "body_fr", columnDefinition = "TEXT")
    private String bodyFr;

    @Column(name = "body_en", columnDefinition = "TEXT")
    private String bodyEn;

    @Column(name = "quote_text_fr", columnDefinition = "TEXT")
    private String quoteTextFr;

    @Column(name = "quote_text_en", columnDefinition = "TEXT")
    private String quoteTextEn;

    @Column(name = "quote_name_fr")
    private String quoteNameFr;

    @Column(name = "quote_name_en")
    private String quoteNameEn;

    @Column(name = "quote_role_fr")
    private String quoteRoleFr;

    @Column(name = "quote_role_en")
    private String quoteRoleEn;

    // Liens vers la republication de CETTE actualité sur nos réseaux (pas les comptes généraux
    // du site, voir SiteContact) — affichés sur la fiche détail uniquement si renseignés.
    @Column(name = "facebook_url")
    private String facebookUrl;

    @Column(name = "linkedin_url")
    private String linkedinUrl;

    @Column(name = "youtube_url")
    private String youtubeUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NewsStatus status = NewsStatus.DRAFT;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt;
}
