package io.legaldocml.util;

import io.legaldocml.test.Tests;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;
import java.time.OffsetDateTime;

public class DateHelperTest {

    @Test
    public void testHelper() throws Exception {
        Tests.assertUtilClassIsWellDefined(DateHelper.class);
    }

    @Test
    public void testConvert() {

        LocalDate date = LocalDate.of(2011, 3, 9);

        OffsetDateTime odt = DateHelper.convert(date);
        Assert.assertEquals(date, odt.toLocalDate());
        Assert.assertEquals(2011, odt.getYear());
        Assert.assertEquals(Month.MARCH, odt.getMonth());
        Assert.assertEquals(9, odt.getDayOfMonth());
        Assert.assertEquals(0, odt.getHour());
        Assert.assertEquals(0, odt.getMinute());
        Assert.assertEquals(0, odt.getSecond());
        Assert.assertEquals(0, odt.getNano());
    }
}
