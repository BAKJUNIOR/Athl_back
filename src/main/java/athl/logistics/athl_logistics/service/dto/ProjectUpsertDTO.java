package athl.logistics.athl_logistics.service.dto;

import athl.logistics.athl_logistics.models.enums.ProjectStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

// Payload créer/modifier un projet — pas de slug ni d'id : le slug est généré une seule fois
// par le backend à la création et n'est plus jamais modifiable depuis le BO (voir Project).
@Data
@NoArgsConstructor
public class ProjectUpsertDTO {

    @NotNull(message = "Le métier est requis")
    private Long serviceId;

    @NotBlank(message = "Le titre en français est requis")
    private String titleFr;

    private String titleEn;
    private String locationFr;
    private String locationEn;
    private String typologyFr;
    private String typologyEn;
    private String year;
    private String descriptionFr;
    private String descriptionEn;
    private String image;
    private List<String> gallery = new ArrayList<>();
    private boolean featured;
    private int sortOrder;
    private ProjectStatus status = ProjectStatus.DRAFT;
}
