package athl.logistics.athl_logistics.service.Impl;

import athl.logistics.athl_logistics.models.Project;
import athl.logistics.athl_logistics.models.ServiceOffering;
import athl.logistics.athl_logistics.models.enums.ProjectStatus;
import athl.logistics.athl_logistics.repositories.ProjectRepository;
import athl.logistics.athl_logistics.repositories.ServiceOfferingRepository;
import athl.logistics.athl_logistics.service.ProjectService;
import athl.logistics.athl_logistics.service.dto.ProjectDTO;
import athl.logistics.athl_logistics.service.dto.ProjectSummaryDTO;
import athl.logistics.athl_logistics.service.dto.ProjectUpsertDTO;
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
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository repository;
    private final ServiceOfferingRepository serviceOfferingRepository;
    private final Slugify slugify = Slugify.builder().build();

    @Override
    @Transactional(readOnly = true)
    public List<ProjectSummaryDTO> list() {
        List<Project> projects = isAdminRequest()
                ? repository.findAllByOrderBySortOrderAsc()
                : repository.findByStatusOrderBySortOrderAsc(ProjectStatus.PUBLISHED);
        return projects.stream().map(ProjectSummaryDTO::new).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ProjectDTO getById(Long id) {
        return new ProjectDTO(findEntityOrThrow(id));
    }

    @Override
    @Transactional(readOnly = true)
    public ProjectDTO getBySlug(String slug) {
        Project entity = repository.findBySlugAndStatus(slug, ProjectStatus.PUBLISHED)
                .orElseThrow(() -> new AccountResourceException("Projet introuvable.", HttpStatus.NOT_FOUND));
        return new ProjectDTO(entity);
    }

    @Override
    @Transactional
    public ProjectDTO create(ProjectUpsertDTO dto) {
        Project entity = new Project();
        applyFields(entity, dto);
        entity.setSlug(generateUniqueSlug(dto.getTitleFr()));

        Project saved = repository.save(entity);
        log.info("Projet créé : id={}, slug={}", saved.getId(), saved.getSlug());
        return new ProjectDTO(saved);
    }

    @Override
    @Transactional
    public ProjectDTO update(Long id, ProjectUpsertDTO dto) {
        Project entity = findEntityOrThrow(id);
        applyFields(entity, dto);
        // Le slug est figé à la création et n'est jamais régénéré, même si le titre change,
        // pour ne pas casser les liens déjà partagés d'un projet publié.

        Project saved = repository.save(entity);
        log.info("Projet mis à jour : id={}", saved.getId());
        return new ProjectDTO(saved);
    }

    @Override
    @Transactional
    public ProjectDTO publish(Long id) {
        Project entity = findEntityOrThrow(id);
        entity.setStatus(ProjectStatus.PUBLISHED);
        return new ProjectDTO(repository.save(entity));
    }

    @Override
    @Transactional
    public ProjectDTO unpublish(Long id) {
        Project entity = findEntityOrThrow(id);
        entity.setStatus(ProjectStatus.DRAFT);
        return new ProjectDTO(repository.save(entity));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Project entity = findEntityOrThrow(id);
        repository.delete(entity);
        log.info("Projet supprimé : id={}", id);
    }

    private Project findEntityOrThrow(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new AccountResourceException("Projet introuvable avec l'ID : " + id, HttpStatus.NOT_FOUND));
    }

    private ServiceOffering findServiceOrThrow(Long serviceId) {
        return serviceOfferingRepository.findById(serviceId)
                .orElseThrow(() -> new AccountResourceException("Métier introuvable avec l'ID : " + serviceId, HttpStatus.BAD_REQUEST));
    }

    private void applyFields(Project entity, ProjectUpsertDTO dto) {
        entity.setService(findServiceOrThrow(dto.getServiceId()));
        entity.setTitleFr(dto.getTitleFr());
        entity.setTitleEn(dto.getTitleEn());
        entity.setLocationFr(dto.getLocationFr());
        entity.setLocationEn(dto.getLocationEn());
        entity.setTypologyFr(dto.getTypologyFr());
        entity.setTypologyEn(dto.getTypologyEn());
        entity.setYear(dto.getYear());
        entity.setDescriptionFr(dto.getDescriptionFr());
        entity.setDescriptionEn(dto.getDescriptionEn());
        entity.setImage(dto.getImage());
        entity.getGallery().clear();
        if (dto.getGallery() != null) {
            entity.getGallery().addAll(dto.getGallery());
        }
        entity.setFeatured(dto.isFeatured());
        entity.setSortOrder(dto.getSortOrder());
        entity.setStatus(dto.getStatus() != null ? dto.getStatus() : ProjectStatus.DRAFT);
    }

    /**
     * Slug propre, unique en base, avec suffixe numérique en cas de collision
     * (ex: "villa-les-rivages", puis "villa-les-rivages-2") — même logique que ServiceOffering.
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
