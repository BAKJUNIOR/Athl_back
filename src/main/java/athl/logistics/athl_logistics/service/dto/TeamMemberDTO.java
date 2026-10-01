package athl.logistics.athl_logistics.service.dto;

import athl.logistics.athl_logistics.models.TeamMember;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
public class TeamMemberDTO {
    private Long id;
    private String name;
    private String roleFr;
    private String roleEn;
    private String photo;
    private String bioFr;
    private String bioEn;
    private String quoteFr;
    private String quoteEn;
    private String initials;
    private int sortOrder;
    private Instant updatedAt;

    public TeamMemberDTO(TeamMember entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.roleFr = entity.getRoleFr();
        this.roleEn = entity.getRoleEn();
        this.photo = entity.getPhoto();
        this.bioFr = entity.getBioFr();
        this.bioEn = entity.getBioEn();
        this.quoteFr = entity.getQuoteFr();
        this.quoteEn = entity.getQuoteEn();
        this.initials = entity.getInitials();
        this.sortOrder = entity.getSortOrder();
        this.updatedAt = entity.getUpdatedAt();
    }
}
