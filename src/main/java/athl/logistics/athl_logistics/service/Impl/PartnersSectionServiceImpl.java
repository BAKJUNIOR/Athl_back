package athl.logistics.athl_logistics.service.Impl;

import athl.logistics.athl_logistics.models.Partner;
import athl.logistics.athl_logistics.models.PartnersSection;
import athl.logistics.athl_logistics.repositories.PartnersSectionRepository;
import athl.logistics.athl_logistics.service.PartnersSectionService;
import athl.logistics.athl_logistics.service.dto.PartnerDTO;
import athl.logistics.athl_logistics.service.dto.PartnersSectionDTO;
import athl.logistics.athl_logistics.web.errors.AccountResourceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PartnersSectionServiceImpl implements PartnersSectionService {

    private static final Long ID = 1L;

    private final PartnersSectionRepository repository;

    @Override
    @Transactional(readOnly = true)
    public PartnersSectionDTO get() {
        return new PartnersSectionDTO(findOrThrow());
    }

    @Override
    @Transactional
    public PartnersSectionDTO update(PartnersSectionDTO dto) {
        PartnersSection entity = findOrThrow();

        entity.setEyebrowFr(dto.getEyebrowFr());
        entity.setEyebrowEn(dto.getEyebrowEn());
        entity.setTitleFr(dto.getTitleFr());
        entity.setTitleEn(dto.getTitleEn());
        entity.setSubtitleFr(dto.getSubtitleFr());
        entity.setSubtitleEn(dto.getSubtitleEn());
        entity.setCtaLabelFr(dto.getCtaLabelFr());
        entity.setCtaLabelEn(dto.getCtaLabelEn());
        applyPartners(entity, dto.getPartners());

        PartnersSection saved = repository.save(entity);
        log.info("Section partenaires mise à jour");
        return new PartnersSectionDTO(saved);
    }

    private void applyPartners(PartnersSection entity, List<PartnerDTO> dtos) {
        entity.getPartners().clear();
        if (dtos == null) return;
        int order = 0;
        for (PartnerDTO d : dtos) {
            Partner p = new Partner();
            p.setSection(entity);
            p.setName(d.getName());
            p.setLogo(d.getLogo());
            p.setSortOrder(order++);
            entity.getPartners().add(p);
        }
    }

    private PartnersSection findOrThrow() {
        return repository.findById(ID)
                .orElseThrow(() -> new AccountResourceException("Section partenaires introuvable.", HttpStatus.NOT_FOUND));
    }
}
