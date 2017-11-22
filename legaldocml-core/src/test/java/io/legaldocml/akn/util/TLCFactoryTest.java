package io.legaldocml.akn.util;

import io.legaldocml.test.SonarJUnit4ClassRunner;
import io.legaldocml.test.Tests;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SonarJUnit4ClassRunner.class)
public class TLCFactoryTest {

    @Test
    public void testFactoryClass() throws Exception {
        Tests.assertUtilClassIsWellDefined(TLCFactory.class);
    }

    public void test() {

       //NoWhiteSpace
       // NoWhiteSpace eid, Uri href, String showAs
//      /  TLCFactory.newTLCOrganization()
    }


}
