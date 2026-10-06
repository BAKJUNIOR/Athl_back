package athl.logistics.athl_logistics.service.Impl;

import athl.logistics.athl_logistics.models.AboutCommitment;
import athl.logistics.athl_logistics.models.AboutGroundRole;
import athl.logistics.athl_logistics.models.AboutPageContent;
import athl.logistics.athl_logistics.models.AboutPillar;
import athl.logistics.athl_logistics.models.AboutValue;
import athl.logistics.athl_logistics.models.AboutWorkforceStat;
import athl.logistics.athl_logistics.models.AboutWorkforceTab;
import athl.logistics.athl_logistics.repositories.AboutPageContentRepository;
import athl.logistics.athl_logistics.service.AboutPageService;
import athl.logistics.athl_logistics.service.dto.AboutCommitmentDTO;
import athl.logistics.athl_logistics.service.dto.AboutGroundRoleDTO;
import athl.logistics.athl_logistics.service.dto.AboutPageDTO;
import athl.logistics.athl_logistics.service.dto.AboutPillarDTO;
import athl.logistics.athl_logistics.service.dto.AboutValueDTO;
import athl.logistics.athl_logistics.service.dto.AboutWorkforceStatDTO;
import athl.logistics.athl_logistics.service.dto.AboutWorkforceTabDTO;
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
public class AboutPageServiceImpl implements AboutPageService {

    private static final Long ID = 1L;

    private final AboutPageContentRepository repository;

    @Override
    @Transactional(readOnly = true)
    public AboutPageDTO get() {
        return new AboutPageDTO(findOrThrow());
    }

    @Override
    @Transactional
    public AboutPageDTO update(AboutPageDTO dto) {
        AboutPageContent entity = findOrThrow();

        entity.setWorkforceEyebrowFr(dto.getWorkforceEyebrowFr());
        entity.setWorkforceEyebrowEn(dto.getWorkforceEyebrowEn());
        entity.setWorkforceTitleFr(dto.getWorkforceTitleFr());
        entity.setWorkforceTitleEn(dto.getWorkforceTitleEn());
        entity.setWorkforceLeadFr(dto.getWorkforceLeadFr());
        entity.setWorkforceLeadEn(dto.getWorkforceLeadEn());
        applyWorkforceTabs(entity, dto.getWorkforceTabs());

        entity.setHeroEyebrowFr(dto.getHeroEyebrowFr());
        entity.setHeroEyebrowEn(dto.getHeroEyebrowEn());
        entity.setHeroTitleFr(dto.getHeroTitleFr());
        entity.setHeroTitleEn(dto.getHeroTitleEn());
        entity.setHeroLeadFr(dto.getHeroLeadFr());
        entity.setHeroLeadEn(dto.getHeroLeadEn());

        entity.setPillarsEyebrowFr(dto.getPillarsEyebrowFr());
        entity.setPillarsEyebrowEn(dto.getPillarsEyebrowEn());
        entity.setPillarsTitleFr(dto.getPillarsTitleFr());
        entity.setPillarsTitleEn(dto.getPillarsTitleEn());
        entity.setPillarsLeadFr(dto.getPillarsLeadFr());
        entity.setPillarsLeadEn(dto.getPillarsLeadEn());
        applyPillars(entity, dto.getPillars());

        entity.setTeamEyebrowFr(dto.getTeamEyebrowFr());
        entity.setTeamEyebrowEn(dto.getTeamEyebrowEn());
        entity.setTeamTitleFr(dto.getTeamTitleFr());
        entity.setTeamTitleEn(dto.getTeamTitleEn());
        entity.setTeamLeadFr(dto.getTeamLeadFr());
        entity.setTeamLeadEn(dto.getTeamLeadEn());

        entity.setGroundTitleFr(dto.getGroundTitleFr());
        entity.setGroundTitleEn(dto.getGroundTitleEn());
        entity.setGroundLeadFr(dto.getGroundLeadFr());
        entity.setGroundLeadEn(dto.getGroundLeadEn());
        entity.setGroundCtaLabelFr(dto.getGroundCtaLabelFr());
        entity.setGroundCtaLabelEn(dto.getGroundCtaLabelEn());
        entity.getGroundImages().clear();
        if (dto.getGroundImages() != null) {
            entity.getGroundImages().addAll(dto.getGroundImages());
        }
        applyGroundRoles(entity, dto.getGroundRoles());
        applyCommitments(entity, dto.getCommitments());

        entity.setValuesTitleFr(dto.getValuesTitleFr());
        entity.setValuesTitleEn(dto.getValuesTitleEn());
        entity.setValuesBackgroundImage(dto.getValuesBackgroundImage());
        applyValues(entity, dto.getValues());

        AboutPageContent saved = repository.save(entity);
        log.info("Contenu de la page À propos mis à jour");
        return new AboutPageDTO(saved);
    }

