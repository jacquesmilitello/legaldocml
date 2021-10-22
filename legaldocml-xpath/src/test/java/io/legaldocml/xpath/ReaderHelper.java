package io.legaldocml.xpath;

import io.legaldocml.akn.AkomaNtoso;
import io.legaldocml.akn.DocumentType;
import io.legaldocml.akn.util.XmlReaderHelper;
import io.legaldocml.io.impl.XmlChannelReader;
import io.legaldocml.module.akn.DefaultAkomaNtosoContext;
import io.legaldocml.util.Buffers;

import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public final class ReaderHelper {

    private ReaderHelper() {
    }


    public static <T extends DocumentType> AkomaNtoso<T> read(Path path) throws IOException {

        AkomaNtoso<T> akomaNtoso;

        XmlChannelReader reader = new XmlChannelReader();
        MappedByteBuffer out = null;
        try (FileChannel channel = FileChannel.open(path, StandardOpenOption.READ)) {

            // Mapping a file into memory
            out = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
            reader.setBuffer(out);
            reader.nextStartOrEndElement();

            akomaNtoso = XmlReaderHelper.createAkomaNtoso(reader, new DefaultAkomaNtosoContext());
            akomaNtoso.read(reader);

        } finally {
            if (out != null) {
                Buffers.releaseDirectByteBuffer(out);
            }
        }

        return akomaNtoso;
    }

}
