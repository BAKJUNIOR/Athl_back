package athl.logistics.athl_logistics.web.resource.partners;

import athl.logistics.athl_logistics.service.PartnersSectionService;
import athl.logistics.athl_logistics.service.dto.PartnersSectionDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// Bandeau "Nos partenaires" du site vitrine. Public en lecture, modification réservée aux
// admins. Jeu de données fixe : pas de create/delete, un seul PUT global — voir
// AboutPageController pour le même principe.
@Slf4j
@RestController
@RequiredArgsConstructor
public class PartnersSectionController {

    private final PartnersSectionService partnersSectionService;

    @GetMapping("/api/v1/partners")
    public ResponseEntity<PartnersSectionDTO> get() {
        return ResponseEntity.ok(partnersSectionService.get());
    }

    @PutMapping("/api/v1/partners")
    public ResponseEntity<PartnersSectionDTO> update(@RequestBody PartnersSectionDTO dto) {
        log.debug("REST request to update partners section");
        return ResponseEntity.ok(partnersSectionService.update(dto));
    }
}
