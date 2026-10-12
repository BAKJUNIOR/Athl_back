package athl.logistics.athl_logistics.service.Impl;

import athl.logistics.athl_logistics.models.PageHeader;
import athl.logistics.athl_logistics.repositories.PageHeaderRepository;
import athl.logistics.athl_logistics.service.dto.PageHeaderDTO;
import athl.logistics.athl_logistics.web.errors.AccountResourceException;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PageHeaderServiceImplTest {

    private final PageHeaderRepository repository = mock(PageHeaderRepository.class);
    private final PageHeaderServiceImpl service = new PageHeaderServiceImpl(repository);

    private static PageHeader header(String key, String titleFr) {
        PageHeader h = new PageHeader();
        h.setPageKey(key);
        h.setTitleFr(titleFr);
        return h;
    }

    @Test
    void listFollowsBoOrderAndIgnoresUnknownKeys() {
        when(repository.findAll()).thenReturn(List.of(header("team", "Équipe"), header("obsolete", "x"), header("services", "Services")));

        assertThat(service.list()).extracting(PageHeaderDTO::getPageKey).containsExactly("services", "team");
    }

    @Test
    void updateTrimsValuesAndStoresBlankAsNull() {
        when(repository.findById("projects")).thenReturn(Optional.of(header("projects", "Ancien")));
        PageHeaderDTO dto = new PageHeaderDTO();
        dto.setPageKey("projects");
        dto.setTitleFr("  Nos réalisations ");
        dto.setSubtitleFr("   ");

        service.update(List.of(dto));

        ArgumentCaptor<PageHeader> saved = ArgumentCaptor.forClass(PageHeader.class);
        verify(repository).save(saved.capture());
        assertThat(saved.getValue().getTitleFr()).isEqualTo("Nos réalisations");
        assertThat(saved.getValue().getSubtitleFr()).isNull();
    }

    @Test
    void updateRejectsUnknownPage() {
        PageHeaderDTO dto = new PageHeaderDTO();
        dto.setPageKey("admin");

        assertThatThrownBy(() -> service.update(List.of(dto))).isInstanceOf(AccountResourceException.class);
        verify(repository, never()).save(any());
    }
}
