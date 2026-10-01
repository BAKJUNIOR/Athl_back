package athl.logistics.athl_logistics.web.resource.aboutpage;

import athl.logistics.athl_logistics.service.AboutPageService;
import athl.logistics.athl_logistics.service.dto.AboutPageDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// Contenu de la page /a-propos du site vitrine. Public en lecture (le front en a besoin pour
// afficher la page), modification réservée aux admins. Jeu de données fixe : pas de
// create/delete, un seul PUT global — voir SiteSettingsController pour le même principe.
@Slf4j
@RestController
@RequiredArgsConstructor
public class AboutPageController {

    private final AboutPageService aboutPageService;

    @GetMapping("/api/v1/about-page")
    public ResponseEntity<AboutPageDTO> get() {
        return ResponseEntity.ok(aboutPageService.get());
    }

    @PutMapping("/api/v1/about-page")
    public ResponseEntity<AboutPageDTO> update(@RequestBody AboutPageDTO dto) {
        log.debug("REST request to update about page content");
        return ResponseEntity.ok(aboutPageService.update(dto));
    }
}
