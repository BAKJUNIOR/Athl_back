package athl.logistics.athl_logistics.service.dto;

import athl.logistics.athl_logistics.models.AboutPillar;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AboutPillarDTO {
    private String titleFr;
    private String titleEn;
    private String image;
    private String bullet1Fr;
    private String bullet1En;
    private String bullet2Fr;
    private String bullet2En;
    private String bullet3Fr;
    private String bullet3En;
    private String bullet4Fr;
    private String bullet4En;

    public AboutPillarDTO(AboutPillar entity) {
        this.titleFr = entity.getTitleFr();
        this.titleEn = entity.getTitleEn();
        this.image = entity.getImage();
        this.bullet1Fr = entity.getBullet1Fr();
        this.bullet1En = entity.getBullet1En();
        this.bullet2Fr = entity.getBullet2Fr();
        this.bullet2En = entity.getBullet2En();
        this.bullet3Fr = entity.getBullet3Fr();
        this.bullet3En = entity.getBullet3En();
        this.bullet4Fr = entity.getBullet4Fr();
        this.bullet4En = entity.getBullet4En();
    }
}
