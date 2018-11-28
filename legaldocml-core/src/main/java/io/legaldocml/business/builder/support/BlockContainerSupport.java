package io.legaldocml.business.builder.support;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.container.ANblockContainer;
import io.legaldocml.akn.element.BlockContainer;
import io.legaldocml.business.builder.element.BlockContainerBuilder;

import java.util.function.Consumer;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface BlockContainerSupport<T extends ANblockContainer<E>, E extends AknObject> extends SupportBuilder<T> {


    default BlockContainerBuilder blockContainer() {
        return blockContainer(null);
    }

    default BlockContainerBuilder blockContainer(Consumer<BlockContainer> consumer) {
        BlockContainer blockContainer = new BlockContainer();
        if (consumer != null) {
            consumer.accept(blockContainer);
        }
        parent().add(blockContainer);
        businessBuilder().getContext().push(parent(), blockContainer);
        return new BlockContainerBuilder(businessBuilder(), blockContainer);
    }

}
