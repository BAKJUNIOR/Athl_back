package athl.logistics.athl_logistics.service.Impl;

import athl.logistics.athl_logistics.models.Testimonial;
import athl.logistics.athl_logistics.repositories.TestimonialRepository;
import athl.logistics.athl_logistics.service.TestimonialService;
import athl.logistics.athl_logistics.service.dto.TestimonialDTO;
import athl.logistics.athl_logistics.service.dto.TestimonialUpsertDTO;
import athl.logistics.athl_logistics.web.errors.AccountResourceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TestimonialServiceImpl implements TestimonialService {

    private final TestimonialRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<TestimonialDTO> list() {
        return repository.findAllByOrderBySortOrderAsc().stream()
                .map(TestimonialDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public TestimonialDTO create(TestimonialUpsertDTO dto) {
        Testimonial entity = new Testimonial();
        applyFields(entity, dto);
        Testimonial saved = repository.save(entity);
        log.info("Témoignage créé : id={}", saved.getId());
        return new TestimonialDTO(saved);
    }

    @Override
    @Transactional
    public TestimonialDTO update(Long id, TestimonialUpsertDTO dto) {
        Testimonial entity = findEntityOrThrow(id);
        applyFields(entity, dto);
        Testimonial saved = repository.save(entity);
        log.info("Témoignage mis à jour : id={}", saved.getId());
        return new TestimonialDTO(saved);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Testimonial entity = findEntityOrThrow(id);
        repository.delete(entity);
        log.info("Témoignage supprimé : id={}", id);
    }

    private Testimonial findEntityOrThrow(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new AccountResourceException("Témoignage introuvable avec l'ID : " + id, HttpStatus.NOT_FOUND));
    }

    private void applyFields(Testimonial entity, TestimonialUpsertDTO dto) {
        entity.setName(dto.getName());
        entity.setRoleFr(dto.getRoleFr());
        entity.setRoleEn(dto.getRoleEn());
        entity.setTextFr(dto.getTextFr());
        entity.setTextEn(dto.getTextEn());
        entity.setPhoto(dto.getPhoto());
        entity.setInitials(dto.getInitials());
        entity.setSortOrder(dto.getSortOrder());
    }
}
