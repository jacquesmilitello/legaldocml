package io.legaldocml.util;

import io.legaldocml.test.SonarJUnit4ClassRunner;
import io.legaldocml.test.Tests;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SonarJUnit4ClassRunner.class)
public class StreamsTest {

    @Test
    public void testUtilClass() throws Exception {
        Tests.assertUtilClassIsWellDefined(Streams.class);
    }

    @Test
    public void testNull() {
        List<String> list = null;
        Assert.assertEquals(Optional.empty(), Streams.stream(list).findFirst());
    }

    @Test
    public void testNotNull() {
        List<String> list = new ArrayList<>();
        list.add("hello");
        Assert.assertEquals(Optional.of("hello"), Streams.stream(list).findFirst());
    }

}
