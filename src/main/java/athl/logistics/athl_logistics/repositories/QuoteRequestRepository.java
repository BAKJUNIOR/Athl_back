package athl.logistics.athl_logistics.repositories;

import athl.logistics.athl_logistics.models.QuoteRequest;
import athl.logistics.athl_logistics.models.enums.SubmissionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuoteRequestRepository extends JpaRepository<QuoteRequest, Long> {
    List<QuoteRequest> findAllByOrderByReceivedAtDesc();

    long countByStatus(SubmissionStatus status);
}
