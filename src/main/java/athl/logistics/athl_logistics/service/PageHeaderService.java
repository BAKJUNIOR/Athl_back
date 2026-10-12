package athl.logistics.athl_logistics.service;

import athl.logistics.athl_logistics.service.dto.PageHeaderDTO;

import java.util.List;

public interface PageHeaderService {
    List<PageHeaderDTO> list();
    List<PageHeaderDTO> update(List<PageHeaderDTO> dtos);
}
