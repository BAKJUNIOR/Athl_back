package athl.logistics.athl_logistics.repositories;

import athl.logistics.athl_logistics.models.NewsArticle;
import athl.logistics.athl_logistics.models.enums.NewsStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NewsArticleRepository extends JpaRepository<NewsArticle, Long> {
    boolean existsBySlug(String slug);

    List<NewsArticle> findByStatusOrderByDateDesc(NewsStatus status);

    List<NewsArticle> findAllByOrderByDateDesc();

    Optional<NewsArticle> findBySlugAndStatus(String slug, NewsStatus status);
}
