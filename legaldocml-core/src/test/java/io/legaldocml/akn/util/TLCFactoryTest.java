package io.legaldocml.akn.util;

import io.legaldocml.test.LoggerInstancePostProcessor;
import io.legaldocml.test.Tests;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(LoggerInstancePostProcessor.class)
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
