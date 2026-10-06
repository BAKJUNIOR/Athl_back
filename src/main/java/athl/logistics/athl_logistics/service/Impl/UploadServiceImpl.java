package athl.logistics.athl_logistics.service.Impl;

import athl.logistics.athl_logistics.service.UploadService;
import athl.logistics.athl_logistics.service.dto.UploadResponseDTO;
import athl.logistics.athl_logistics.web.errors.AccountResourceException;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.unit.DataSize;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Locale;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class UploadServiceImpl implements UploadService {

    private final Cloudinary cloudinary;

    // Limite Cloudinary pour les images et PDF ; les vidéos ne sont bornées que par
    // spring.servlet.multipart.max-file-size (Cloudinary les accepte bien plus lourdes).
    @Value("${app.upload.max-size}")
    private DataSize maxSize;

    @Override
    public UploadResponseDTO upload(MultipartFile file, String folder) {
        if (file == null || file.isEmpty()) {
            throw new AccountResourceException("Fichier manquant.", HttpStatus.BAD_REQUEST);
        }

        String resourceType = resourceTypeFor(file.getContentType());
        if (!"video".equals(resourceType) && file.getSize() > maxSize.toBytes()) {
            throw new AccountResourceException(
                    "Fichier trop lourd (" + formatSize(file.getSize()) + "). Taille maximum : " + formatSize(maxSize.toBytes()) + ".",
                    HttpStatus.CONTENT_TOO_LARGE);
        }

        try {
            Map<?, ?> options = ObjectUtils.asMap(
                    "resource_type", resourceType,
                    "folder", folder
            );
            @SuppressWarnings("unchecked")
            Map<String, Object> result = cloudinary.uploader().upload(file.getBytes(), options);
            return new UploadResponseDTO((String) result.get("secure_url"), (String) result.get("public_id"));
        } catch (IOException e) {
            log.error("Échec de l'upload Cloudinary", e);
            throw new AccountResourceException("Erreur lors de l'envoi du fichier.", HttpStatus.BAD_GATEWAY);
        } catch (RuntimeException e) {
            log.warn("Upload refusé par Cloudinary : {}", e.getMessage());
            String message = e.getMessage() != null && e.getMessage().contains("File size too large")
                    ? "Fichier trop lourd (" + formatSize(file.getSize()) + ") : refusé par l'hébergeur d'images."
                    : "L'hébergeur d'images a refusé ce fichier. Vérifiez son format et sa taille.";
            throw new AccountResourceException(message, HttpStatus.UNPROCESSABLE_CONTENT);
        }
    }

    /** Taille lisible, ex. 13180635 -> "12,6 Mo". */
    static String formatSize(long bytes) {
        if (bytes < 1024 * 1024) {
            return String.format(Locale.FRANCE, "%d Ko", Math.max(1, bytes / 1024));
        }
        return String.format(Locale.FRANCE, "%.1f Mo", bytes / (1024.0 * 1024.0)).replace(",0 Mo", " Mo");
    }

    private String resourceTypeFor(String contentType) {
        if (contentType == null) return "raw";
        if (contentType.equals("application/pdf")) return "raw";
        if (contentType.startsWith("video/")) return "video";
        if (contentType.startsWith("image/")) return "image";
        return "raw";
    }
}
