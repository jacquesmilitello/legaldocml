package io.legaldocml.akn.v3;

import io.legaldocml.akn.AkomaNtoso;
import io.legaldocml.akn.AkomaNtosoContext;
import io.legaldocml.akn.ReaderHelper;
import io.legaldocml.test.PathForTest;
import org.junit.Assert;
import org.junit.Test;

public class AknIdExtractTest {

    @Test
    public void testOther() throws Exception {

        AkomaNtoso<?> akomaNtoso = ReaderHelper.read(PathForTest.path("/xml/v3/cl_Sesion56_2.xml"));
        AkomaNtosoContext ctx = akomaNtoso.getContext();

        Assert.assertNotNull(ctx.getId("v_1"));


    }
}
