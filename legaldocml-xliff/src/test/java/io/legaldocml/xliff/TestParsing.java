package io.legaldocml.xliff;

import io.legaldocml.io.impl.XmlChannelReader;
import io.legaldocml.io.impl.XmlChannelWriter;
import io.legaldocml.module.akn.v3.XmlChannelWriterV3;
import io.legaldocml.test.PathForTest;
import io.legaldocml.test.XmlUnitHelper;
import io.legaldocml.util.Buffers;
import io.legaldocml.xliff.element.Xliff;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.EnumSet;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.READ;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;
import static java.nio.file.StandardOpenOption.WRITE;

class TestParsing {

    @Test
    void testRead() throws IOException {
        readAndWriteAndCompare("/ex.5.7.xlf");
        //readAndWriteAndCompare("/ex.23.2.xlf");
    }

    static void readAndWriteAndCompare(String source)throws IOException {

        Xliff xliff = read(PathForTest.path(source));

        Path expectedPath = Paths.get(System.getProperty("java.io.tmpdir"), "xliff-test-001.xml");
        XmlChannelWriter writer = new XmlChannelWriterV3();
        writer.setChannel(FileChannel.open(expectedPath, EnumSet.of(CREATE, TRUNCATE_EXISTING, WRITE)));
        xliff.write(writer);
        writer.flush();

        try (InputStream expected = Files.newInputStream(expectedPath)) {
            try (InputStream actual = Files.newInputStream(PathForTest.path("/ex.5.7.xlf"))) {
                XmlUnitHelper.compare(actual, expected);
            }
        }

    }

    static Xliff read(Path path) throws IOException {
        Xliff xliff = new Xliff();
        XmlChannelReader reader = new XmlChannelReader();
        MappedByteBuffer out = null;
        try (FileChannel channel = FileChannel.open(path, READ)) {

            // Mapping a file into memory
            out = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
            reader.setBuffer(out);
            reader.nextStartOrEndElement();

            xliff.read(reader);

        } finally {
            if (out != null) {
                Buffers.releaseDirectByteBuffer(out);
            }
        }

        return xliff;
    }
}
