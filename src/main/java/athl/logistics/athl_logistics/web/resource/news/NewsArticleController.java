package athl.logistics.athl_logistics.web.resource.news;

import athl.logistics.athl_logistics.service.NewsArticleService;
import athl.logistics.athl_logistics.service.dto.NewsArticleDTO;
import athl.logistics.athl_logistics.service.dto.NewsArticleSummaryDTO;
import athl.logistics.athl_logistics.service.dto.NewsArticleUpsertDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/news")
@RequiredArgsConstructor
public class NewsArticleController {

    private final NewsArticleService newsArticleService;

    @GetMapping
    public ResponseEntity<List<NewsArticleSummaryDTO>> list() {
        return ResponseEntity.ok(newsArticleService.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsArticleDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(newsArticleService.getById(id));
    }

    @GetMapping("/slug/{slug}")
    public ResponseEntity<NewsArticleDTO> getBySlug(@PathVariable String slug) {
        return ResponseEntity.ok(newsArticleService.getBySlug(slug));
    }

    @PostMapping
    public ResponseEntity<NewsArticleDTO> create(@Valid @RequestBody NewsArticleUpsertDTO dto) {
        log.debug("REST request to create a news article: {}", dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newsArticleService.create(dto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<NewsArticleDTO> update(@PathVariable Long id, @Valid @RequestBody NewsArticleUpsertDTO dto) {
        log.debug("REST request to update news article ID: {}", id);
        return ResponseEntity.ok(newsArticleService.update(id, dto));
    }

    @PostMapping("/{id}/publish")
    public ResponseEntity<NewsArticleDTO> publish(@PathVariable Long id) {
        log.debug("REST request to publish news article ID: {}", id);
        return ResponseEntity.ok(newsArticleService.publish(id));
    }

    @PostMapping("/{id}/unpublish")
    public ResponseEntity<NewsArticleDTO> unpublish(@PathVariable Long id) {
        log.debug("REST request to unpublish news article ID: {}", id);
        return ResponseEntity.ok(newsArticleService.unpublish(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.debug("REST request to delete news article ID: {}", id);
        newsArticleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
