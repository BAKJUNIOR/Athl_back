package athl.logistics.athl_logistics.service;

import athl.logistics.athl_logistics.service.dto.NewsArticleDTO;
import athl.logistics.athl_logistics.service.dto.NewsArticleSummaryDTO;
import athl.logistics.athl_logistics.service.dto.NewsArticleUpsertDTO;

import java.util.List;

public interface NewsArticleService {
    /** Publiées uniquement pour un appelant anonyme, tout (brouillons inclus) pour un admin authentifié. */
    List<NewsArticleSummaryDTO> list();

    NewsArticleDTO getById(Long id);

    /** Public : fiche détail /actualites/:slug, publiées uniquement. */
    NewsArticleDTO getBySlug(String slug);

    NewsArticleDTO create(NewsArticleUpsertDTO dto);

    NewsArticleDTO update(Long id, NewsArticleUpsertDTO dto);

    NewsArticleDTO publish(Long id);

    NewsArticleDTO unpublish(Long id);

    void delete(Long id);
}
