package io.legaldocml.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
@ExtendWith(LoggerInstancePostProcessor.class)
public class PathForTestTest {

    @Test
    public void testPath() throws IOException {
        IOException exception = Assertions.assertThrows(IOException.class, () -> PathForTest.path("fake"));
        Assertions.assertTrue(exception.getMessage().contains("fake"));
    }

}
