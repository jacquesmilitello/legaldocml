package io.legaldocml.test;

import org.junit.Test;

public class TestsTest {

    @Test
    public void test() {

        try {
            Tests.assertUtilClassIsWellDefined(Test01.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static final class Test01 {
        private Test01(){}
    }
}
