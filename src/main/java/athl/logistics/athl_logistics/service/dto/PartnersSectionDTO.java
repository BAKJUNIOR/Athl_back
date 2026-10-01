package athl.logistics.athl_logistics.service.dto;

import athl.logistics.athl_logistics.models.PartnersSection;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class PartnersSectionDTO {
    private String eyebrowFr;
    private String eyebrowEn;
    private String titleFr;
    private String titleEn;
    private String subtitleFr;
    private String subtitleEn;
    private String ctaLabelFr;
    private String ctaLabelEn;
    private List<PartnerDTO> partners;

    public PartnersSectionDTO(PartnersSection entity) {
        this.eyebrowFr = entity.getEyebrowFr();
        this.eyebrowEn = entity.getEyebrowEn();
        this.titleFr = entity.getTitleFr();
        this.titleEn = entity.getTitleEn();
        this.subtitleFr = entity.getSubtitleFr();
        this.subtitleEn = entity.getSubtitleEn();
        this.ctaLabelFr = entity.getCtaLabelFr();
        this.ctaLabelEn = entity.getCtaLabelEn();
        this.partners = entity.getPartners().stream().map(PartnerDTO::new).collect(Collectors.toList());
    }
}
