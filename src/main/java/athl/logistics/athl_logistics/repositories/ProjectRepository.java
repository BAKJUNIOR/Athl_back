package athl.logistics.athl_logistics.repositories;

import athl.logistics.athl_logistics.models.Project;
import athl.logistics.athl_logistics.models.enums.ProjectStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    boolean existsBySlug(String slug);

    List<Project> findByStatusOrderBySortOrderAsc(ProjectStatus status);

    List<Project> findAllByOrderBySortOrderAsc();

    Optional<Project> findBySlugAndStatus(String slug, ProjectStatus status);
}
