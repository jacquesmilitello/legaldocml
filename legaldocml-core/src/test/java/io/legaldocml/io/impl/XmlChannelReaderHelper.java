package io.legaldocml.io.impl;

import io.legaldocml.util.Buffers;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Consumer;

class XmlChannelReaderHelper {

    static void doTest(Path path, Consumer<XmlChannelReader> consumer) throws IOException {
        XmlChannelReader reader = new XmlChannelReader();
        try (FileChannel channel = FileChannel.open(path)) {
            MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
            try {
                reader.setBuffer(buffer);
                consumer.accept(reader);
            } finally {
                Buffers.releaseDirectByteBuffer(buffer);
            }
        }
    }

    static Path path(String classPathResource) {
        try {
            return Paths.get(XmlChannelReaderPiTest.class.getResource(classPathResource).toURI());
        } catch (URISyntaxException cause) {
            throw new IllegalArgumentException("Resource [" + classPathResource + "] not found", cause);
        }
    }
}
