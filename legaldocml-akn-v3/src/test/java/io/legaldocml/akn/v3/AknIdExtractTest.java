package io.legaldocml.akn.v3;

import io.legaldocml.akn.AkomaNtoso;
import io.legaldocml.akn.AkomaNtosoContext;
import io.legaldocml.akn.ReaderHelper;
import org.junit.Assert;
import org.junit.Test;

import java.nio.file.Paths;

public class AknIdExtractTest {

    @Test
    public void testOther() throws Exception {

        AkomaNtoso<?> akomaNtoso = ReaderHelper.read(Paths.get(AknTest.class.getResource("/xml/cl_Sesion56_2.xml").toURI()));
        AkomaNtosoContext ctx = akomaNtoso.getContext();

        Assert.assertNotNull(ctx.getId("v_1"));


    }
}
