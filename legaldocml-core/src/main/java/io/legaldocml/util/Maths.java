package io.legaldocml.util;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Maths {

    private static final int[] TABLE = {0, 1, 2, 2, 3, 3, 4, 5, 5, 6, 7, 7, 8, 8, 9, 0};

    private Maths() {
    }

    public static int mul10(int value) {
        return (value << 3) + (value << 1);
    }

    /**
     * {@see http://www.hackersdelight.org/divcMore.pdf}
     */
    public static int div10(int n) {
        int q, r;
        n = n + (n >> 31 & 9);
        q = (n >> 1) + (n >> 2);
        q = q + (q >> 4);
        q = q + (q >> 8);
        q = q + (q >> 16);
        q = q >> 3;
        r = n - ((q << 3) + (q << 1));
        return q + ((r + 6) >> 4);
    }

    public static int unsignedDiv10(int n) {
        int q, r;
        q = (n >> 1) + (n >> 2);
        q = q + (q >> 4);
        q = q + (q >> 8);
        q = q + (q >> 16);
        q = q >> 3;
        r = n - ((q << 3) + (q << 1));
        return q + ((r + 6) >> 4);
    }

    /**
     * {@see http://www.hackersdelight.org/divcMore.pdf}
     */
    public static int div100(int n) {
        int q, r;
        n = n + (n >> 31 & 99);
        q = (n >> 1) + (n >> 3) + (n >> 6) - (n >> 10) +
                (n >> 12) + (n >> 13) - (n >> 16);
        q = q + (q >> 20);
        q = q >> 6;
        r = n - q * 100;
        return q + ((r + 28) >> 7);
    }

    /**
     * {@see http://www.hackersdelight.org/divcMore.pdf}
     */
    public static int unsignedDiv100(int n) {
        int q, r;
        q = (n >> 1) + (n >> 3) + (n >> 6) - (n >> 10) +
                (n >> 12) + (n >> 13) - (n >> 16);
        q = q + (q >> 20);
        q = q >> 6;
        r = n - q * 100;
        return q + ((r + 28) >> 7);
    }

    /**
     * {@see http://www.hackersdelight.org/divcMore.pdf}
     */
    public static int unsignedDiv1000(int n) {
        int q, r, t;
        t = (n >> 7) + (n >> 8) + (n >> 12);
        q = (n >> 1) + t + (n >> 15) + (t >> 11) + (t >> 14);
        q = q >> 9;
        r = n - q * 1000;
        return q + ((r + 24) >> 10);
    }

}
