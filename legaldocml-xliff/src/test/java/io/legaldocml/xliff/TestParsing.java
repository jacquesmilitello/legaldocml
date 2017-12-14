package io.legaldocml.xliff;

import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.impl.XmlChannelReader;
import io.legaldocml.test.PathForTest;
import io.legaldocml.xliff.element.Xliff;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class TestParsing {

    @Test
    public void testRead() throws IOException {

       Xliff xliff = read(PathForTest.path("/ex.5.7.xlf"));

    }

    public static Xliff read(Path path) throws IOException {

        Xliff xliff = new Xliff();

        XmlChannelReader reader = new XmlChannelReader();
        MappedByteBuffer out = null;
        try (FileChannel channel = FileChannel.open(path, StandardOpenOption.READ)) {

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
