package athl.logistics.athl_logistics.service.dto;

import athl.logistics.athl_logistics.models.AboutGroundRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AboutGroundRoleDTO {
    private String labelFr;
    private String labelEn;

    public AboutGroundRoleDTO(AboutGroundRole entity) {
        this.labelFr = entity.getLabelFr();
        this.labelEn = entity.getLabelEn();
    }
}
