package athl.logistics.athl_logistics.service.dto;

import athl.logistics.athl_logistics.models.AboutWorkforceStat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AboutWorkforceStatDTO {
    private String labelFr;
    private String labelEn;
    private double value;
    private int decimals;
    private String suffix;

    public AboutWorkforceStatDTO(AboutWorkforceStat entity) {
        this.labelFr = entity.getLabelFr();
        this.labelEn = entity.getLabelEn();
        this.value = entity.getValue();
        this.decimals = entity.getDecimals();
        this.suffix = entity.getSuffix();
    }
}
