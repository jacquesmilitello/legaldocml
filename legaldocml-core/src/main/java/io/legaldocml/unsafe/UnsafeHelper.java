package io.legaldocml.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class UnsafeHelper {

    public static final long BYTE_ARRAY_BASE_OFFSET;

    public static final long CHAR_ARRAY_BASE_OFFSET;

    public static final long OBJECT_ARRAY_BASE_OFFSET;

    private static final Unsafe UNSAFE;

    static {

        final PrivilegedExceptionAction<Unsafe> action = new PrivilegedExceptionAction<Unsafe>() {
            /**
             * {@inheritDoc}
             */
            @Override
            public Unsafe run() throws NoSuchFieldException, IllegalAccessException {
                Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
                theUnsafe.setAccessible(true);
                return (Unsafe) theUnsafe.get(null);
            }
        };

        try {
            UNSAFE = AccessController.doPrivileged(action);
        } catch (PrivilegedActionException cause) {
            throw new RuntimeException("Unable to load unsafe", cause);
        }

        BYTE_ARRAY_BASE_OFFSET = UNSAFE.ARRAY_BYTE_BASE_OFFSET;
        CHAR_ARRAY_BASE_OFFSET = UNSAFE.ARRAY_CHAR_BASE_OFFSET;
        OBJECT_ARRAY_BASE_OFFSET = UNSAFE.ARRAY_OBJECT_BASE_OFFSET;
    }

    private UnsafeHelper() {
    }

    public static Unsafe getUnsafe() {
        return UNSAFE;
    }

    public static long getFieldOffset(Class<?> clazz, String field) {
        try {
            return UNSAFE.objectFieldOffset(clazz.getDeclaredField(field));
        } catch (NoSuchFieldException | SecurityException e) {
            throw new RuntimeException(e);
        }
    }

    public static long addressOf(Object o) {
        Object[] array = new Object[]{o};
        int addrSize = UNSAFE.addressSize();

        if (addrSize == 8) {
            return UNSAFE.getLong(array, OBJECT_ARRAY_BASE_OFFSET);
        }

        if (addrSize == 4) {
            return UNSAFE.getInt(array, OBJECT_ARRAY_BASE_OFFSET);
        }

        throw new Error("Unsupported address size [" + addrSize + "] !!");
    }

}