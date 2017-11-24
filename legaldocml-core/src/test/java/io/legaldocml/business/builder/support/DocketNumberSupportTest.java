package io.legaldocml.business.builder.support;

import io.legaldocml.akn.element.DocketNumber;
import io.legaldocml.akn.element.P;
import io.legaldocml.akn.type.NoWhiteSpace;
import io.legaldocml.business.builder.element.InlineTypeBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.nullable;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public class DocketNumberSupportTest extends SupportBuilderTestCase<DocketNumberSupport<P>,P> {

    @Test
    public void testEmpty() throws IOException {
        doCallRealMethod().when(mock).docketNumber(any());
        doCallRealMethod().when(mock).docketNumber(nullable(Consumer.class), any());
        mock.docketNumber();
        assertEquals("<p><docketNumber/></p>", write());
    }

    @Test
    public void testWithText() throws IOException {
        doCallRealMethod().when(mock).docketNumber(any());
        doCallRealMethod().when(mock).docketNumber(nullable(Consumer.class), any());
        InlineTypeBuilder<DocketNumber> builder = mock.docketNumber();
        builder.text("hello");
        assertEquals("<p><docketNumber>hello</docketNumber></p>", write());
    }

    @Test
    public void testWithConsumer() throws IOException {
        doCallRealMethod().when(mock).docketNumber(any());
        doCallRealMethod().when(mock).docketNumber(any(), any());
        InlineTypeBuilder<DocketNumber> builder = mock.docketNumber(dc -> dc.setEid(NoWhiteSpace.valueOf("eid_1")));
        builder.text("hello");
        assertEquals("<p><docketNumber eId=\"eid_1\">hello</docketNumber></p>", write());
    }

}
