package athl.logistics.athl_logistics.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Noms de champs alignés sur la réponse Cloudinary d'origine (secure_url, public_id) pour ne
// rien changer côté BO, qui consommait déjà directement la réponse de l'API Cloudinary.
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UploadResponseDTO {

    @JsonProperty("secure_url")
    private String secureUrl;

    @JsonProperty("public_id")
    private String publicId;
}
