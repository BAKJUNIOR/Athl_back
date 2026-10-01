package athl.logistics.athl_logistics.repositories;

import athl.logistics.athl_logistics.models.Testimonial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestimonialRepository extends JpaRepository<Testimonial, Long> {
    List<Testimonial> findAllByOrderBySortOrderAsc();
}
