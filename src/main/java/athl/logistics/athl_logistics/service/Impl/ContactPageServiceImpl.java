package athl.logistics.athl_logistics.service.Impl;

import athl.logistics.athl_logistics.models.ContactPageContent;
import athl.logistics.athl_logistics.repositories.ContactPageContentRepository;
import athl.logistics.athl_logistics.service.ContactPageService;
import athl.logistics.athl_logistics.service.dto.ContactPageDTO;
import athl.logistics.athl_logistics.web.errors.AccountResourceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ContactPageServiceImpl implements ContactPageService {

    private static final Long ID = 1L;

    private final ContactPageContentRepository repository;

    @Override
    @Transactional(readOnly = true)
    public ContactPageDTO get() {
        return new ContactPageDTO(findOrThrow());
    }

    @Override
    @Transactional
    public ContactPageDTO update(ContactPageDTO dto) {
        ContactPageContent entity = findOrThrow();

        entity.setHeroEyebrowFr(dto.getHeroEyebrowFr());
        entity.setHeroEyebrowEn(dto.getHeroEyebrowEn());
        entity.setHeroTitleFr(dto.getHeroTitleFr());
        entity.setHeroTitleEn(dto.getHeroTitleEn());
        entity.setHeroSubtitleFr(dto.getHeroSubtitleFr());
        entity.setHeroSubtitleEn(dto.getHeroSubtitleEn());

        entity.setWriteToUsEyebrowFr(dto.getWriteToUsEyebrowFr());
        entity.setWriteToUsEyebrowEn(dto.getWriteToUsEyebrowEn());

        entity.setInfoEyebrowFr(dto.getInfoEyebrowFr());
        entity.setInfoEyebrowEn(dto.getInfoEyebrowEn());
        entity.setInfoHeadingFr(dto.getInfoHeadingFr());
        entity.setInfoHeadingEn(dto.getInfoHeadingEn());

        entity.setPhoneTitleFr(dto.getPhoneTitleFr());
        entity.setPhoneTitleEn(dto.getPhoneTitleEn());
        entity.setPhoneNoteFr(dto.getPhoneNoteFr());
        entity.setPhoneNoteEn(dto.getPhoneNoteEn());

        entity.setEmailTitleFr(dto.getEmailTitleFr());
        entity.setEmailTitleEn(dto.getEmailTitleEn());

        entity.setAddressTitleFr(dto.getAddressTitleFr());
        entity.setAddressTitleEn(dto.getAddressTitleEn());
        entity.setAddressNoteFr(dto.getAddressNoteFr());
        entity.setAddressNoteEn(dto.getAddressNoteEn());

        ContactPageContent saved = repository.save(entity);
        log.info("Contenu de la page Contact mis à jour");
        return new ContactPageDTO(saved);
    }

    private ContactPageContent findOrThrow() {
        return repository.findById(ID)
                .orElseThrow(() -> new AccountResourceException("Contenu de la page Contact introuvable.", HttpStatus.NOT_FOUND));
    }
}
