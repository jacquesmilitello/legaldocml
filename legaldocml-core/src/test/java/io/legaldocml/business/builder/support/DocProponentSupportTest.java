package io.legaldocml.business.builder.support;

import io.legaldocml.akn.element.DocProponent;
import io.legaldocml.akn.element.P;
import io.legaldocml.akn.type.NoWhiteSpace;
import io.legaldocml.business.builder.element.InlineTypeBuilder;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doCallRealMethod;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public class DocProponentSupportTest extends SupportBuilderTestCase<DocProponentSupport<P>,P> {

    @Test
    public void testEmpty() throws IOException {
        doCallRealMethod().when(mock).docProponent(Mockito.any());
        doCallRealMethod().when(mock).docProponent(Mockito.nullable(Consumer.class),Mockito.any());
        mock.docProponent();
        assertEquals("<p><docProponent/></p>", write());
    }

    @Test
    public void testWithText() throws IOException {
        doCallRealMethod().when(mock).docProponent(Mockito.any());
        doCallRealMethod().when(mock).docProponent(Mockito.nullable(Consumer.class),Mockito.any());
        InlineTypeBuilder<DocProponent> builder = mock.docProponent();
        builder.text("hello");
        assertEquals("<p><docProponent>hello</docProponent></p>", write());
    }

    @Test
    public void testWithConsumer() throws IOException {
        doCallRealMethod().when(mock).docProponent(Mockito.any());
        doCallRealMethod().when(mock).docProponent(Mockito.any(),Mockito.any());
        InlineTypeBuilder<DocProponent> builder = mock.docProponent( dc -> dc.setEid(NoWhiteSpace.valueOf("eid_1")));
        builder.text("hello");
        assertEquals("<p><docProponent eId=\"eid_1\">hello</docProponent></p>", write());
    }

}
