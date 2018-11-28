package io.legaldocml.business.util;

import io.legaldocml.test.Tests;
import org.junit.jupiter.api.Test;

class AknReferencesTest {

    @Test
    void testHelperClass() throws Exception {
        Tests.assertUtilClassIsWellDefined(AknReferences.class);
    }

    @Test
    void testNullRef() {
        AknReferences.apply(null,null);
    }
}
