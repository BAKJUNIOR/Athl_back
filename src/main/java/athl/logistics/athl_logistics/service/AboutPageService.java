package athl.logistics.athl_logistics.service;

import athl.logistics.athl_logistics.service.dto.AboutPageDTO;

public interface AboutPageService {
    AboutPageDTO get();
    AboutPageDTO update(AboutPageDTO dto);
}
