package io.legaldocml.business.util;

import io.legaldocml.test.Tests;
import org.junit.Test;

public class AknReferenceHelperTest {

    @Test
    public void testHelperClass() throws Exception {
        Tests.assertUtilClassIsWellDefined(AknReferenceHelper.class);
    }

    @Test
    public void testNullRef() {
        AknReferenceHelper.apply(null,null);
    }
}
