package io.legaldocml.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Dates {

    private Dates() {
    }

    /**
     * Default localtime 00:00:00.000.
     */
    public static final LocalTime TIME_00_00_00 = LocalTime.of(0, 0, 0, 0);

    /**
     * Default Zone Offset
     */
    public static final ZoneOffset ZONE_OFFSET_0 =  ZoneOffset.ofHours(0);


    public static OffsetDateTime convert(LocalDate localDate) {
        return OffsetDateTime.of(localDate, TIME_00_00_00, ZONE_OFFSET_0);
    }
}
