package athl.logistics.athl_logistics.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UploadResponseDTO {

    @JsonProperty("secure_url")
    private String secureUrl;

    @JsonProperty("public_id")
    private String publicId;
}
