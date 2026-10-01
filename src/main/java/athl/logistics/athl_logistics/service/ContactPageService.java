package athl.logistics.athl_logistics.service;

import athl.logistics.athl_logistics.service.dto.ContactPageDTO;

public interface ContactPageService {
    ContactPageDTO get();
    ContactPageDTO update(ContactPageDTO dto);
}
