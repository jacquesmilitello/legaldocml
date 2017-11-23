package io.legaldocml.akn.util;

import io.legaldocml.test.LoggerInstancePostProcessor;
import io.legaldocml.test.Tests;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(LoggerInstancePostProcessor.class)
public class XmlReaderHelperTest {

    @Test
    public void testHelper() throws Exception {
        Tests.assertUtilClassIsWellDefined(XmlReaderHelper.class);
    }
}
