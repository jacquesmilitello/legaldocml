package io.legaldocml.test;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;
import java.nio.file.Path;

public class PathForTestTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testPathForFile() throws IOException {
        Path path = PathForTest.path("/log4j2.xml");
        Assert.assertTrue(path.toFile().exists());
    }

    @Test
    public void testPathForFileNotExist() throws IOException {
        thrown.expect(IOException.class);
        thrown.expectMessage("resource not found");
        Path path = PathForTest.path("/log4j3.xml");
    }
}