    private void applyWorkforceTabs(AboutPageContent entity, List<AboutWorkforceTabDTO> dtos) {
        entity.getWorkforceTabs().clear();
        if (dtos == null) return;
        int order = 0;
        for (AboutWorkforceTabDTO d : dtos) {
            AboutWorkforceTab t = new AboutWorkforceTab();
            t.setAboutPage(entity);
            t.setNumber(d.getNumber());
            t.setTitleFr(d.getTitleFr());
            t.setTitleEn(d.getTitleEn());
            t.setImage(d.getImage());
            t.setHeroImage(d.getHeroImage());
            t.setVideoUrl(d.getVideoUrl() == null || d.getVideoUrl().isBlank() ? null : d.getVideoUrl().trim());
            t.setLeadFr(d.getLeadFr());
            t.setLeadEn(d.getLeadEn());
            t.setBullet1Fr(d.getBullet1Fr());
            t.setBullet1En(d.getBullet1En());
            t.setBullet2Fr(d.getBullet2Fr());
            t.setBullet2En(d.getBullet2En());
            t.setBullet3Fr(d.getBullet3Fr());
            t.setBullet3En(d.getBullet3En());
            t.setBullet4Fr(d.getBullet4Fr());
            t.setBullet4En(d.getBullet4En());
            t.setSortOrder(order++);
            applyWorkforceStats(t, d.getStats());
            entity.getWorkforceTabs().add(t);
        }
    }

    private void applyWorkforceStats(AboutWorkforceTab tab, List<AboutWorkforceStatDTO> dtos) {
        tab.getStats().clear();
        if (dtos == null) return;
        int order = 0;
        for (AboutWorkforceStatDTO d : dtos) {
            AboutWorkforceStat s = new AboutWorkforceStat();
            s.setTab(tab);
            s.setLabelFr(d.getLabelFr());
            s.setLabelEn(d.getLabelEn());
            s.setValue(d.getValue());
            s.setDecimals(d.getDecimals());
            s.setSuffix(d.getSuffix());
            s.setSortOrder(order++);
            tab.getStats().add(s);
        }
    }

    private void applyPillars(AboutPageContent entity, List<AboutPillarDTO> dtos) {
        entity.getPillars().clear();
        if (dtos == null) return;
        int order = 0;
        for (AboutPillarDTO d : dtos) {
            AboutPillar p = new AboutPillar();
            p.setAboutPage(entity);
            p.setTitleFr(d.getTitleFr());
            p.setTitleEn(d.getTitleEn());
            p.setImage(d.getImage());
            p.setBullet1Fr(d.getBullet1Fr());
            p.setBullet1En(d.getBullet1En());
            p.setBullet2Fr(d.getBullet2Fr());
            p.setBullet2En(d.getBullet2En());
            p.setBullet3Fr(d.getBullet3Fr());
            p.setBullet3En(d.getBullet3En());
            p.setBullet4Fr(d.getBullet4Fr());
            p.setBullet4En(d.getBullet4En());
            p.setSortOrder(order++);
            entity.getPillars().add(p);
        }
    }

    private void applyGroundRoles(AboutPageContent entity, List<AboutGroundRoleDTO> dtos) {
        entity.getGroundRoles().clear();
        if (dtos == null) return;
        int order = 0;
        for (AboutGroundRoleDTO d : dtos) {
            AboutGroundRole r = new AboutGroundRole();
            r.setAboutPage(entity);
            r.setLabelFr(d.getLabelFr());
            r.setLabelEn(d.getLabelEn());
            r.setSortOrder(order++);
            entity.getGroundRoles().add(r);
        }
    }

    private void applyCommitments(AboutPageContent entity, List<AboutCommitmentDTO> dtos) {
        entity.getCommitments().clear();
        if (dtos == null) return;
        int order = 0;
        for (AboutCommitmentDTO d : dtos) {
            AboutCommitment c = new AboutCommitment();
            c.setAboutPage(entity);
            c.setTitleFr(d.getTitleFr());
            c.setTitleEn(d.getTitleEn());
            c.setTextFr(d.getTextFr());
            c.setTextEn(d.getTextEn());
            c.setSortOrder(order++);
            entity.getCommitments().add(c);
        }
    }

    private void applyValues(AboutPageContent entity, List<AboutValueDTO> dtos) {
        entity.getValues().clear();
        if (dtos == null) return;
        int order = 0;
        for (AboutValueDTO d : dtos) {
            AboutValue v = new AboutValue();
            v.setAboutPage(entity);
            v.setLabelFr(d.getLabelFr());
            v.setLabelEn(d.getLabelEn());
            v.setTextFr(d.getTextFr());
            v.setTextEn(d.getTextEn());
            v.setSortOrder(order++);
            entity.getValues().add(v);
        }
    }

    private AboutPageContent findOrThrow() {
        return repository.findById(ID)
                .orElseThrow(() -> new AccountResourceException("Contenu de la page À propos introuvable.", HttpStatus.NOT_FOUND));
    }
}
