package athl.logistics.athl_logistics.service.Impl;

import athl.logistics.athl_logistics.models.SiteContact;
import athl.logistics.athl_logistics.repositories.SiteContactRepository;
import athl.logistics.athl_logistics.service.SiteSettingsService;
import athl.logistics.athl_logistics.service.dto.SiteContactDTO;
import athl.logistics.athl_logistics.web.errors.AccountResourceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SiteSettingsServiceImpl implements SiteSettingsService {

    private static final Long SITE_CONTACT_ID = 1L;

    private final SiteContactRepository siteContactRepository;

    @Override
    @Transactional(readOnly = true)
    public SiteContactDTO getSiteContact() {
        return new SiteContactDTO(findContactOrThrow());
    }

    @Override
    @Transactional
    public SiteContactDTO updateSiteContact(SiteContactDTO dto) {
        SiteContact entity = findContactOrThrow();
        entity.setPhone1(dto.getPhone1());
        entity.setPhone2(dto.getPhone2());
        entity.setPhone3(dto.getPhone3());
        entity.setAddress(dto.getAddress());
        entity.setFacebookUrl(dto.getFacebookUrl());
        entity.setYoutubeUrl(dto.getYoutubeUrl());
        entity.setInstagramUrl(dto.getInstagramUrl());
        entity.setLinkedinUrl(dto.getLinkedinUrl());
        entity.setTiktokUrl(dto.getTiktokUrl());
        entity.setContactEmail(dto.getContactEmail());
        entity.setFooterAboutFr(dto.getFooterAboutFr());
        entity.setFooterAboutEn(dto.getFooterAboutEn());
        entity.setMapLocation(dto.getMapLocation());
        SiteContact saved = siteContactRepository.save(entity);
        log.info("Coordonnées du site mises à jour");
        return new SiteContactDTO(saved);
    }

    private SiteContact findContactOrThrow() {
        return siteContactRepository.findById(SITE_CONTACT_ID)
                .orElseThrow(() -> new AccountResourceException("Coordonnées du site introuvables.", HttpStatus.NOT_FOUND));
    }
}
