package io.legaldocml.module.akn.v3;

import io.legaldocml.akn.AkomaNtoso;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.impl.XmlChannelReader;
import io.legaldocml.io.impl.XmlChannelWriter;
import io.legaldocml.test.PathForTest;
import org.junit.Assert;
import org.junit.Test;
import org.xmlunit.builder.DiffBuilder;
import org.xmlunit.diff.ComparisonControllers;
import org.xmlunit.diff.Diff;
import org.xmlunit.diff.Difference;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;
import java.util.Iterator;

public class AknTest {

    @Test
    public void testOther() throws Exception {
        test("/xml/v3/cl_Sesion56_2.xml");
        test("/xml/v3/it_senato_ddl_2013.xml");
        test("/xml/v3/uy_bill_2010-09-27.xml");
        test("/xml/v3/us_Act_2011-11-29.xml");
        test("/xml/v3/za_Judgement_2008-11-26.xml");
        test("/xml/v3/us_Title9-Chap3-eng.xml");
        test("/xml/v3/uk_pga-2014-27-enacted-data.xml");
        test("/xml/v3/ke_Debate_Bungeni_2011-06-10.xml");
        test("/xml/v3/eu_COM(2013)0619_EN-8.xml");
    }

    public static void test(String resource) throws IOException {

        Path path = PathForTest.path(resource);
        XmlChannelReader reader = new XmlChannelReader();
        MappedByteBuffer out = null;
        try (FileChannel channel = FileChannel.open(path, StandardOpenOption.READ)) {

            // Mapping a file into memory
            out = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
            reader.setBuffer(out);
            reader.nextStartOrEndElement();

            AkomaNtoso<?> akomaNtoso = new AkomaNtoso<>();
            akomaNtoso.read(reader);

            XmlChannelWriter writer = new XmlChannelWriterV3();
            writer.setChannel(FileChannel.open(Paths.get(System.getProperty("java.io.tmpdir"),"aknv2-test-001.xml"), EnumSet.of(StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE)));
            akomaNtoso.write(writer);
            writer.flush();

        } finally {
            if (out != null) {
                Buffers.releaseDirectByteBuffer(out);
            }
        }

        compare(Files.newInputStream(path), new FileInputStream(Paths.get(System.getProperty("java.io.tmpdir"),"aknv2-test-001.xml").toFile()));
    }

    public static void compare(InputStream controlXml, InputStream testXml) {

        Diff myDiff = DiffBuilder
                .compare(controlXml)
                .withTest(testXml)
                .withComparisonController(ComparisonControllers.StopWhenDifferent)
                .checkForIdentical()
                .ignoreComments()
                .ignoreWhitespace()
                .normalizeWhitespace()
                .checkForSimilar()
                .build();

        Iterator<Difference> iter = myDiff.getDifferences().iterator();
        int size = 0;
        while (iter.hasNext()) {
            System.out.println(iter.next().toString());
            size++;
        }

        Assert.assertEquals(0, size);


    }

}
