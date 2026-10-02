package athl.logistics.athl_logistics.service.dto;

import athl.logistics.athl_logistics.models.enums.NewsStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

// Payload créer/modifier une actualité — pas de slug ni d'id : le slug est généré une seule fois
// par le backend à la création et n'est plus jamais modifiable depuis le BO (voir NewsArticle).
@Data
@NoArgsConstructor
public class NewsArticleUpsertDTO {

    private String image;

    @NotNull(message = "La date est requise")
    private LocalDate date;

    private boolean featured;
    private String categoryFr;
    private String categoryEn;

    @NotBlank(message = "Le titre en français est requis")
    private String titleFr;

    private String titleEn;
    private String excerptFr;
    private String excerptEn;
    private String bodyFr;
    private String bodyEn;
    private String quoteTextFr;
    private String quoteTextEn;
    private String quoteNameFr;
    private String quoteNameEn;
    private String quoteRoleFr;
    private String quoteRoleEn;
    private String facebookUrl;
    private String linkedinUrl;
    private String youtubeUrl;
    private NewsStatus status = NewsStatus.DRAFT;
}
