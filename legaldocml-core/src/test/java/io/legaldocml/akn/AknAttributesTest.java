package io.legaldocml.akn;

import io.legaldocml.test.LoggerInstancePostProcessor;
import io.legaldocml.test.Tests;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(LoggerInstancePostProcessor.class)
class AknAttributesTest {

    @Test
    void testUtilClass() throws Exception {
        Tests.assertUtilClassIsWellDefined(AknAttributes.class);
    }
}
