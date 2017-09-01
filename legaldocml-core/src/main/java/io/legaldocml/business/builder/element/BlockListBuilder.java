package io.legaldocml.business.builder.element;

import io.legaldocml.akn.element.BlockList;
import io.legaldocml.akn.element.Item;
import io.legaldocml.business.builder.BusinessBuilder;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class BlockListBuilder extends ElementBuilder<BlockList>  {

    public BlockListBuilder(BusinessBuilder businessBuilder, BlockList parent) {
        super(businessBuilder, parent);
    }

    public ItemBuilder item() {
        Item item = new Item();
        getParent().add(item);
        return new ItemBuilder(getBusinessBuilder(), item);
    }

}