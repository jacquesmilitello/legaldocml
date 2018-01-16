package io.legaldocml.business.guid;

import io.legaldocml.akn.type.NoWhiteSpace;
import io.legaldocml.unsafe.UnsafeString;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
final class TimestampGuidGenerator implements GuidGenerator {

    /**
     * Size of id.
     */
    public static final int SIZE = 35;

    /**
     * Counter
     */
    private final AtomicInteger counter = new AtomicInteger(1);

    /**
     * Simple random
     */
    private static final Random RANDOM = new Random();

    /**
     * {@inheritDoc}
     */
    @Override
    public NoWhiteSpace generate() {
        LocalDateTime ts = LocalDateTime.now(Clock.systemUTC());

        final char[] guid = new char[SIZE];
        formatYYMMDD(ts, guid);
        guid[8] = '-';
        formatHHMMSSsss(ts, guid);
        guid[18] = '-';
        formatInteger(this.counter.getAndIncrement(), 19, 9, guid);
        guid[28] = '-';
        formatInteger(Math.abs(RANDOM.nextInt() % 1000000), 29, 6, guid);
        return NoWhiteSpace.valueOf(guid);
    }

    private void formatInteger(int integer, int offset, int len, char[] guid) {
        char[] v = UnsafeString.getChars(Integer.toString(integer));
        for (int i = 0; i < len - v.length; i++) {
            guid[offset + i] = '0';
        }
        System.arraycopy(v, 0, guid, offset + len - v.length, v.length);
    }

    private void formatYYMMDD(LocalDateTime ts, char[] guid) {
        int t = ts.getYear();
        guid[3] = (char) (48 + (t % 10));
        guid[2] = (char) (48 + (t / 10 % 10));
        guid[1] = (char) (48 + (t / 100 % 10));
        guid[0] = (char) (48 + (t / 1000));
        t = ts.getMonthValue();
        if (t > 9) {
            guid[4] = '1';
        } else {
            guid[4] = '0';
        }
        guid[5] = (char) (48 + (t % 10));
        t = ts.getDayOfMonth();
        if (t > 9) {
            guid[6] = '1';
        } else {
            guid[6] = '0';
        }
        guid[7] = (char) (48 + (t % 10));
    }

    private void formatHHMMSSsss(LocalDateTime ts, char[] guid) {
        int t = ts.getHour();
        if (t > 9) {
            guid[9] = (char) (48 + (t / 10));
        } else {
            guid[9] = '0';
        }
        guid[10] = (char) (48 + (t % 10));
        t = ts.getMinute();
        guid[11] = (char) (48 + (t / 10));
        guid[12] = (char) (48 + (t % 10));
        t = ts.getSecond();
        guid[13] = (char) (48 + (t / 10));
        guid[14] = (char) (48 + (t % 10));
        t = ts.getNano();
        t /= 1000000;
        if (t > 99) {
            guid[15] = (char) (48 + (t / 100));
        } else {
            guid[15] = '0';
        }
        t %= 100;
        if (t > 9) {
            guid[16] = (char) (48 + (t / 10));
        } else {
            guid[16] = '0';
        }
        guid[17] = (char) (48 + (t % 10));

    }

}