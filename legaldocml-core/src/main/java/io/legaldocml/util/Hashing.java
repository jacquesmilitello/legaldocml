package io.legaldocml.util;

import io.legaldocml.unsafe.UnsafeHelper;
import io.legaldocml.unsafe.UnsafeString;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Hashing {

    private static final XXHash XX_HASH_INSTANCE = new XXHash();

    private Hashing() {
    }

    public static long xx(long seed, String value) {
        char[] op = UnsafeString.getChars(value);
        return XX_HASH_INSTANCE.xxHash64(seed, UnsafeHelper.CHAR_ARRAY_BASE_OFFSET, op.length * 2l, op);
    }

    public static long xx(long seed, int length, char[] value) {
        return XX_HASH_INSTANCE.xxHash64(seed, UnsafeHelper.CHAR_ARRAY_BASE_OFFSET, length * 2l, value);
    }

    public static long xx(long seed, HashReader reader) {
        return XX_HASH_INSTANCE.xxHash64(seed, reader);
    }

    public static int hashCode(char[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        int hash = array[0];
        for (int i = 1; i < array.length; i++) {
            hash = (hash << 4) + (hash << 3) + (hash << 2) + (hash << 1) + hash + array[i];
        }
        return hash;
    }
}
