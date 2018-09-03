package io.legaldocml.business.builder.element;

import io.legaldocml.akn.DocumentType;
import io.legaldocml.akn.element.EolType;
import io.legaldocml.akn.element.SrcType;
import io.legaldocml.business.builder.AbstractBusinessPartBuilder;
import io.legaldocml.business.builder.BusinessBuilder;
import io.legaldocml.business.builder.BusinessBuilderAkomaNtosoContext;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class SrcTypeBuilder<T extends SrcType> extends AbstractBusinessPartBuilder<T> {

    public SrcTypeBuilder(BusinessBuilder<? extends DocumentType, ? extends BusinessBuilderAkomaNtosoContext> businessBuilder, T srcType) {
        super(businessBuilder,srcType);
    }

}
