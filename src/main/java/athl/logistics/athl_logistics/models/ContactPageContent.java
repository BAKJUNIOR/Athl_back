package athl.logistics.athl_logistics.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Contenu propre à la page /contact du site vitrine : textes des en-têtes et des cartes
 * d'information. Les coordonnées elles-mêmes (téléphones, adresse, email, réseaux) restent
 * sur SiteContact, partagées avec le footer. Table singleton : une seule ligne, id figé à 1.
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "contact_page_content")
public class ContactPageContent {

    @Id
    private Long id = 1L;

    @Column(name = "hero_eyebrow_fr")
    private String heroEyebrowFr;
    @Column(name = "hero_eyebrow_en")
    private String heroEyebrowEn;
    @Column(name = "hero_title_fr")
    private String heroTitleFr;
    @Column(name = "hero_title_en")
    private String heroTitleEn;
    @Column(name = "hero_subtitle_fr", columnDefinition = "TEXT")
    private String heroSubtitleFr;
    @Column(name = "hero_subtitle_en", columnDefinition = "TEXT")
    private String heroSubtitleEn;

    @Column(name = "write_to_us_eyebrow_fr")
    private String writeToUsEyebrowFr;
    @Column(name = "write_to_us_eyebrow_en")
    private String writeToUsEyebrowEn;

    @Column(name = "info_eyebrow_fr")
    private String infoEyebrowFr;
    @Column(name = "info_eyebrow_en")
    private String infoEyebrowEn;
    @Column(name = "info_heading_fr")
    private String infoHeadingFr;
    @Column(name = "info_heading_en")
    private String infoHeadingEn;

    @Column(name = "phone_title_fr")
    private String phoneTitleFr;
    @Column(name = "phone_title_en")
    private String phoneTitleEn;
    @Column(name = "phone_note_fr")
    private String phoneNoteFr;
    @Column(name = "phone_note_en")
    private String phoneNoteEn;

    @Column(name = "email_title_fr")
    private String emailTitleFr;
    @Column(name = "email_title_en")
    private String emailTitleEn;

    @Column(name = "address_title_fr")
    private String addressTitleFr;
    @Column(name = "address_title_en")
    private String addressTitleEn;
    @Column(name = "address_note_fr")
    private String addressNoteFr;
    @Column(name = "address_note_en")
    private String addressNoteEn;
}
