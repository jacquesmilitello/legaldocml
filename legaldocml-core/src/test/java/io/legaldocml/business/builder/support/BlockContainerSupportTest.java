package io.legaldocml.business.builder.support;

import io.legaldocml.akn.element.Content;
import io.legaldocml.akn.group.BlockElements;
import io.legaldocml.akn.type.NoWhiteSpace;
import io.legaldocml.business.builder.element.BlockContainerBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doCallRealMethod;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
@SuppressWarnings("unchecked")
class BlockContainerSupportTest extends SupportBuilderTestCase<BlockContainerSupport<Content, BlockElements>,Content> {

	@Test
    void testEmpty()  {
        doCallRealMethod().when(mock).blockContainer();
        doCallRealMethod().when(mock).blockContainer(any());
        mock.blockContainer();
        assertEquals("<content><blockContainer/></content>", write());
    }

    @Test
    void testAddElement()  {
        doCallRealMethod().when(mock).blockContainer();
        doCallRealMethod().when(mock).blockContainer(any());
        BlockContainerBuilder builder = mock.blockContainer( container -> {
            container.setEid(NoWhiteSpace.valueOf("test"));
        });
        builder.p();
        assertEquals("<content><blockContainer eId=\"test\"><p/></blockContainer></content>", write());
    }

}
