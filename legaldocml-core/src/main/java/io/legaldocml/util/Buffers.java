package io.legaldocml.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(Buffers.class);

    private static final String JDK8_CLEANER = "sun.misc.Cleaner";
    private static final String JDK9_CLEANER = "jdk.internal.ref.Cleaner";


    /**
     * Sun specific mechanisms to clean up resources associated with direct byte buffers.
     */
    @SuppressWarnings("unchecked")
    private static final Class<? extends ByteBuffer> SUN_DIRECT_BUFFER = (Class<? extends ByteBuffer>) lookupClassQuietly("sun.nio.ch.DirectBuffer");

    private static final Method SUN_BUFFER_CLEANER;

    private static final Method SUN_CLEANER_CLEAN;

    static {
        Method bufferCleaner = lookupMethodQuietly(SUN_DIRECT_BUFFER, "cleaner");
        Method cleanerClean;

        if (Jvm.isJava8()) {
            cleanerClean = lookupMethodQuietly(lookupClassQuietly(JDK8_CLEANER), "clean");
        } else {
            cleanerClean = lookupMethodQuietly(lookupClassQuietly(JDK9_CLEANER), "clean");
        }

        SUN_BUFFER_CLEANER = bufferCleaner;
        SUN_CLEANER_CLEAN = cleanerClean;
    }

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
            // noinspection RedundantCast
            ((java.nio.Buffer)buffer).flip();
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
        if (SUN_DIRECT_BUFFER.isAssignableFrom(buffer.getClass())) {
            try {
                Object cleaner = SUN_BUFFER_CLEANER.invoke(buffer, (Object[]) null);
                SUN_CLEANER_CLEAN.invoke(cleaner, (Object[]) null);
            } catch (Exception cause) {
                LOGGER.warn("Failed to clean up Sun specific DirectByteBuffer. [{}]", cause.getMessage());
            }
        }
    }

    public static MappedByteBuffer direct(byte[] bytes) {
        MappedByteBuffer buffer = (MappedByteBuffer) ByteBuffer.allocateDirect(bytes.length);
        buffer.order(ByteOrder.nativeOrder());
        buffer.put(bytes);
        // noinspection RedundantCast
        ((java.nio.Buffer)buffer).flip();
        return buffer;
    }

    static Method lookupMethodQuietly(Class<?> clazz, String method) {
        try {
            return clazz.getMethod(method);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    static Class<?> lookupClassQuietly(String fcqn) {
        try {
            return Class.forName(fcqn);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}