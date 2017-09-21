package io.legaldocml.util;

import io.legaldocml.test.SonarJUnit4ClassRunner;
import io.legaldocml.test.Tests;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
@RunWith(SonarJUnit4ClassRunner.class)
public class QNameUtilTest {

    @Test
    public void testUtilClass() throws Exception {
        Tests.assertUtilClassIsWellDefined(QnameUtil.class);
    }

    @Test
    public void testLocalName() {
        Assert.assertEquals("meta",QnameUtil.localName(CharArrays.immutable("akn:meta")));
        Assert.assertEquals("meta",QnameUtil.localName(CharArrays.immutable("meta")));
    }
}
