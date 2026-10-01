package athl.logistics.athl_logistics.service;

import athl.logistics.athl_logistics.service.dto.TestimonialDTO;
import athl.logistics.athl_logistics.service.dto.TestimonialUpsertDTO;

import java.util.List;

public interface TestimonialService {
    List<TestimonialDTO> list();

    TestimonialDTO create(TestimonialUpsertDTO dto);

    TestimonialDTO update(Long id, TestimonialUpsertDTO dto);

    void delete(Long id);
}
