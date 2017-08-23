package io.legaldocml.module.akn.v3;

import io.legaldocml.ReaderHelper;
import io.legaldocml.akn.AkomaNtoso;
import io.legaldocml.akn.AkomaNtosoContext;
import io.legaldocml.test.PathForTest;
import io.legaldocml.test.SonarJUnit4ClassRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SonarJUnit4ClassRunner.class)
public class AknIdExtractTest {

    @Test
    public void testOther() throws Exception {

        AkomaNtoso<?> akomaNtoso = ReaderHelper.read(PathForTest.path("/xml/v3/cl_Sesion56_2.xml"));
        AkomaNtosoContext ctx = akomaNtoso.getContext();

        Assert.assertNotNull(ctx.getId("v_1"));


    }
}
