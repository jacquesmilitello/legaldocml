package io.legaldocml.archive.zip;

import io.legaldocml.archive.Archive;
import io.legaldocml.archive.ArchiveException;
import io.legaldocml.archive.ArchiveFactory;
import io.legaldocml.test.LoggerInstancePostProcessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static io.legaldocml.business.BusinessProvider.businessProvider;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(LoggerInstancePostProcessor.class)
public class ZipArchiveWriteTest {

    private static final String FILE = System.getProperty("java.io.tmpdir") + "/test-write-only.zip";


    @BeforeEach
    public void setup() throws IOException {
        Path path = Paths.get(FILE);
        if (Files.exists(path)) {
            Files.delete(Paths.get(FILE));
        }
    }

    @Test
    public void testBadPath() throws IOException {
        Path path = Mockito.mock(Path.class);
        ArchiveException exception = assertThrows(ArchiveException.class, () ->ArchiveFactory.writeOnly("zip", businessProvider("default"), path));
        assertEquals(ArchiveException.Type.WRITE_OPEN, exception.getType());
    }

    @Test
    public void testGet() throws IOException {
        try (Archive archive = ArchiveFactory.writeOnly("zip", businessProvider("default"), Paths.get(FILE))) {
            ArchiveException exception = assertThrows(ArchiveException.class, () ->archive.get(null));
            assertEquals(ArchiveException.Type.WRITE_ONLY_MODE, exception.getType());
        }
    }

    @Test
    public void testRaw() throws IOException {
        try (Archive archive = ArchiveFactory.writeOnly("zip", businessProvider("default"), Paths.get(FILE))) {
            ArchiveException exception = assertThrows(ArchiveException.class, () -> archive.raw(null));
            assertEquals(ArchiveException.Type.WRITE_ONLY_MODE, exception.getType());
        }
    }

}
