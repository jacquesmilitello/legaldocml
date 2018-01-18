package io.legaldocml.util;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * Utility class for all buffers (to reduce memory foot print).
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Buffers {

    /**
     * Holder of all ByteBuffers.
     */
    private static final Map<String, ByteBuffer> REF = new HashMap<>();

    private Buffers() {
        // classe non instanciable
    }

    @SuppressWarnings("all")
    public static long address(String element) {

        ByteBuffer buffer = REF.get(element);

        if (buffer == null) {
            // create it.
            byte[] bytes = element.getBytes(StandardCharsets.US_ASCII);
            buffer = ByteBuffer.allocateDirect(bytes.length);
            buffer.put(bytes);
            buffer.flip();
            REF.put(element, buffer);
        }

        return ((sun.nio.ch.DirectBuffer) buffer).address();

    }


    @SuppressWarnings("all")
    public static long address(MappedByteBuffer buffer) {
        return ((sun.nio.ch.DirectBuffer) buffer).address();
    }

    @SuppressWarnings("restriction")
	public static void releaseDirectByteBuffer(MappedByteBuffer buffer) {
        ((sun.nio.ch.DirectBuffer) buffer).cleaner().clean();
    }

    public static MappedByteBuffer direct(byte[] bytes) {
        MappedByteBuffer buffer = (MappedByteBuffer) ByteBuffer.allocateDirect(bytes.length);
        buffer.order(ByteOrder.nativeOrder());
        buffer.put(bytes);
        buffer.flip();
        return buffer;
    }

}