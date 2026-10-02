package athl.logistics.athl_logistics.service.Impl;

import athl.logistics.athl_logistics.models.NewsArticle;
import athl.logistics.athl_logistics.models.enums.NewsStatus;
import athl.logistics.athl_logistics.repositories.NewsArticleRepository;
import athl.logistics.athl_logistics.service.NewsArticleService;
import athl.logistics.athl_logistics.service.dto.NewsArticleDTO;
import athl.logistics.athl_logistics.service.dto.NewsArticleSummaryDTO;
import athl.logistics.athl_logistics.service.dto.NewsArticleUpsertDTO;
import athl.logistics.athl_logistics.web.errors.AccountResourceException;
import com.github.slugify.Slugify;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class NewsArticleServiceImpl implements NewsArticleService {

    private final NewsArticleRepository repository;
    private final Slugify slugify = Slugify.builder().build();

    @Override
    @Transactional(readOnly = true)
    public List<NewsArticleSummaryDTO> list() {
        List<NewsArticle> articles = isAdminRequest()
                ? repository.findAllByOrderByDateDesc()
                : repository.findByStatusOrderByDateDesc(NewsStatus.PUBLISHED);
        return articles.stream().map(NewsArticleSummaryDTO::new).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public NewsArticleDTO getById(Long id) {
        return new NewsArticleDTO(findEntityOrThrow(id));
    }

    @Override
    @Transactional(readOnly = true)
    public NewsArticleDTO getBySlug(String slug) {
        NewsArticle entity = repository.findBySlugAndStatus(slug, NewsStatus.PUBLISHED)
                .orElseThrow(() -> new AccountResourceException("Actualité introuvable.", HttpStatus.NOT_FOUND));
        return new NewsArticleDTO(entity);
    }

    @Override
    @Transactional
    public NewsArticleDTO create(NewsArticleUpsertDTO dto) {
        NewsArticle entity = new NewsArticle();
        applyFields(entity, dto);
        entity.setSlug(generateUniqueSlug(dto.getTitleFr()));

        NewsArticle saved = repository.save(entity);
        log.info("Actualité créée : id={}, slug={}", saved.getId(), saved.getSlug());
        return new NewsArticleDTO(saved);
    }

    @Override
    @Transactional
    public NewsArticleDTO update(Long id, NewsArticleUpsertDTO dto) {
        NewsArticle entity = findEntityOrThrow(id);
        applyFields(entity, dto);
        // Le slug est figé à la création et n'est jamais régénéré, même si le titre change,
        // pour ne pas casser les liens déjà partagés d'un article publié.

        NewsArticle saved = repository.save(entity);
        log.info("Actualité mise à jour : id={}", saved.getId());
        return new NewsArticleDTO(saved);
    }

    @Override
    @Transactional
    public NewsArticleDTO publish(Long id) {
        NewsArticle entity = findEntityOrThrow(id);
        entity.setStatus(NewsStatus.PUBLISHED);
        return new NewsArticleDTO(repository.save(entity));
    }

    @Override
    @Transactional
    public NewsArticleDTO unpublish(Long id) {
        NewsArticle entity = findEntityOrThrow(id);
        entity.setStatus(NewsStatus.DRAFT);
        return new NewsArticleDTO(repository.save(entity));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        NewsArticle entity = findEntityOrThrow(id);
        repository.delete(entity);
        log.info("Actualité supprimée : id={}", id);
    }

    private NewsArticle findEntityOrThrow(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new AccountResourceException("Actualité introuvable avec l'ID : " + id, HttpStatus.NOT_FOUND));
    }

    private void applyFields(NewsArticle entity, NewsArticleUpsertDTO dto) {
        entity.setImage(dto.getImage());
        entity.setDate(dto.getDate());
        entity.setFeatured(dto.isFeatured());
        entity.setCategoryFr(dto.getCategoryFr());
        entity.setCategoryEn(dto.getCategoryEn());
        entity.setTitleFr(dto.getTitleFr());
        entity.setTitleEn(dto.getTitleEn());
        entity.setExcerptFr(dto.getExcerptFr());
        entity.setExcerptEn(dto.getExcerptEn());
        entity.setBodyFr(dto.getBodyFr());
        entity.setBodyEn(dto.getBodyEn());
        entity.setQuoteTextFr(dto.getQuoteTextFr());
        entity.setQuoteTextEn(dto.getQuoteTextEn());
        entity.setQuoteNameFr(dto.getQuoteNameFr());
        entity.setQuoteNameEn(dto.getQuoteNameEn());
        entity.setQuoteRoleFr(dto.getQuoteRoleFr());
        entity.setQuoteRoleEn(dto.getQuoteRoleEn());
        entity.setFacebookUrl(dto.getFacebookUrl());
        entity.setLinkedinUrl(dto.getLinkedinUrl());
        entity.setYoutubeUrl(dto.getYoutubeUrl());
        entity.setStatus(dto.getStatus() != null ? dto.getStatus() : NewsStatus.DRAFT);
    }

    /**
     * Slug propre, unique en base, avec suffixe numérique en cas de collision — même logique
     * que ProjectServiceImpl/ServiceOfferingServiceImpl.
     */
    private String generateUniqueSlug(String titleFr) {
        String base = slugify.slugify(titleFr);
        String candidate = base;
        int suffix = 2;
        while (repository.existsBySlug(candidate)) {
            candidate = base + "-" + suffix;
            suffix++;
        }
        return candidate;
    }

    private boolean isAdminRequest() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) return false;
        return auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(a -> a.equals("ROLE_SUPER_ADMIN") || a.equals("ROLE_ADMIN"));
    }
}
