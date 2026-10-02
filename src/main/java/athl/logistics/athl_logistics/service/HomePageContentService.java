package athl.logistics.athl_logistics.service;

import athl.logistics.athl_logistics.service.dto.HomePageContentDTO;

public interface HomePageContentService {
    HomePageContentDTO get();

    HomePageContentDTO update(HomePageContentDTO dto);
}
