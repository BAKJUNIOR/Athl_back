package athl.logistics.athl_logistics.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

// Chiffres agrégés du tableau de bord du BO (page d'accueil admin) — lecture seule, admin uniquement.
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashboardSummaryDTO {
    private long quotesThisMonth;
    /** Variation vs le mois précédent, en %. Null si le mois précédent n'a reçu aucune demande (comparaison non significative). */
    private Double quotesTrendPercent;

    private long applicationsThisMonth;
    private Double applicationsTrendPercent;

    private long newQuotesCount;
    private long newApplicationsCount;
    private long draftServicesCount;
    private long activeJobsCount;
    private long teamMembersCount;
    private long activeUsersCount;

    /** 12 valeurs, index 0 = janvier, année en cours. */
    private List<Long> quotesByMonth;
    private List<Long> applicationsByMonth;
}
