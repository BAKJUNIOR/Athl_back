package athl.logistics.athl_logistics.service.Impl;

import athl.logistics.athl_logistics.models.JobApplication;
import athl.logistics.athl_logistics.models.QuoteRequest;
import athl.logistics.athl_logistics.models.enums.JobStatus;
import athl.logistics.athl_logistics.models.enums.ServiceStatus;
import athl.logistics.athl_logistics.models.enums.SubmissionStatus;
import athl.logistics.athl_logistics.repositories.JobApplicationRepository;
import athl.logistics.athl_logistics.repositories.JobOfferRepository;
import athl.logistics.athl_logistics.repositories.QuoteRequestRepository;
import athl.logistics.athl_logistics.repositories.ServiceOfferingRepository;
import athl.logistics.athl_logistics.repositories.TeamMemberRepository;
import athl.logistics.athl_logistics.repositories.UserRepository;
import athl.logistics.athl_logistics.service.DashboardStatsService;
import athl.logistics.athl_logistics.service.dto.DashboardSummaryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardStatsServiceImpl implements DashboardStatsService {

    private final QuoteRequestRepository quoteRequestRepository;
    private final JobApplicationRepository jobApplicationRepository;
    private final ServiceOfferingRepository serviceOfferingRepository;
    private final JobOfferRepository jobOfferRepository;
    private final TeamMemberRepository teamMemberRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public DashboardSummaryDTO getSummary() {
        List<QuoteRequest> quotes = quoteRequestRepository.findAllByOrderByReceivedAtDesc();
        List<JobApplication> applications = jobApplicationRepository.findAllByOrderByReceivedAtDesc();

        YearMonth currentMonth = YearMonth.now();
        YearMonth previousMonth = currentMonth.minusMonths(1);
        int currentYear = currentMonth.getYear();

        long quotesThisMonth = countInMonth(quotes.stream().map(QuoteRequest::getReceivedAt).toList(), currentMonth);
        long quotesPrevMonth = countInMonth(quotes.stream().map(QuoteRequest::getReceivedAt).toList(), previousMonth);
        long applicationsThisMonth = countInMonth(applications.stream().map(JobApplication::getReceivedAt).toList(), currentMonth);
        long applicationsPrevMonth = countInMonth(applications.stream().map(JobApplication::getReceivedAt).toList(), previousMonth);

        long newQuotes = quotes.stream().filter(q -> q.getStatus() == SubmissionStatus.NEW).count();
        long newApplications = applications.stream().filter(a -> a.getStatus() == SubmissionStatus.NEW).count();

        return new DashboardSummaryDTO(
                quotesThisMonth,
                trendPercent(quotesThisMonth, quotesPrevMonth),
                applicationsThisMonth,
                trendPercent(applicationsThisMonth, applicationsPrevMonth),
                newQuotes,
                newApplications,
                serviceOfferingRepository.countByStatus(ServiceStatus.DRAFT),
                jobOfferRepository.countByStatus(JobStatus.PUBLISHED),
                teamMemberRepository.count(),
                userRepository.countByIsActiveTrue(),
                byMonth(quotes.stream().map(QuoteRequest::getReceivedAt).toList(), currentYear),
                byMonth(applications.stream().map(JobApplication::getReceivedAt).toList(), currentYear)
        );
    }

    private long countInMonth(List<Instant> timestamps, YearMonth month) {
        return timestamps.stream().filter(t -> YearMonth.from(toLocalDate(t)).equals(month)).count();
    }

    private Double trendPercent(long current, long previous) {
        if (previous == 0) return null;
        return Math.round(((current - previous) / (double) previous) * 1000) / 10.0;
    }

    private List<Long> byMonth(List<Instant> timestamps, int year) {
        long[] counts = new long[12];
        for (Instant t : timestamps) {
            LocalDate date = toLocalDate(t);
            if (date.getYear() == year) {
                counts[date.getMonthValue() - 1]++;
            }
        }
        List<Long> result = new ArrayList<>(12);
        for (long c : counts) result.add(c);
        return result;
    }

    private LocalDate toLocalDate(Instant instant) {
        return instant.atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
