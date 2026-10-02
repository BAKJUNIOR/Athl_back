package athl.logistics.athl_logistics.web.resource.homepage;

import athl.logistics.athl_logistics.service.HomePageContentService;
import athl.logistics.athl_logistics.service.dto.HomePageContentDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequiredArgsConstructor
public class HomePageContentController {

    private final HomePageContentService homePageContentService;

    @GetMapping("/api/v1/home-page")
    public ResponseEntity<HomePageContentDTO> get() {
        return ResponseEntity.ok(homePageContentService.get());
    }

    @PutMapping("/api/v1/home-page")
    public ResponseEntity<HomePageContentDTO> update(@RequestBody HomePageContentDTO dto) {
        log.debug("REST request to update home page content");
        return ResponseEntity.ok(homePageContentService.update(dto));
    }
}
