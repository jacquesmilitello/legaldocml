package io.legaldocml.util;

import io.legaldocml.test.SonarJUnit4ClassRunner;
import io.legaldocml.test.Tests;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static io.legaldocml.util.CharArrays.*;
import static io.legaldocml.util.QnameUtil.localName;

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
        Assert.assertTrue(localName(immutable("akn:meta")).equals("meta"));
        Assert.assertTrue(localName(immutable("meta")).equals("meta"));
    }

}