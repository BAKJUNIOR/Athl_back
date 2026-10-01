package athl.logistics.athl_logistics.service;

import athl.logistics.athl_logistics.service.dto.UploadResponseDTO;
import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
    UploadResponseDTO upload(MultipartFile file, String folder);
}
