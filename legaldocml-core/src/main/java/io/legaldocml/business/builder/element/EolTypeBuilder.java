package io.legaldocml.business.builder.element;

import io.legaldocml.akn.DocumentType;
import io.legaldocml.akn.element.EolType;
import io.legaldocml.business.builder.AbstractBusinessPartBuilder;
import io.legaldocml.business.builder.BusinessBuilder;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class EolTypeBuilder<T extends EolType> extends AbstractBusinessPartBuilder<T> {

    public EolTypeBuilder(BusinessBuilder<? extends DocumentType> businessBuilder, T eolType) {
        super(businessBuilder,eolType);
    }

}
