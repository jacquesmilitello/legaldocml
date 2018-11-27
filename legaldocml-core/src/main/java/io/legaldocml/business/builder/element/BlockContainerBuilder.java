package io.legaldocml.business.builder.element;

import io.legaldocml.akn.DocumentType;
import io.legaldocml.akn.element.BlockContainer;
import io.legaldocml.akn.element.BlockContainerTypeElement;
import io.legaldocml.business.builder.AbstractBusinessPartBuilder;
import io.legaldocml.business.builder.BusinessBuilder;
import io.legaldocml.business.builder.BusinessBuilderAkomaNtosoContext;
import io.legaldocml.business.builder.group.BlockElementsBuilder;
import io.legaldocml.business.builder.support.ComponentRefSupport;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class BlockContainerBuilder extends AbstractBusinessPartBuilder<BlockContainer> implements ComponentRefSupport<BlockContainer, BlockContainerTypeElement>, BlockElementsBuilder<BlockContainer, BlockContainerTypeElement> {

    public BlockContainerBuilder(BusinessBuilder<? extends DocumentType, ? extends BusinessBuilderAkomaNtosoContext> businessBuilder, BlockContainer container) {
        super(businessBuilder, container);
    }

}