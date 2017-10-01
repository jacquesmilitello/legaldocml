package io.legaldocml.akn.util;

import io.legaldocml.test.SonarJUnit4ClassRunner;
import io.legaldocml.test.Tests;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SonarJUnit4ClassRunner.class)
public class XmlReaderHelperTest {

    @Test
    public void testHelper() throws Exception {
        Tests.assertUtilClassIsWellDefined(XmlReaderHelper.class);
    }
}
