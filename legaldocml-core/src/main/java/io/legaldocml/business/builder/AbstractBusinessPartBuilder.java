package io.legaldocml.business.builder;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.DocumentType;
import io.legaldocml.business.builder.support.SupportBuilder;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class AbstractBusinessPartBuilder<T extends AknObject> implements SupportBuilder<T> {

    private final BusinessBuilder<? extends DocumentType> businessBuilder;
    private final T container;

    protected AbstractBusinessPartBuilder(BusinessBuilder<? extends DocumentType> businessBuilder, T container) {
        this.businessBuilder = businessBuilder;
        this.container = container;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final BusinessBuilder<? extends DocumentType> businessBuilder() {
        return businessBuilder;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T parent() {
        return this.container;
    }

}
