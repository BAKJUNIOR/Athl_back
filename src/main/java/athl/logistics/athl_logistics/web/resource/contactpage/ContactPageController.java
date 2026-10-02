package athl.logistics.athl_logistics.web.resource.contactpage;

import athl.logistics.athl_logistics.service.ContactPageService;
import athl.logistics.athl_logistics.service.dto.ContactPageDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequiredArgsConstructor
public class ContactPageController {

    private final ContactPageService contactPageService;

    @GetMapping("/api/v1/contact-page")
    public ResponseEntity<ContactPageDTO> get() {
        return ResponseEntity.ok(contactPageService.get());
    }

    @PutMapping("/api/v1/contact-page")
    public ResponseEntity<ContactPageDTO> update(@RequestBody ContactPageDTO dto) {
        log.debug("REST request to update contact page content");
        return ResponseEntity.ok(contactPageService.update(dto));
    }
}
