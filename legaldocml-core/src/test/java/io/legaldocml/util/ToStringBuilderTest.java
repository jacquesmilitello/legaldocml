package io.legaldocml.util;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
        Assertions.assertEquals("{\"test\":null}", builder.toString());

        builder = new ToStringBuilder(true);
        builder.append("test", "hello".toCharArray());
        Assertions.assertEquals("{\"test\":\"hello\"}", builder.toString());

        builder = new ToStringBuilder(false);
        builder.append("test", (char[]) null);
        Assertions.assertEquals("{}", builder.toString());
    }

    @Test
    public void testAppendString() {
        ToStringBuilder builder = new ToStringBuilder(true);
        builder.append("test", (String) null);
        Assertions.assertEquals("{\"test\":null}", builder.toString());

        builder = new ToStringBuilder(true);
        builder.append("test", "hello");
        Assertions.assertEquals("{\"test\":\"hello\"}", builder.toString());

        builder = new ToStringBuilder(false);
        builder.append("test", (String) null);
        Assertions.assertEquals("{}", builder.toString());

    }

    @Test
    public void testAppendObject() {
        ToStringBuilder builder = new ToStringBuilder(true);
        builder.append("object", (String) null);
        Assertions.assertEquals("{\"object\":null}", builder.toString());

        builder = new ToStringBuilder(true);
        builder.append("object", new Toto());
        Assertions.assertEquals("{\"object\":\"toto2\"}", builder.toString());

        builder = new ToStringBuilder(false);
        builder.append("object", (Object) null);
        Assertions.assertEquals("{}", builder.toString());

        builder = new ToStringBuilder(false);
        builder.append("object", new Toto());
        Assertions.assertEquals("{\"object\":\"toto2\"}", builder.toString());
    }

    @Test
    public void testAppendLocalDate() {
        ToStringBuilder builder = new ToStringBuilder(true);
        builder.append("date", (LocalDate) null);
        Assertions.assertEquals("{\"date\":null}", builder.toString());

        builder = new ToStringBuilder(true);
        builder.append("date", LocalDate.of(2011, 3, 9));
        Assertions.assertEquals("{\"date\":[2011,3,9]}", builder.toString());

        builder = new ToStringBuilder(true);
        builder.append("date", LocalDate.of(2011, 11, 12));
        Assertions.assertEquals("{\"date\":[2011,11,12]}", builder.toString());

        builder = new ToStringBuilder(false);
        builder.append("date", (LocalDate) null);
        Assertions.assertEquals("{}", builder.toString());

        builder = new ToStringBuilder(false);
        builder.append("date", LocalDate.of(2011, 3, 9));
        Assertions.assertEquals("{\"date\":[2011,3,9]}", builder.toString());
    }

    @Test
    public void testAppendLocalTime() {
        ToStringBuilder builder = new ToStringBuilder(true);
        builder.append("time", (LocalTime) null);
        Assertions.assertEquals("{\"time\":null}", builder.toString());

        builder = new ToStringBuilder(true);
        builder.append("time", LocalTime.of(18, 36, 7));
        Assertions.assertEquals("{\"time\":[18,36,7]}", builder.toString());

        builder = new ToStringBuilder(true);
        builder.append("time", LocalTime.of(4, 5, 12));
        Assertions.assertEquals("{\"time\":[4,5,12]}", builder.toString());

        builder = new ToStringBuilder(false);
        builder.append("time", (LocalTime) null);
        Assertions.assertEquals("{}", builder.toString());

        builder = new ToStringBuilder(false);
        builder.append("time", LocalTime.of(4, 5, 12));
        Assertions.assertEquals("{\"time\":[4,5,12]}", builder.toString());

    }

    @Test
    public void testAppendLocalDateTime() {
        ToStringBuilder builder = new ToStringBuilder(true);
        builder.append("dateTime", (LocalDateTime) null);
        Assertions.assertEquals("{\"dateTime\":null}", builder.toString());

        builder = new ToStringBuilder(true);
        builder.append("dateTime", LocalDateTime.of(2011, 3, 9, 18, 36, 7));
        Assertions.assertEquals("{\"dateTime\":[2011,3,9,18,36,7]}", builder.toString());

        builder = new ToStringBuilder(false);
        builder.append("dateTime", (LocalDateTime) null);
        Assertions.assertEquals("{}", builder.toString());

        builder = new ToStringBuilder(false);
        builder.append("dateTime", LocalDateTime.of(2011, 3, 9, 18, 36, 7));
        Assertions.assertEquals("{\"dateTime\":[2011,3,9,18,36,7]}", builder.toString());

    }

    @Test
    public void test2Append() {
        ToStringBuilder builder = new ToStringBuilder(true);
        builder.append("key1", "hello1");
        builder.append("key2", "hello2");
        Assertions.assertEquals("{\"key1\":\"hello1\",\"key2\":\"hello2\"}", builder.toString());

        builder = new ToStringBuilder(true);
        builder.append("key1", (String) null);
        builder.append("key2", "hello2");
        Assertions.assertEquals("{\"key1\":null,\"key2\":\"hello2\"}", builder.toString());
    }

    @Test
    public void testObjectConstructor() {
        Toto toto = new Toto();
        ToStringBuilder builder = new ToStringBuilder(toto, true);

        Assertions.assertEquals("{\"class\":\"Toto\",\"identityHashCode\":\"" + toto.hashCode() + "\"}", builder.toString());
        builder.append("test","toto3");

        Assertions.assertEquals("{\"class\":\"Toto\",\"identityHashCode\":\"" + toto.hashCode() + "\",\"test\":\"toto3\"}", builder.toString());
        builder.append("test","toto3");

    }

    @Test
    public void testAppendManyProperties() {
        ToStringBuilder builder = new ToStringBuilder(true);
        for (int i = 0; i < 256; i++) {
            builder.append("key1", "hello1");
            builder.append("test", "hello".toCharArray());
            builder.append("date", LocalDate.of(2011, 3, 9));
            builder.append("time", LocalTime.of(18, 36, 7));
            builder.append("dateTime", LocalDateTime.of(2011, 3, 9, 18, 36, 7));
            builder.append("object", new Toto());
        }
        builder.toString();
    }

    private static final class Toto {

        @Override
        public String toString() {
            return "toto2";
        }
    }
}
