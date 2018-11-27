package io.legaldocml;

import io.legaldocml.akn.AkomaNtoso;
import io.legaldocml.io.XmlReaderFactory;
import io.legaldocml.io.XmlReaderFactoryProvider;
import io.legaldocml.test.PathForTest;
import io.legaldocml.util.Buffers;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.io.InputStream;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;


/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class ReaderHelper {

    private ReaderHelper() {
    }

    private static final DocumentBuilderFactory DOCUMENT_BUILDER_FACTORY = DocumentBuilderFactory.newInstance();
    private static final XmlReaderFactory XML_READER_FACTORY = XmlReaderFactoryProvider.newXmlReaderFactory(2);

    static {
        DOCUMENT_BUILDER_FACTORY.setNamespaceAware(true);
    }

    public static Document load(String classpathResource) {
        return load(ReaderHelper.class.getResourceAsStream(classpathResource));
    }

    public static Document load(InputStream stream) {
        try {
            DocumentBuilder db = DOCUMENT_BUILDER_FACTORY.newDocumentBuilder();

            return db.parse(stream);
        } catch (Exception cause) {
            throw new RuntimeException(cause);
        }
    }


    public static AkomaNtoso<?> read(String resource) throws IOException {

        Path path = PathForTest.path(resource);
        MappedByteBuffer out = null;
        try (FileChannel channel = FileChannel.open(path, StandardOpenOption.READ)) {

            // Mapping a file into memory
            out = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());

             return XML_READER_FACTORY.read(out);

        } finally {
            if (out != null) {
                Buffers.releaseDirectByteBuffer(out);
            }
        }

    }
}
