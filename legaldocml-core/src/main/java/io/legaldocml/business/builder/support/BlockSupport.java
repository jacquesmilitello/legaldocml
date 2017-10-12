package io.legaldocml.business.builder.support;

import io.legaldocml.akn.container.BlockElementsContainer;
import io.legaldocml.akn.element.Block;
import io.legaldocml.business.builder.element.InlineTypeBuilder;

import java.util.function.Consumer;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface BlockSupport<T extends BlockElementsContainer> extends SupportBuilder<T> {

    default InlineTypeBuilder<Block> block() {
        return block(null);
    }

    default InlineTypeBuilder<Block> block(Consumer<Block> consumer) {
        Block block = new Block();
        parent().add(block);
        if (consumer != null) {
            consumer.accept(block);
        }
        return new InlineTypeBuilder<>(businessBuilder(), block);
    }

}
