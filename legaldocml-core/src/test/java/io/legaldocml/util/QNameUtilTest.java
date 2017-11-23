package io.legaldocml.util;

import io.legaldocml.test.LoggerInstancePostProcessor;
import io.legaldocml.test.Tests;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static io.legaldocml.util.CharArrays.immutable;
import static io.legaldocml.util.QnameUtil.localName;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
@ExtendWith(LoggerInstancePostProcessor.class)
public class QNameUtilTest {

    @Test
    public void testUtilClass() throws Exception {
        Tests.assertUtilClassIsWellDefined(QnameUtil.class);
    }

    @Test
    public void testLocalName() {
        assertTrue(localName(immutable("akn:meta")).equals("meta"));
        assertTrue(localName(immutable("meta")).equals("meta"));
    }

}