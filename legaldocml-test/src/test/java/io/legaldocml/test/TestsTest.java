package io.legaldocml.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
@ExtendWith(LoggerInstancePostProcessor.class)
public class TestsTest {


    @Test
    public void testOk() throws Exception {
        Tests.assertUtilClassIsWellDefined(Test01.class);
        Tests.assertUtilClassIsWellDefined(Tests.class);
    }

    @Test
    public void testClassNotFinal() throws Exception {
        AssertionError error = assertThrows(AssertionError.class, () -> Tests.assertUtilClassIsWellDefined(Test02.class));
        Assertions.assertTrue(error.getMessage().contains("final"));
    }

    @Test
    public void testClassPublicAndConstructorPublic() throws Exception {
        AssertionError error = assertThrows(AssertionError.class, () -> Tests.assertUtilClassIsWellDefined(Test03.class));
        Assertions.assertTrue(error.getMessage().contains("constructor"));
    }

    @Test
    public void testMethodNotStatic() throws Exception {
        AssertionError error = assertThrows(AssertionError.class, () -> Tests.assertUtilClassIsWellDefined(Test04.class));
        Assertions.assertTrue(error.getMessage().contains("non-static"));
    }

    private static final class Test01 {
        private Test01() {
        }
    }

    private static class Test02 {
    }

    public final static class Test03 {
    }

    private static final class Test04 {
        private Test04() {
        }

        @SuppressWarnings("unused")
		public void hello() {
        }
    }
}
