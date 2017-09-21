package io.legaldocml.archive.zip;

import io.legaldocml.archive.Archive;
import io.legaldocml.archive.ArchiveException;
import io.legaldocml.archive.ArchiveFactory;
import io.legaldocml.business.BusinessProvider;
import io.legaldocml.test.PathForTest;
import io.legaldocml.test.SonarJUnit4ClassRunner;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import java.io.IOException;
import java.nio.file.Path;

import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsInstanceOf.instanceOf;

@RunWith(SonarJUnit4ClassRunner.class)
public class ZipArchiveReadTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testBadUri() {
        Path path = Mockito.mock(Path.class);
        thrown.expect(ArchiveException.class);
        thrown.expectCause(instanceOf(IllegalArgumentException.class));
        thrown.expect(hasProperty("type", equalTo(ArchiveException.Type.READ_OPEN)));
        ArchiveFactory.readOnly("zip", path);
    }

    @Test
    public void testZipEmpty() throws IOException {
        thrown.expect(ArchiveException.class);
        thrown.expect(hasProperty("type", equalTo(ArchiveException.Type.READ_META)));
        ArchiveFactory.readOnly("zip", PathForTest.path("/zip/empty.zip"));
    }

    @Test
    public void testPut() throws IOException {
        thrown.expect(ArchiveException.class);
        thrown.expect(hasProperty("type", equalTo(ArchiveException.Type.READ_ONLY_MODE)));
        try (Archive archive = ArchiveFactory.readOnly("zip", PathForTest.path("/zip/single.zip"))) {
            archive.put(null);
        }

    }

    @Test
    public void testPut2() throws IOException {
        thrown.expect(ArchiveException.class);
        thrown.expect(hasProperty("type", equalTo(ArchiveException.Type.READ_ONLY_MODE)));
        try (Archive archive = ArchiveFactory.readOnly("zip", PathForTest.path("/zip/single.zip"))) {
            archive.put(null, null,null);
        }

    }

    @Test
    public void testRemove() throws IOException {
        thrown.expect(ArchiveException.class);
        thrown.expect(hasProperty("type", equalTo(ArchiveException.Type.READ_ONLY_MODE)));
        try (Archive archive = ArchiveFactory.readOnly("zip", PathForTest.path("/zip/single.zip"))) {
            archive.remove(null);
        }

    }

    @Test
    public void testRaw() throws IOException {
        thrown.expect(ArchiveException.class);
        thrown.expect(hasProperty("type", equalTo(ArchiveException.Type.READ_NOT_FOUND)));
        try (Archive archive = ArchiveFactory.readOnly("zip", PathForTest.path("/zip/single.zip"))) {
            archive.raw(BusinessProvider.businessProvider("default").newAknIdentifier("1","2", "3"));
        }

    }

}
