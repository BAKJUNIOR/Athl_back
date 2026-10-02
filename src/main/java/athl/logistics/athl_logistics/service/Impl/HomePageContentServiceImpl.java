package athl.logistics.athl_logistics.service.Impl;

import athl.logistics.athl_logistics.models.HomePageContent;
import athl.logistics.athl_logistics.repositories.HomePageContentRepository;
import athl.logistics.athl_logistics.service.HomePageContentService;
import athl.logistics.athl_logistics.service.dto.HomePageContentDTO;
import athl.logistics.athl_logistics.web.errors.AccountResourceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class HomePageContentServiceImpl implements HomePageContentService {

    private static final Long ID = 1L;

    private final HomePageContentRepository repository;

    @Override
    @Transactional(readOnly = true)
    public HomePageContentDTO get() {
        return new HomePageContentDTO(findOrThrow());
    }

    @Override
    @Transactional
    public HomePageContentDTO update(HomePageContentDTO dto) {
        HomePageContent entity = findOrThrow();

        entity.setHeroTitleLine1Fr(dto.getHeroTitleLine1Fr());
        entity.setHeroTitleLine1En(dto.getHeroTitleLine1En());
        entity.setHeroTitleLine2Fr(dto.getHeroTitleLine2Fr());
        entity.setHeroTitleLine2En(dto.getHeroTitleLine2En());
        entity.setHeroSubtitleFr(dto.getHeroSubtitleFr());
        entity.setHeroSubtitleEn(dto.getHeroSubtitleEn());
        entity.getHeroImages().clear();
        if (dto.getHeroImages() != null) {
            entity.getHeroImages().addAll(dto.getHeroImages());
        }

        entity.setPillarConstructionLeadFr(dto.getPillarConstructionLeadFr());
        entity.setPillarConstructionLeadEn(dto.getPillarConstructionLeadEn());
        entity.setPillarConstructionImage(dto.getPillarConstructionImage());

        entity.setPillarMobilityLeadFr(dto.getPillarMobilityLeadFr());
        entity.setPillarMobilityLeadEn(dto.getPillarMobilityLeadEn());
        entity.setPillarMobilityImage(dto.getPillarMobilityImage());

        entity.setPillarImportLeadFr(dto.getPillarImportLeadFr());
        entity.setPillarImportLeadEn(dto.getPillarImportLeadEn());
        entity.setPillarImportImage(dto.getPillarImportImage());

        HomePageContent saved = repository.save(entity);
        log.info("Contenu de la page d'accueil mis à jour");
        return new HomePageContentDTO(saved);
    }

    private HomePageContent findOrThrow() {
        return repository.findById(ID)
                .orElseThrow(() -> new AccountResourceException("Contenu de la page d'accueil introuvable.", HttpStatus.NOT_FOUND));
    }
}
