package io.legaldocml.module.akn.v3;

import io.legaldocml.ReaderHelper;
import io.legaldocml.akn.AkomaNtoso;
import io.legaldocml.akn.AkomaNtosoContext;
import io.legaldocml.test.LoggerInstancePostProcessor;
import io.legaldocml.test.PathForTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(LoggerInstancePostProcessor.class)
public class AknIdExtractTest {

    @Test
    public void testOther() throws Exception {

        AkomaNtoso<?> akomaNtoso = ReaderHelper.read(PathForTest.path("/xml/v3/cl_Sesion56_2.xml"));
        AkomaNtosoContext ctx = akomaNtoso.getContext();

        assertNotNull(ctx.getId("v_1"));


    }
}
