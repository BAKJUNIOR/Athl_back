package athl.logistics.athl_logistics.service.dto;

import athl.logistics.athl_logistics.models.PageHeader;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PageHeaderDTO {
    private String pageKey;
    private String eyebrowFr;
    private String eyebrowEn;
    private String titleFr;
    private String titleEn;
    private String subtitleFr;
    private String subtitleEn;

    public PageHeaderDTO(PageHeader entity) {
        this.pageKey = entity.getPageKey();
        this.eyebrowFr = entity.getEyebrowFr();
        this.eyebrowEn = entity.getEyebrowEn();
        this.titleFr = entity.getTitleFr();
        this.titleEn = entity.getTitleEn();
        this.subtitleFr = entity.getSubtitleFr();
        this.subtitleEn = entity.getSubtitleEn();
    }
}
