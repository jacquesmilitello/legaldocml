package io.legaldocml.business.builder.element;

import io.legaldocml.akn.DocumentType;
import io.legaldocml.akn.element.DocContainerType;
import io.legaldocml.business.builder.AbstractBusinessPartBuilder;
import io.legaldocml.business.builder.BusinessBuilder;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class DocContainerTypeBuilder<T extends DocContainerType> extends AbstractBusinessPartBuilder<T> {

    public DocContainerTypeBuilder(BusinessBuilder<? extends DocumentType> businessBuilder, T modType) {
        super(businessBuilder,modType);
    }

}
