package athl.logistics.athl_logistics.service;

import athl.logistics.athl_logistics.service.dto.SiteContactDTO;

public interface SiteSettingsService {
    SiteContactDTO getSiteContact();

    SiteContactDTO updateSiteContact(SiteContactDTO dto);
}
