package athl.logistics.athl_logistics.service.dto;

import athl.logistics.athl_logistics.models.ContactPageContent;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ContactPageDTO {
    private String heroEyebrowFr;
    private String heroEyebrowEn;
    private String heroTitleFr;
    private String heroTitleEn;
    private String heroSubtitleFr;
    private String heroSubtitleEn;

    private String writeToUsEyebrowFr;
    private String writeToUsEyebrowEn;

    private String infoEyebrowFr;
    private String infoEyebrowEn;
    private String infoHeadingFr;
    private String infoHeadingEn;

    private String phoneTitleFr;
    private String phoneTitleEn;
    private String phoneNoteFr;
    private String phoneNoteEn;

    private String emailTitleFr;
    private String emailTitleEn;

    private String addressTitleFr;
    private String addressTitleEn;
    private String addressNoteFr;
    private String addressNoteEn;

    public ContactPageDTO(ContactPageContent entity) {
        this.heroEyebrowFr = entity.getHeroEyebrowFr();
        this.heroEyebrowEn = entity.getHeroEyebrowEn();
        this.heroTitleFr = entity.getHeroTitleFr();
        this.heroTitleEn = entity.getHeroTitleEn();
        this.heroSubtitleFr = entity.getHeroSubtitleFr();
        this.heroSubtitleEn = entity.getHeroSubtitleEn();

        this.writeToUsEyebrowFr = entity.getWriteToUsEyebrowFr();
        this.writeToUsEyebrowEn = entity.getWriteToUsEyebrowEn();

        this.infoEyebrowFr = entity.getInfoEyebrowFr();
        this.infoEyebrowEn = entity.getInfoEyebrowEn();
        this.infoHeadingFr = entity.getInfoHeadingFr();
        this.infoHeadingEn = entity.getInfoHeadingEn();

        this.phoneTitleFr = entity.getPhoneTitleFr();
        this.phoneTitleEn = entity.getPhoneTitleEn();
        this.phoneNoteFr = entity.getPhoneNoteFr();
        this.phoneNoteEn = entity.getPhoneNoteEn();

        this.emailTitleFr = entity.getEmailTitleFr();
        this.emailTitleEn = entity.getEmailTitleEn();

        this.addressTitleFr = entity.getAddressTitleFr();
        this.addressTitleEn = entity.getAddressTitleEn();
        this.addressNoteFr = entity.getAddressNoteFr();
        this.addressNoteEn = entity.getAddressNoteEn();
    }
}
