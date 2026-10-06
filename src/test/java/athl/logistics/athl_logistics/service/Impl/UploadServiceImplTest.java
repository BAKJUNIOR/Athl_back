package athl.logistics.athl_logistics.service.Impl;

import athl.logistics.athl_logistics.web.errors.AccountResourceException;
import com.cloudinary.Cloudinary;
import com.cloudinary.Uploader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.util.unit.DataSize;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UploadServiceImplTest {

    private final Cloudinary cloudinary = mock(Cloudinary.class);
    private final Uploader uploader = mock(Uploader.class);
    private final UploadServiceImpl service = new UploadServiceImpl(cloudinary);

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(service, "maxSize", DataSize.ofMegabytes(10));
        when(cloudinary.uploader()).thenReturn(uploader);
    }

    private static MockMultipartFile file(String contentType, int sizeBytes) {
        return new MockMultipartFile("file", "photo", contentType, new byte[sizeBytes]);
    }

    @Test
    void imageOverLimitIsRejectedBeforeCallingCloudinary() throws Exception {
        assertThatThrownBy(() -> service.upload(file("image/jpeg", 13_180_635), "projects"))
                .isInstanceOf(AccountResourceException.class)
                .hasMessage("Fichier trop lourd (12,6 Mo). Taille maximum : 10 Mo.")
                .extracting("status").isEqualTo(HttpStatus.CONTENT_TOO_LARGE);
        verify(uploader, never()).upload(any(), any());
    }

    @Test
    void videoOverImageLimitIsStillSent() throws Exception {
        when(uploader.upload(any(), any())).thenReturn(Map.of("secure_url", "https://res.cloudinary.com/v.mp4"));

        assertThat(service.upload(file("video/mp4", 15 * 1024 * 1024), "popups").getSecureUrl())
                .isEqualTo("https://res.cloudinary.com/v.mp4");
    }

    @Test
    void cloudinaryRefusalBecomesReadableError() throws Exception {
        when(uploader.upload(any(), any()))
                .thenThrow(new RuntimeException("File size too large. Got 9000000. Maximum is 8000000."));

        assertThatThrownBy(() -> service.upload(file("image/png", 9_000_000), "news"))
                .isInstanceOf(AccountResourceException.class)
                .hasMessage("Fichier trop lourd (8,6 Mo) : refusé par l'hébergeur d'images.")
                .extracting("status").isEqualTo(HttpStatus.UNPROCESSABLE_CONTENT);
    }

    @Test
    void formatsSizes() {
        assertThat(UploadServiceImpl.formatSize(10 * 1024 * 1024)).isEqualTo("10 Mo");
        assertThat(UploadServiceImpl.formatSize(500 * 1024)).isEqualTo("500 Ko");
    }
}
