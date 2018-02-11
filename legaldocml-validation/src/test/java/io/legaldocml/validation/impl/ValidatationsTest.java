package io.legaldocml.validation.impl;

import io.legaldocml.test.Tests;
import io.legaldocml.validation.impl.Validations;
import org.junit.jupiter.api.Test;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
class ValidatationsTest {

    @Test
    void testConstructor() throws Exception {
        Tests.assertUtilClassIsWellDefined(Validations.class);
    }

}
