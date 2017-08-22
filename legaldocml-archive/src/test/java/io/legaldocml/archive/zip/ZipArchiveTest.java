package io.legaldocml.archive.zip;

import io.legaldocml.akn.AkomaNtoso;
import io.legaldocml.akn.element.Debate;
import io.legaldocml.archive.Archive;
import io.legaldocml.archive.ArchiveException;
import io.legaldocml.archive.ArchiveFactory;
import io.legaldocml.business.AknIdentifier;
import io.legaldocml.business.BusinessProvider;
import io.legaldocml.business.MediaType;
import io.legaldocml.test.PathForTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ZipArchiveTest {

    private static final String FILE = System.getProperty("java.io.tmpdir") + "/test.zip";
    private static final String FILE2 = System.getProperty("java.io.tmpdir") + "/test2.zip";

    private BusinessProvider provider;

    @Before
    public void setup() throws IOException {
        Path path = Paths.get(FILE);
        if (Files.exists(path)) {
            Files.delete(Paths.get(FILE));
        }
        path = Paths.get(FILE2);
        if (Files.exists(path)) {
            Files.delete(Paths.get(FILE2));
        }
        provider = BusinessProvider.businessProvider("default");
    }

    @Test
    public void testCreateRead() throws Exception {

        AkomaNtoso<Debate> akn = ReaderHelper.read(PathForTest.path("/xml/v3/cl_Sesion56_2.xml"));
        try (Archive archive = ArchiveFactory.writeOnly("zip", provider, Paths.get(FILE))) {
            archive.put(akn);
        }
        try (Archive archive = ArchiveFactory.readOnly("zip", Paths.get(FILE))) {
            akn = archive.get(AknIdentifier.extract(provider, akn));
            Assert.assertEquals("/akn/cl/debate/recurso/2006/1076048/es@20120505/!main.xml", AknIdentifier.extract(provider, akn).manifestation());
        }


    }


    @Test
    public void testCreateRead2() throws Exception {

        Path path = PathForTest.path("/xml/v3/cl_Sesion56_2.xml");
        AknIdentifier identifier = provider.newAknIdentifier("123", "456", "789");

        try (Archive archive = ArchiveFactory.writeOnly("zip", provider, Paths.get(FILE))) {
            archive.put(identifier, MediaType.LEGALDOCML, path);
        }

        try (Archive archive = ArchiveFactory.readOnly("zip", Paths.get(FILE))) {
            AkomaNtoso<Debate> akn = archive.get(identifier);
            Assert.assertEquals("/akn/cl/debate/recurso/2006/1076048/es@20120505/!main.xml", AknIdentifier.extract(provider, akn).manifestation());
        }

    }

    @Test
    public void testMeta() throws Exception {
        AkomaNtoso<Debate> akn = ReaderHelper.read(PathForTest.path("/xml/v3/cl_Sesion56_2.xml"));
        try (Archive archive = ArchiveFactory.writeOnly("zip", provider, Paths.get(FILE))) {
            archive.put(akn);
            Assert.assertEquals(1, archive.getMeta().stream().count());
        }

        try (Archive archive = ArchiveFactory.readOnly("zip", Paths.get(FILE))) {
            Assert.assertEquals(1, archive.getMeta().stream().count());
            archive.getMeta().stream().forEach(resource -> {
                Assert.assertEquals(MediaType.LEGALDOCML, resource.getMediaType());
                Assert.assertEquals(AknIdentifier.extract(provider, akn), resource.getAknIdentifier());
            });
        }

    }


    @Test
    public void testWriteOnlyAddSameResourceTwice() throws Exception {
        AkomaNtoso<Debate> akn = ReaderHelper.read(PathForTest.path("/xml/v3/cl_Sesion56_2.xml"));

        try (Archive archive = ArchiveFactory.writeOnly("zip", provider, Paths.get(FILE))) {
            archive.put(akn);
            try {
                archive.put(akn);
                Assert.fail();
            } catch (ArchiveException cause) {
                Assert.assertEquals(ArchiveException.Type.WRITE_ALREADY_EXISTS, cause.getType());
            }

        }
    }

    @Test
    public void testReadWrite() throws Exception {
        AkomaNtoso<Debate> akn = ReaderHelper.read(PathForTest.path("/xml/v3/cl_Sesion56_2.xml"));
        try (Archive archive = ArchiveFactory.writeOnly("zip", provider, Paths.get(FILE))) {
            archive.put(akn);
        }

        try (Archive archive = ArchiveFactory.readWrite("zip", provider, Paths.get(FILE), Paths.get(FILE2))) {
            archive.put(akn);
        }

    }


}
