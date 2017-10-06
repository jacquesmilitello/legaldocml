package io.legaldocml.business.builder.element;

import io.legaldocml.akn.element.BlockList;
import io.legaldocml.akn.element.Item;
import io.legaldocml.business.builder.AbstractBusinessPartBuilder;
import io.legaldocml.business.builder.BusinessBuilder;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class BlockListBuilder extends AbstractBusinessPartBuilder<BlockList>  {

    public BlockListBuilder(BusinessBuilder businessBuilder, BlockList container) {
        super(businessBuilder, container);
    }

    public ItemBuilder item() {
        Item item = new Item();
        parent().add(item);
        return new ItemBuilder(getBusinessBuilder(), item);
    }

}