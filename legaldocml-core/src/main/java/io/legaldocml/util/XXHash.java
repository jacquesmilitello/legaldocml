package io.legaldocml.util;

import io.legaldocml.unsafe.UnsafeHelper;
import sun.misc.Unsafe;

/**
 * https://github.com/Cyan4973/xxHash.<br/>
 * http://cyan4973.github.io/xxHash/
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
@SuppressWarnings("all")
final class XXHash {

    private static final Unsafe UNSAFE = UnsafeHelper.getUnsafe();

    private final static long PRIME64_1 = -7046029288634856825L;
    private final static long PRIME64_2 = -4417276706812531889L;
    private final static long PRIME64_3 = 1609587929392839161L;
    private final static long PRIME64_4 = -8796714831421723037L;
    private final static long PRIME64_5 = 2870177450012600261L;

    public long xxHash64(long seed, long adr, long len, Object o) {
        long h64 = 0;

        if (len >= 32) {
            long p = len;
            long v1 = seed + PRIME64_1 + PRIME64_2;
            long v2 = seed + PRIME64_2;
            long v3 = seed;
            long v4 = seed - PRIME64_1;

            do {
                v1 = rotateLeft31(v1 + UNSAFE.getLong(o, adr) * PRIME64_2) * PRIME64_1;
                v2 = rotateLeft31(v2 + UNSAFE.getLong(o, adr + 8) * PRIME64_2) * PRIME64_1;
                v3 = rotateLeft31(v3 + UNSAFE.getLong(o, adr + 16) * PRIME64_2) * PRIME64_1;
                v4 = rotateLeft31(v4 + UNSAFE.getLong(o, adr + 24) * PRIME64_2) * PRIME64_1;
                p -= 32;
                adr += 32;
            } while (p >= 32);

            h64 = compute(h64, v1, v2, v3, v4) + len;
            len = p;
        } else {
            h64 = seed + PRIME64_5;
            h64 += len;
        }

        while (len >= 8) {
            h64 = rotateLeft(h64 ^ (rotateLeft31(UNSAFE.getLong(o, adr) * PRIME64_2) * PRIME64_1), 27) * PRIME64_1
                    + PRIME64_4;
            len -= 8;
            adr += 8;
        }

        if (len >= 4) {
            h64 = rotateLeft(h64 ^ UNSAFE.getInt(o, adr) * PRIME64_1, 23) * PRIME64_2 + PRIME64_3;
            len -= 4;
            adr += 4;
        }

        while (len != 0) {
            h64 = rotateLeft(h64 ^ UNSAFE.getByte(o, adr++) * PRIME64_5, 11) * PRIME64_1;
            --len;
        }

        h64 = (h64 ^ (h64 >>> 33)) * PRIME64_2;
        h64 = (h64 ^ (h64 >>> 29)) * PRIME64_3;
        return h64 ^ (h64 >>> 32);

    }

    public long xxHash64(long seed, HashReader reader) {
        long h64 = 0;
        long len = reader.length();

        if (len >= 32) {
            long p = len;
            long v1 = seed + PRIME64_1 + PRIME64_2;
            long v2 = seed + PRIME64_2;
            long v3 = seed;
            long v4 = seed - PRIME64_1;

            do {
                v1 = rotateLeft31(v1 + reader.getLong() * PRIME64_2) * PRIME64_1;
                v2 = rotateLeft31(v2 + reader.getLong() * PRIME64_2) * PRIME64_1;
                v3 = rotateLeft31(v3 + reader.getLong() * PRIME64_2) * PRIME64_1;
                v4 = rotateLeft31(v4 + reader.getLong() * PRIME64_2) * PRIME64_1;
                p -= 32;
            } while (p >= 32);

            h64 = compute(h64, v1, v2, v3, v4) + len;
            len = p;
        } else {
            h64 = seed + PRIME64_5;
            h64 += len;
        }

        while (len >= 8) {
            h64 = rotateLeft(h64 ^ (rotateLeft(reader.getLong() * PRIME64_2, 31) * PRIME64_1), 27) * PRIME64_1
                    + PRIME64_4;
            len -= 8;
        }

        if (len >= 4) {
            h64 = rotateLeft(h64 ^ reader.getInt() * PRIME64_1, 23) * PRIME64_2 + PRIME64_3;
            len -= 4;
        }

        while (len != 0) {
            h64 = rotateLeft(h64 ^ reader.getByte() * PRIME64_5, 11) * PRIME64_1;
            --len;
        }

        h64 = (h64 ^ (h64 >>> 33)) * PRIME64_2;
        h64 = (h64 ^ (h64 >>> 29)) * PRIME64_3;
        return h64 ^ (h64 >>> 32);
    }

    private static long compute(long h64, long v1, long v2, long v3, long v4) {
        h64 = rotateLeft(v1, 1) + rotateLeft(v2, 7) + rotateLeft(v3, 12) + rotateLeft(v4, 18);
        h64 = (h64 ^ rotateLeft31(v1 * PRIME64_2) * PRIME64_1) * PRIME64_1 + PRIME64_4;
        h64 = (h64 ^ rotateLeft31(v2 * PRIME64_2) * PRIME64_1) * PRIME64_1 + PRIME64_4;
        h64 = (h64 ^ rotateLeft31(v3 * PRIME64_2) * PRIME64_1) * PRIME64_1 + PRIME64_4;
        h64 = (h64 ^ rotateLeft31(v4 * PRIME64_2) * PRIME64_1) * PRIME64_1 + PRIME64_4;
        return h64;
    }

    private static long rotateLeft(long i, int distance) {
        return (i << distance) | (i >>> -distance);
    }

    private static long rotateLeft31(long i) {
        return (i << 31) | (i >>> -31);
    }

}
