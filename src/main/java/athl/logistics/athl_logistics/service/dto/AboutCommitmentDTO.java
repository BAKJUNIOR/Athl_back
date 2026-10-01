package athl.logistics.athl_logistics.service.dto;

import athl.logistics.athl_logistics.models.AboutCommitment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AboutCommitmentDTO {
    private String titleFr;
    private String titleEn;
    private String textFr;
    private String textEn;

    public AboutCommitmentDTO(AboutCommitment entity) {
        this.titleFr = entity.getTitleFr();
        this.titleEn = entity.getTitleEn();
        this.textFr = entity.getTextFr();
        this.textEn = entity.getTextEn();
    }
}
