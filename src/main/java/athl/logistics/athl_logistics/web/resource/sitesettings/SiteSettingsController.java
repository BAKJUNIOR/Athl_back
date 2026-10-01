package athl.logistics.athl_logistics.web.resource.sitesettings;

import athl.logistics.athl_logistics.service.SiteSettingsService;
import athl.logistics.athl_logistics.service.dto.SiteContactDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// Réglages du site vitrine : coordonnées/réseaux sociaux. Public en lecture (le front en a
// besoin pour le footer et la page Contact), modification réservée aux admins. Jeu de
// données fixe : pas de create/delete, un seul PUT global.
@Slf4j
@RestController
@RequiredArgsConstructor
public class SiteSettingsController {

    private final SiteSettingsService siteSettingsService;

    @GetMapping("/api/v1/site-settings/contact")
    public ResponseEntity<SiteContactDTO> getSiteContact() {
        return ResponseEntity.ok(siteSettingsService.getSiteContact());
    }

    @PutMapping("/api/v1/site-settings/contact")
    public ResponseEntity<SiteContactDTO> updateSiteContact(@RequestBody SiteContactDTO dto) {
        log.debug("REST request to update site contact: {}", dto);
        return ResponseEntity.ok(siteSettingsService.updateSiteContact(dto));
    }
}
