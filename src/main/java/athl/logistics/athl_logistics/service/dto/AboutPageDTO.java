package athl.logistics.athl_logistics.service.dto;

import athl.logistics.athl_logistics.models.AboutPageContent;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class AboutPageDTO {
    private String workforceEyebrowFr;
    private String workforceEyebrowEn;
    private String workforceTitleFr;
    private String workforceTitleEn;
    private String workforceLeadFr;
    private String workforceLeadEn;
    private List<AboutWorkforceTabDTO> workforceTabs;

    private String heroEyebrowFr;
    private String heroEyebrowEn;
    private String heroTitleFr;
    private String heroTitleEn;
    private String heroLeadFr;
    private String heroLeadEn;

    private String pillarsEyebrowFr;
    private String pillarsEyebrowEn;
    private String pillarsTitleFr;
    private String pillarsTitleEn;
    private String pillarsLeadFr;
    private String pillarsLeadEn;
    private List<AboutPillarDTO> pillars;

    private String teamEyebrowFr;
    private String teamEyebrowEn;
    private String teamTitleFr;
    private String teamTitleEn;
    private String teamLeadFr;
    private String teamLeadEn;

    private String groundTitleFr;
    private String groundTitleEn;
    private String groundLeadFr;
    private String groundLeadEn;
    private String groundCtaLabelFr;
    private String groundCtaLabelEn;
    private List<String> groundImages;
    private List<AboutGroundRoleDTO> groundRoles;
    private List<AboutCommitmentDTO> commitments;

    private String valuesTitleFr;
    private String valuesTitleEn;
    private String valuesBackgroundImage;
    private List<AboutValueDTO> values;

    public AboutPageDTO(AboutPageContent entity) {
        this.workforceEyebrowFr = entity.getWorkforceEyebrowFr();
        this.workforceEyebrowEn = entity.getWorkforceEyebrowEn();
        this.workforceTitleFr = entity.getWorkforceTitleFr();
        this.workforceTitleEn = entity.getWorkforceTitleEn();
        this.workforceLeadFr = entity.getWorkforceLeadFr();
        this.workforceLeadEn = entity.getWorkforceLeadEn();
        this.workforceTabs = entity.getWorkforceTabs().stream().map(AboutWorkforceTabDTO::new).collect(Collectors.toList());

        this.heroEyebrowFr = entity.getHeroEyebrowFr();
        this.heroEyebrowEn = entity.getHeroEyebrowEn();
        this.heroTitleFr = entity.getHeroTitleFr();
        this.heroTitleEn = entity.getHeroTitleEn();
        this.heroLeadFr = entity.getHeroLeadFr();
        this.heroLeadEn = entity.getHeroLeadEn();

        this.pillarsEyebrowFr = entity.getPillarsEyebrowFr();
        this.pillarsEyebrowEn = entity.getPillarsEyebrowEn();
        this.pillarsTitleFr = entity.getPillarsTitleFr();
        this.pillarsTitleEn = entity.getPillarsTitleEn();
        this.pillarsLeadFr = entity.getPillarsLeadFr();
        this.pillarsLeadEn = entity.getPillarsLeadEn();
        this.pillars = entity.getPillars().stream().map(AboutPillarDTO::new).collect(Collectors.toList());

        this.teamEyebrowFr = entity.getTeamEyebrowFr();
        this.teamEyebrowEn = entity.getTeamEyebrowEn();
        this.teamTitleFr = entity.getTeamTitleFr();
        this.teamTitleEn = entity.getTeamTitleEn();
        this.teamLeadFr = entity.getTeamLeadFr();
        this.teamLeadEn = entity.getTeamLeadEn();

        this.groundTitleFr = entity.getGroundTitleFr();
        this.groundTitleEn = entity.getGroundTitleEn();
        this.groundLeadFr = entity.getGroundLeadFr();
        this.groundLeadEn = entity.getGroundLeadEn();
        this.groundCtaLabelFr = entity.getGroundCtaLabelFr();
        this.groundCtaLabelEn = entity.getGroundCtaLabelEn();
        this.groundImages = entity.getGroundImages();
        this.groundRoles = entity.getGroundRoles().stream().map(AboutGroundRoleDTO::new).collect(Collectors.toList());
        this.commitments = entity.getCommitments().stream().map(AboutCommitmentDTO::new).collect(Collectors.toList());

        this.valuesTitleFr = entity.getValuesTitleFr();
        this.valuesTitleEn = entity.getValuesTitleEn();
        this.valuesBackgroundImage = entity.getValuesBackgroundImage();
        this.values = entity.getValues().stream().map(AboutValueDTO::new).collect(Collectors.toList());
    }
}
