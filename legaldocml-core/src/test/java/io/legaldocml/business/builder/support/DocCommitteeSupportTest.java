package io.legaldocml.business.builder.support;

import io.legaldocml.akn.element.DocCommittee;
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
@SuppressWarnings("unchecked")
class DocCommitteeSupportTest extends SupportBuilderTestCase<DocCommitteeSupport<P,?>,P> {

    @Test
    void testEmpty() throws IOException {
        doCallRealMethod().when(mock).docCommittee(any());
        doCallRealMethod().when(mock).docCommittee(nullable(Consumer.class), any());
        mock.docCommittee();
        assertEquals("<p><docCommittee/></p>", write());
    }

    @Test
    void testWithText() throws IOException {
        doCallRealMethod().when(mock).docCommittee(any());
        doCallRealMethod().when(mock).docCommittee(nullable(Consumer.class), any());
        InlineTypeBuilder<DocCommittee> builder = mock.docCommittee();
        builder.text("hello");
        assertEquals("<p><docCommittee>hello</docCommittee></p>", write());
    }

    @Test
    void testWithConsumer() throws IOException {
        doCallRealMethod().when(mock).docCommittee(any());
        doCallRealMethod().when(mock).docCommittee(any(), any());
        InlineTypeBuilder<DocCommittee> builder = mock.docCommittee( dc -> dc.setEid(NoWhiteSpace.valueOf("eid_1")));
        builder.text("hello");
        assertEquals("<p><docCommittee eId=\"eid_1\">hello</docCommittee></p>", write());
    }

}
