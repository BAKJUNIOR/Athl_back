package athl.logistics.athl_logistics.service.Impl;

import athl.logistics.athl_logistics.service.UploadService;
import athl.logistics.athl_logistics.service.dto.UploadResponseDTO;
import athl.logistics.athl_logistics.web.errors.AccountResourceException;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class UploadServiceImpl implements UploadService {

    private final Cloudinary cloudinary;

    @Override
    public UploadResponseDTO upload(MultipartFile file, String folder) {
        if (file == null || file.isEmpty()) {
            throw new AccountResourceException("Fichier manquant.", HttpStatus.BAD_REQUEST);
        }

        String resourceType = resourceTypeFor(file.getContentType());

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
        }
    }

    private String resourceTypeFor(String contentType) {
        if (contentType == null) return "raw";
        if (contentType.equals("application/pdf")) return "raw";
        if (contentType.startsWith("video/")) return "video";
        if (contentType.startsWith("image/")) return "image";
        return "raw";
    }
}
