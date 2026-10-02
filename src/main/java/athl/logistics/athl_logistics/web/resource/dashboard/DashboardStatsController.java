package athl.logistics.athl_logistics.web.resource.dashboard;

import athl.logistics.athl_logistics.service.DashboardStatsService;
import athl.logistics.athl_logistics.service.dto.DashboardSummaryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// Chiffres agrégés du tableau de bord du BO — admin uniquement (voir SecurityConfig).
@RestController
@RequiredArgsConstructor
public class DashboardStatsController {

    private final DashboardStatsService dashboardStatsService;

    @GetMapping("/api/v1/dashboard/summary")
    public ResponseEntity<DashboardSummaryDTO> getSummary() {
        return ResponseEntity.ok(dashboardStatsService.getSummary());
    }
}
