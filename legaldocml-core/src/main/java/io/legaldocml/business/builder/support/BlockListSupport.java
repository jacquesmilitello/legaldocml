package io.legaldocml.business.builder.support;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.container.ANblockContainer;
import io.legaldocml.akn.element.BlockList;
import io.legaldocml.business.builder.element.BlockListBuilder;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface BlockListSupport<T extends ANblockContainer<E>, E extends AknObject> extends SupportBuilder<T> {

    default BlockListBuilder blockList() {
        BlockList list = new BlockList();
        parent().add(list);
        businessBuilder().getContext().push(parent(), list);
        return new BlockListBuilder(businessBuilder(), list);
    }

}
