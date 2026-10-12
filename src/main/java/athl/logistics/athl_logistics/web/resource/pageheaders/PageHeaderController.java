package athl.logistics.athl_logistics.web.resource.pageheaders;

import athl.logistics.athl_logistics.service.PageHeaderService;
import athl.logistics.athl_logistics.service.dto.PageHeaderDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PageHeaderController {

    private final PageHeaderService pageHeaderService;

    @GetMapping("/api/v1/page-headers")
    public ResponseEntity<List<PageHeaderDTO>> list() {
        return ResponseEntity.ok(pageHeaderService.list());
    }

    @PutMapping("/api/v1/page-headers")
    public ResponseEntity<List<PageHeaderDTO>> update(@RequestBody List<PageHeaderDTO> dtos) {
        log.debug("REST request to update page headers");
        return ResponseEntity.ok(pageHeaderService.update(dtos));
    }
}
