package io.legaldocml.module.akn.v3;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import io.legaldocml.akn.MarkupAkomaNtosoContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import io.legaldocml.akn.AkomaNtoso;
import io.legaldocml.akn.AkomaNtosoContext;
import io.legaldocml.io.XmlProvider;
import io.legaldocml.test.LoggerInstancePostProcessor;
import io.legaldocml.test.PathForTest;

@ExtendWith(LoggerInstancePostProcessor.class)
class AknIdExtractTest {

    @Test
    void testOther() throws Exception {

        AkomaNtoso<?> akomaNtoso = XmlProvider.readerFactory().read(PathForTest.path("/xml/v3/cl_Sesion56_2.xml"));
        MarkupAkomaNtosoContext ctx = (MarkupAkomaNtosoContext) akomaNtoso.getContext();

        assertNotNull(ctx.getId("v_1"));


    }
}
