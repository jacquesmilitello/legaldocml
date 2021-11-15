package io.legaldocml.util;

import io.legaldocml.io.XmlWriter;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.function.Consumer;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class StringWriterTemplate {

    void reserveBuffer(String value) throws IOException {
        MappedByteBuffer buffer = null;
        try {
            byte[] bytes = value.getBytes(StandardCharsets.US_ASCII);
            buffer = (MappedByteBuffer) ByteBuffer.allocateDirect(bytes.length);
            buffer.put(bytes);
            // noinspection RedundantCast
            ((java.nio.Buffer)buffer).flip();
            @SuppressWarnings("restriction")
			long addr = ((sun.nio.ch.DirectBuffer) buffer).address();

            onReserveBuffer(addr, bytes.length);


        } finally {
            if (buffer != null) {
                Buffers.releaseDirectByteBuffer(buffer);
            }
        }
    }

    protected abstract void onReserveBuffer(long addr, int length) throws IOException;

    public static void writeElement(XmlWriter writer, String element, Consumer<XmlWriter> consumer) throws IOException {
        new StringWriterTemplate() {
            @Override
            protected void onReserveBuffer(long addr, int length) throws IOException {
                writer.writeStart(addr, length);
                consumer.accept(writer);
                writer.writeEnd(addr, length);
            }
        }.reserveBuffer(element);
    }

    public static void writeAttribute(XmlWriter writer, String attributeName, char[] attributeValue) throws IOException {
        new StringWriterTemplate() {
            @Override
            protected void onReserveBuffer(long addr, int length) throws IOException {
                writer.writeAttribute(addr, length, attributeValue);
            }
        }.reserveBuffer(attributeName);
    }


}
