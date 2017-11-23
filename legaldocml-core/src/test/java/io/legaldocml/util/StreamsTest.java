package io.legaldocml.util;

import io.legaldocml.test.LoggerInstancePostProcessor;
import io.legaldocml.test.Tests;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(LoggerInstancePostProcessor.class)
public class StreamsTest {

    @Test
    public void testUtilClass() throws Exception {
        Tests.assertUtilClassIsWellDefined(Streams.class);
    }

    @Test
    public void testNull() {
        List<String> list = null;
        assertEquals(Optional.empty(), Streams.stream(list).findFirst());
    }

    @Test
    public void testNotNull() {
        List<String> list = new ArrayList<>();
        list.add("hello");
        assertEquals(Optional.of("hello"), Streams.stream(list).findFirst());
    }

}
