package athl.logistics.athl_logistics.service.dto;

import athl.logistics.athl_logistics.models.AboutValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AboutValueDTO {
    private String labelFr;
    private String labelEn;
    private String textFr;
    private String textEn;

    public AboutValueDTO(AboutValue entity) {
        this.labelFr = entity.getLabelFr();
        this.labelEn = entity.getLabelEn();
        this.textFr = entity.getTextFr();
        this.textEn = entity.getTextEn();
    }
}
