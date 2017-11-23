package io.legaldocml.archive.zip;

import io.legaldocml.archive.Archive;
import io.legaldocml.archive.ArchiveException;
import io.legaldocml.archive.ArchiveFactory;
import io.legaldocml.business.BusinessProvider;
import io.legaldocml.test.LoggerInstancePostProcessor;
import io.legaldocml.test.PathForTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(LoggerInstancePostProcessor.class)
public class ZipArchiveReadTest {

    @Test
    public void testBadUri() {
        Path path = Mockito.mock(Path.class);
        ArchiveException exception = assertThrows(ArchiveException.class, () -> ArchiveFactory.readOnly("zip", path));
        assertEquals(IllegalArgumentException.class, exception.getCause().getClass());
        assertEquals(ArchiveException.Type.READ_OPEN, exception.getType());
    }

    @Test
    public void testZipEmpty() throws IOException {
        ArchiveException exception = assertThrows(ArchiveException.class, () -> ArchiveFactory.readOnly("zip", PathForTest.path("/zip/empty.zip")));
        assertEquals(ArchiveException.Type.READ_META, exception.getType());
    }

    @Test
    public void testPut() throws IOException {
        try (Archive archive = ArchiveFactory.readOnly("zip", PathForTest.path("/zip/single.zip"))) {
            ArchiveException exception = assertThrows(ArchiveException.class, () -> archive.put(null));
            assertEquals(ArchiveException.Type.READ_ONLY_MODE, exception.getType());
        }

    }

    @Test
    public void testPut2() throws IOException {
        try (Archive archive = ArchiveFactory.readOnly("zip", PathForTest.path("/zip/single.zip"))) {
            ArchiveException exception = assertThrows(ArchiveException.class, () -> archive.put(null, null, null));
            assertEquals(ArchiveException.Type.READ_ONLY_MODE, exception.getType());
        }

    }

    @Test
    public void testRemove() throws IOException {
        try (Archive archive = ArchiveFactory.readOnly("zip", PathForTest.path("/zip/single.zip"))) {
            ArchiveException exception = assertThrows(ArchiveException.class, () -> archive.remove(null));
            assertEquals(ArchiveException.Type.READ_ONLY_MODE, exception.getType());
        }

    }

    @Test
    public void testRaw() throws IOException {
        try (Archive archive = ArchiveFactory.readOnly("zip", PathForTest.path("/zip/single.zip"))) {
            ArchiveException exception = assertThrows(ArchiveException.class, () -> archive.raw(BusinessProvider.businessProvider("default").newAknIdentifier("1", "2", "3")));
            assertEquals(ArchiveException.Type.READ_NOT_FOUND, exception.getType());
        }

    }

}
