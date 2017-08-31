package io.legaldocml.schematron;

import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.impl.XmlChannelReader;
import io.legaldocml.schematron.model.Schema;
import io.legaldocml.test.PathForTest;
import org.junit.Test;

import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class TestParsing {

    @Test
    public void testRead() throws IOException {

        Schema schema = read(PathForTest.path("/ex/examples.sch"));

    }

    public static Schema read(Path path) throws IOException {

        Schema schema = new Schema();

        XmlChannelReader reader = new XmlChannelReader();
        MappedByteBuffer out = null;
        try (FileChannel channel = FileChannel.open(path, StandardOpenOption.READ)) {

            // Mapping a file into memory
            out = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
            reader.setBuffer(out);
            reader.nextStartOrEndElement();

            schema.read(reader);

        } finally {
            if (out != null) {
                Buffers.releaseDirectByteBuffer(out);
            }
        }

        return schema;
    }
}
