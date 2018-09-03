package io.legaldocml.business.builder.element;

import io.legaldocml.akn.DocumentType;
import io.legaldocml.akn.element.Blocksreq;
import io.legaldocml.akn.group.BlockElements;
import io.legaldocml.business.builder.AbstractBusinessPartBuilder;
import io.legaldocml.business.builder.BusinessBuilder;
import io.legaldocml.business.builder.BusinessBuilderAkomaNtosoContext;
import io.legaldocml.business.builder.group.BlockElementsBuilder;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public class BlocksreqBuilder<T extends Blocksreq> extends AbstractBusinessPartBuilder<T> implements BlockElementsBuilder<T,BlockElements> {

    public BlocksreqBuilder(BusinessBuilder<? extends DocumentType, ? extends BusinessBuilderAkomaNtosoContext> businessBuilder, T blocksreq) {
        super(businessBuilder, blocksreq);
    }

}
