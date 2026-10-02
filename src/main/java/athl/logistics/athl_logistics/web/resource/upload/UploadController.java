package athl.logistics.athl_logistics.web.resource.upload;

import athl.logistics.athl_logistics.service.UploadService;
import athl.logistics.athl_logistics.service.dto.UploadResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Slf4j
@RestController
@RequiredArgsConstructor
public class UploadController {

    private final UploadService uploadService;

    @PostMapping(value = "/api/v1/uploads", consumes = "multipart/form-data")
    public ResponseEntity<UploadResponseDTO> upload(
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "folder", required = false) String folder
    ) {
        log.debug("REST request to upload a file (folder={})", folder);
        return ResponseEntity.ok(uploadService.upload(file, folder));
    }
}
