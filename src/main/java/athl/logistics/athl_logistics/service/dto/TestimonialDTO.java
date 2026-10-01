package athl.logistics.athl_logistics.service.dto;

import athl.logistics.athl_logistics.models.Testimonial;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
public class TestimonialDTO {
    private Long id;
    private String name;
    private String roleFr;
    private String roleEn;
    private String textFr;
    private String textEn;
    private String photo;
    private String initials;
    private int sortOrder;
    private Instant updatedAt;

    public TestimonialDTO(Testimonial entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.roleFr = entity.getRoleFr();
        this.roleEn = entity.getRoleEn();
        this.textFr = entity.getTextFr();
        this.textEn = entity.getTextEn();
        this.photo = entity.getPhoto();
        this.initials = entity.getInitials();
        this.sortOrder = entity.getSortOrder();
        this.updatedAt = entity.getUpdatedAt();
    }
}
