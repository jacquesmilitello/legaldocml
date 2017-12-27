package io.legaldocml.util;

import io.legaldocml.test.LoggerInstancePostProcessor;
import io.legaldocml.test.Tests;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.LocalDate;
import java.time.Month;
import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(LoggerInstancePostProcessor.class)
public class DatesTest {

    @Test
    public void testHelper() throws Exception {
        Tests.assertUtilClassIsWellDefined(Dates.class);
    }

    @Test
    public void testConvert() {

        LocalDate date = LocalDate.of(2011, 3, 9);

        OffsetDateTime odt = Dates.convert(date);
        assertEquals(date, odt.toLocalDate());
        assertEquals(2011, odt.getYear());
        assertEquals(Month.MARCH, odt.getMonth());
        assertEquals(9, odt.getDayOfMonth());
        assertEquals(0, odt.getHour());
        assertEquals(0, odt.getMinute());
        assertEquals(0, odt.getSecond());
        assertEquals(0, odt.getNano());
    }
}
