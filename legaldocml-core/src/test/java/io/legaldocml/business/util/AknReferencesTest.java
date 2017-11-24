package io.legaldocml.business.util;

import io.legaldocml.test.Tests;
import org.junit.jupiter.api.Test;

public class AknReferencesTest {

    @Test
    public void testHelperClass() throws Exception {
        Tests.assertUtilClassIsWellDefined(AknReferences.class);
    }

    @Test
    public void testNullRef() {
        AknReferences.apply(null,null);
    }
}
