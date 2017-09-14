package io.legaldocml.test;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
@RunWith(SonarJUnit4ClassRunner.class)
public class TestsTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testOk() throws Exception {
        Tests.assertUtilClassIsWellDefined(Test01.class);
        Tests.assertUtilClassIsWellDefined(Tests.class);
    }

    @Test
    public void testClassNotFinal() throws Exception {
        thrown.expect(AssertionError.class);
        thrown.expectMessage("final");
        Tests.assertUtilClassIsWellDefined(Test02.class);

    }

    @Test
    public void testClassPublicAndConstructorPublic() throws Exception {
        thrown.expect(AssertionError.class);
        thrown.expectMessage("constructor");
        Tests.assertUtilClassIsWellDefined(Test03.class);
    }

    @Test
    public void testMethodNotStatic() throws Exception {
        thrown.expect(AssertionError.class);
        thrown.expectMessage("non-static");
        Tests.assertUtilClassIsWellDefined(Test04.class);
    }

    private static final class Test01 {
        private Test01() {
        }
    }

    private static class Test02 {
    }

    @SuppressWarnings("ALL")
    public final static class Test03 {
    }

    private static final class Test04 {
        private Test04() {
        }

        public void hello() {
        }
    }
}
