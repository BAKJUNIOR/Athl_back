package athl.logistics.athl_logistics.service.dto;

import athl.logistics.athl_logistics.models.Partner;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartnerDTO {
    private String name;
    private String logo;

    public PartnerDTO(Partner entity) {
        this.name = entity.getName();
        this.logo = entity.getLogo();
    }
}
