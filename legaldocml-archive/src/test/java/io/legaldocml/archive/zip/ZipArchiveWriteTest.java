package io.legaldocml.archive.zip;

import io.legaldocml.archive.Archive;
import io.legaldocml.archive.ArchiveException;
import io.legaldocml.archive.ArchiveFactory;
import io.legaldocml.test.SonarJUnit4ClassRunner;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static io.legaldocml.business.BusinessProvider.businessProvider;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.hamcrest.core.IsEqual.equalTo;

@RunWith(SonarJUnit4ClassRunner.class)
public class ZipArchiveWriteTest {

    private static final String FILE = System.getProperty("java.io.tmpdir") + "/test-write-only.zip";

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setup() throws IOException {
        Path path = Paths.get(FILE);
        if (Files.exists(path)) {
            Files.delete(Paths.get(FILE));
        }
    }

    @Test
    public void testBadPath() throws IOException {
        Path path = Mockito.mock(Path.class);
        thrown.expect(ArchiveException.class);
        thrown.expect(hasProperty("type", equalTo(ArchiveException.Type.WRITE_OPEN)));
        ArchiveFactory.writeOnly("zip", businessProvider("default"), path);

    }

    @Test
    public void testGet() throws IOException {
        thrown.expect(ArchiveException.class);
        thrown.expect(hasProperty("type", equalTo(ArchiveException.Type.WRITE_ONLY_MODE)));
        try (Archive archive = ArchiveFactory.writeOnly("zip", businessProvider("default"), Paths.get(FILE))) {
            archive.get(null);
        }
    }

    @Test
    public void testRaw() throws IOException {
        thrown.expect(ArchiveException.class);
        thrown.expect(hasProperty("type", equalTo(ArchiveException.Type.WRITE_ONLY_MODE)));
        try (Archive archive = ArchiveFactory.writeOnly("zip", businessProvider("default"), Paths.get(FILE))) {
            archive.raw(null);
        }
    }

}
