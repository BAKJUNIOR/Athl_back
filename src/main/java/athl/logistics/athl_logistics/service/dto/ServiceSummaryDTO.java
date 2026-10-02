package athl.logistics.athl_logistics.service.dto;

import athl.logistics.athl_logistics.models.ServiceOffering;
import athl.logistics.athl_logistics.models.enums.ServiceStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;


@Data
@NoArgsConstructor
public class ServiceSummaryDTO {
    private Long id;
    private String slug;
    private String number;
    private String titleFr;
    private String titleEn;
    private String shortTitleFr;
    private String shortTitleEn;
    private String leadFr;
    private String leadEn;
    private String image;
    private String heroImage;
    private List<ServicePrestationDTO> prestations;
    private ServiceStatus status;
    private Instant updatedAt;

    public ServiceSummaryDTO(ServiceOffering entity) {
        this.id = entity.getId();
        this.slug = entity.getSlug();
        this.number = entity.getNumber();
        this.titleFr = entity.getTitleFr();
        this.titleEn = entity.getTitleEn();
        this.shortTitleFr = entity.getShortTitleFr();
        this.shortTitleEn = entity.getShortTitleEn();
        this.leadFr = entity.getLeadFr();
        this.leadEn = entity.getLeadEn();
        this.image = entity.getImage();
        this.heroImage = entity.getHeroImage();
        this.prestations = entity.getPrestations().stream().map(ServicePrestationDTO::new).collect(Collectors.toList());
        this.status = entity.getStatus();
        this.updatedAt = entity.getUpdatedAt();
    }
}
