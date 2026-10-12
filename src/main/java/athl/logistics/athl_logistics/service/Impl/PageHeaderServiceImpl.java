package athl.logistics.athl_logistics.service.Impl;

import athl.logistics.athl_logistics.models.PageHeader;
import athl.logistics.athl_logistics.repositories.PageHeaderRepository;
import athl.logistics.athl_logistics.service.PageHeaderService;
import athl.logistics.athl_logistics.service.dto.PageHeaderDTO;
import athl.logistics.athl_logistics.web.errors.AccountResourceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PageHeaderServiceImpl implements PageHeaderService {

    /** Pages dont l'en-tête est éditable, dans l'ordre d'affichage du BO. */
    static final List<String> PAGE_KEYS = List.of("services", "projects", "news", "team", "careers", "quote", "search");

    private final PageHeaderRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<PageHeaderDTO> list() {
        return repository.findAll().stream()
                .filter(h -> PAGE_KEYS.contains(h.getPageKey()))
                .sorted(Comparator.comparingInt(h -> PAGE_KEYS.indexOf(h.getPageKey())))
                .map(PageHeaderDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<PageHeaderDTO> update(List<PageHeaderDTO> dtos) {
        if (dtos == null) {
            throw new AccountResourceException("Liste d'en-têtes manquante.", HttpStatus.BAD_REQUEST);
        }
        for (PageHeaderDTO dto : dtos) {
            if (dto.getPageKey() == null || !PAGE_KEYS.contains(dto.getPageKey())) {
                throw new AccountResourceException("Page inconnue : " + dto.getPageKey(), HttpStatus.BAD_REQUEST);
            }
            PageHeader entity = repository.findById(dto.getPageKey()).orElseGet(() -> {
                PageHeader created = new PageHeader();
                created.setPageKey(dto.getPageKey());
                return created;
            });
            entity.setEyebrowFr(clean(dto.getEyebrowFr()));
            entity.setEyebrowEn(clean(dto.getEyebrowEn()));
            entity.setTitleFr(clean(dto.getTitleFr()));
            entity.setTitleEn(clean(dto.getTitleEn()));
            entity.setSubtitleFr(clean(dto.getSubtitleFr()));
            entity.setSubtitleEn(clean(dto.getSubtitleEn()));
            repository.save(entity);
        }
        log.info("En-têtes de pages mis à jour : {}", dtos.stream().map(PageHeaderDTO::getPageKey).collect(Collectors.toList()));
        return list();
    }

    // Vide = null : le front retombe alors sur son texte par défaut.
    private static String clean(String value) {
        return value == null || value.isBlank() ? null : value.trim();
    }
}
