package io.legaldocml.business.builder.support;

import io.legaldocml.akn.element.Content;
import io.legaldocml.akn.group.BlockElements;
import io.legaldocml.business.builder.element.BlockContainerBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doCallRealMethod;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
@SuppressWarnings("unchecked")
class BlockContainerSupportTest extends SupportBuilderTestCase<BlockContainerSupport<Content, BlockElements>,Content> {

	@Test
    void testEmpty() throws IOException {
        doCallRealMethod().when(mock).blockContainer();
        mock.blockContainer();
        assertEquals("<content><blockContainer/></content>", write());
    }

    @Test
    void testAddElement() throws IOException {
        doCallRealMethod().when(mock).blockContainer();
        BlockContainerBuilder builder = mock.blockContainer();
        builder.p();
        assertEquals("<content><blockContainer><p/></blockContainer></content>", write());
    }

}
