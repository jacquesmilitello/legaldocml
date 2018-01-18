package io.legaldocml.business.builder.element;

import io.legaldocml.akn.element.ContainerElement;
import io.legaldocml.akn.element.ContainerType;
import io.legaldocml.business.builder.AbstractBusinessPartBuilder;
import io.legaldocml.business.builder.BusinessBuilder;
import io.legaldocml.business.builder.group.BlockElementsBuilder;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class ContainerTypeBuilder<T extends ContainerType> extends AbstractBusinessPartBuilder<T> implements BlockElementsBuilder<T,ContainerElement> {

    public ContainerTypeBuilder(BusinessBuilder businessBuilder, T containerType) {
        super(businessBuilder,containerType);
    }

}
