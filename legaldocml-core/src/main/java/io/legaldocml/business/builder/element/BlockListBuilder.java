package io.legaldocml.business.builder.element;

import io.legaldocml.akn.DocumentType;
import io.legaldocml.akn.element.BlockList;
import io.legaldocml.akn.element.Item;
import io.legaldocml.akn.element.ListIntroduction;
import io.legaldocml.business.builder.AbstractBusinessPartBuilder;
import io.legaldocml.business.builder.BusinessBuilder;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class BlockListBuilder extends AbstractBusinessPartBuilder<BlockList> {

    public BlockListBuilder(BusinessBuilder<? extends DocumentType> businessBuilder, BlockList container) {
        super(businessBuilder, container);
    }

    public ItemBuilder item() {
        Item item = new Item();
        parent().add(item);
        businessBuilder().getContext().push(parent(), item);
        return new ItemBuilder(businessBuilder(), item);
    }

    public InlineTypeBuilder<ListIntroduction> listIntroduction() {
        ListIntroduction listIntroduction = new ListIntroduction();
        parent().setListIntroduction(listIntroduction);
        return new InlineTypeBuilder<>(businessBuilder(), listIntroduction);
    }

}