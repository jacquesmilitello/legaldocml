package io.legaldocml.business.builder.support;

import io.legaldocml.akn.container.ANblockContainer;
import io.legaldocml.akn.element.BlockList;
import io.legaldocml.business.builder.element.BlockListBuilder;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface BlockListSupport<T extends ANblockContainer> extends SupportBuilder<T> {

    default BlockListBuilder blockList() {
        BlockList list = new BlockList();
        getParent().add(list);
        return new BlockListBuilder(getBusinessBuilder(), list);
    }

}
