package io.legaldocml.business.builder.element;

import io.legaldocml.akn.element.Item;
import io.legaldocml.business.builder.AbstractBusinessPartBuilder;
import io.legaldocml.business.builder.BusinessBuilder;
import io.legaldocml.business.builder.support.BlockListSupport;
import io.legaldocml.business.builder.support.PSupport;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class ItemBuilder extends AbstractBusinessPartBuilder<Item> implements PSupport<Item>, BlockListSupport<Item> {

    public ItemBuilder(BusinessBuilder businessBuilder, Item parent) {
        super(businessBuilder, parent);
    }

}
