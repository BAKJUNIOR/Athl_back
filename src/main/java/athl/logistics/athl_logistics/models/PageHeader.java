package athl.logistics.athl_logistics.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * En-tête (surtitre, titre, texte d'introduction) d'une page de liste du site vitrine :
 * /services, /projets, /equipe, /carrieres, /devis, /actualites, /recherche. Une ligne par page,
 * identifiée par `pageKey` (voir PageHeaderServiceImpl.PAGE_KEYS). Les pages Accueil, À propos
 * et Contact ont leur propre contenu (HomePageContent, AboutPageContent, ContactPageContent).
 * Un champ vide = le front affiche son texte par défaut (public/i18n).
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "page_headers")
public class PageHeader {

    @Id
    @Column(name = "page_key", length = 40)
    private String pageKey;

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
}
