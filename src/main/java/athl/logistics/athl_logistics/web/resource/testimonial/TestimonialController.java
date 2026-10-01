package athl.logistics.athl_logistics.web.resource.testimonial;

import athl.logistics.athl_logistics.service.TestimonialService;
import athl.logistics.athl_logistics.service.dto.TestimonialDTO;
import athl.logistics.athl_logistics.service.dto.TestimonialUpsertDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Public en lecture (carrousel de témoignages sur l'accueil), gestion réservée aux admins.
@Slf4j
@RestController
@RequestMapping("/api/v1/testimonials")
@RequiredArgsConstructor
public class TestimonialController {

    private final TestimonialService testimonialService;

    @GetMapping
    public ResponseEntity<List<TestimonialDTO>> list() {
        return ResponseEntity.ok(testimonialService.list());
    }

    @PostMapping
    public ResponseEntity<TestimonialDTO> create(@Valid @RequestBody TestimonialUpsertDTO dto) {
        log.debug("REST request to create a testimonial: {}", dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(testimonialService.create(dto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TestimonialDTO> update(@PathVariable Long id, @Valid @RequestBody TestimonialUpsertDTO dto) {
        log.debug("REST request to update testimonial ID: {}", id);
        return ResponseEntity.ok(testimonialService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.debug("REST request to delete testimonial ID: {}", id);
        testimonialService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
