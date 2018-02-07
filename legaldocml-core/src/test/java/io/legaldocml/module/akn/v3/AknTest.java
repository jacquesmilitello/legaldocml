package io.legaldocml.module.akn.v3;

import io.legaldocml.akn.AkomaNtoso;
import io.legaldocml.io.XmlReaderFactory;
import io.legaldocml.io.XmlReaderFactoryProvider;
import io.legaldocml.io.impl.XmlChannelWriter;
import io.legaldocml.test.LoggerInstancePostProcessor;
import io.legaldocml.test.PathForTest;
import io.legaldocml.util.Buffers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;

import static io.legaldocml.XmlUnitHelper.compare;

@ExtendWith(LoggerInstancePostProcessor.class)
class AknTest {

    private static final XmlReaderFactory XML_READER_FACTORY = XmlReaderFactoryProvider.newXmlReaderFactory(2);

    @Test
    void testOther() throws Exception {
        test("/xml/v3/cl_Sesion56_2.xml");
        test("/xml/v3/it_senato_ddl_2013.xml");
        test("/xml/v3/uy_bill_2010-09-27.xml");
        test("/xml/v3/us_Act_2011-11-29.xml");
        test("/xml/v3/za_Judgement_2008-11-26.xml");
        test("/xml/v3/us_Title9-Chap3-eng.xml");
        test("/xml/v3/uk_pga-2014-27-enacted-data.xml");
        test("/xml/v3/ke_Debate_Bungeni_2011-06-10.xml");
        test("/xml/v3/eu_COM(2013)0619_EN-8.xml");
        test("/xml/v3/uk_pga_1998_29.xml");
        test("/xml/v3/112hr4310enr.akn.xml");
        test("/xml/v3/uk/asp-2017-1-enacted-data.akn.xml");
        test("/xml/v3/uk/ssi-2017-102-enacted-data.akn.xml");
        test("/xml/v3/uk/ssi-2017-113-enacted-data.akn.xml");
        test("/xml/v3/uk/ssi-2017-126-enacted-data.akn.xml");

    }

    public static void test(String resource) throws IOException {

        Path path = PathForTest.path(resource);
        MappedByteBuffer out = null;
        try (FileChannel channel = FileChannel.open(path, StandardOpenOption.READ)) {

            // Mapping a file into memory
            out = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());

            AkomaNtoso<?> akomaNtoso = XML_READER_FACTORY.read(out);

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

}
