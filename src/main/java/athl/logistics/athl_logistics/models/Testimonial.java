package athl.logistics.athl_logistics.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

/**
 * Témoignage client affiché en carrousel sur la page d'accueil. Le nom n'est pas traduit
 * (comme TeamMember), seuls le rôle et le texte le sont.
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "testimonials")
public class Testimonial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le nom est requis")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Le rôle en français est requis")
    @Column(name = "role_fr", nullable = false)
    private String roleFr;

    @Column(name = "role_en")
    private String roleEn;

    @NotBlank(message = "Le texte en français est requis")
    @Column(name = "text_fr", nullable = false, columnDefinition = "TEXT")
    private String textFr;

    @Column(name = "text_en", columnDefinition = "TEXT")
    private String textEn;

    private String photo;

    private String initials;

    @Column(name = "sort_order")
    private int sortOrder;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt;
}
