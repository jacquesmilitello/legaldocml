package io.legaldocml.util;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public class ToStringBuilderTest {

    @Test
    public void testAppendCharArray() {
        ToStringBuilder builder = new ToStringBuilder(true);
        builder.append("test", (char[]) null);
        Assert.assertEquals("{\"test\":null}", builder.toString());

        builder = new ToStringBuilder(true);
        builder.append("test", "hello".toCharArray());
        Assert.assertEquals("{\"test\":\"hello\"}", builder.toString());

    }

    @Test
    public void testAppendString() {
        ToStringBuilder builder = new ToStringBuilder(true);
        builder.append("test", (String) null);
        Assert.assertEquals("{\"test\":null}", builder.toString());

        builder = new ToStringBuilder(true);
        builder.append("test", "hello");
        Assert.assertEquals("{\"test\":\"hello\"}", builder.toString());

    }

    @Test
    public void testAppendObject() {
        ToStringBuilder builder = new ToStringBuilder(true);
        builder.append("object", (String) null);
        Assert.assertEquals("{\"object\":null}", builder.toString());

        builder = new ToStringBuilder(true);
        builder.append("object", new Toto());
        Assert.assertEquals("{\"object\":\"toto2\"}", builder.toString());

    }

    @Test
    public void testAppendLocalDate() {
        ToStringBuilder builder = new ToStringBuilder(true);
        builder.append("date", (LocalDate) null);
        Assert.assertEquals("{\"date\":null}", builder.toString());

        builder = new ToStringBuilder(true);
        builder.append("date", LocalDate.of(2011,3,9));
        Assert.assertEquals("{\"date\":[2011,03,09]}", builder.toString());

    }

    @Test
    public void testAppendLocalTime() {
        ToStringBuilder builder = new ToStringBuilder(true);
        builder.append("time", (LocalTime) null);
        Assert.assertEquals("{\"time\":null}", builder.toString());

        builder = new ToStringBuilder(true);
        builder.append("time", LocalTime.of(18,36,7));
        Assert.assertEquals("{\"time\":[18,36,07]}", builder.toString());

    }

    @Test
    public void testAppendLocalDateTime() {
        ToStringBuilder builder = new ToStringBuilder(true);
        builder.append("dateTime", (LocalDateTime) null);
        Assert.assertEquals("{\"dateTime\":null}", builder.toString());

        builder = new ToStringBuilder(true);
        builder.append("dateTime", LocalDateTime.of(2011,03,9, 18,36,7));
        Assert.assertEquals("{\"dateTime\":[2011,03,09,18,36,07]}", builder.toString());

    }

    @Test
    public void test2Append() {
        ToStringBuilder builder = new ToStringBuilder(true);
        builder.append("key1","hello1");
        builder.append("key2","hello2");
        Assert.assertEquals("{\"key1\":\"hello1\",\"key2\":\"hello2\"}", builder.toString());

        builder = new ToStringBuilder(true);
        builder.append("key1",(String)null);
        builder.append("key2","hello2");
        Assert.assertEquals("{\"key1\":null,\"key2\":\"hello2\"}", builder.toString());
    }


    private static final class Toto {

        @Override
        public String toString() {
            return "toto2";
        }
    }
}
