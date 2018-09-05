package io.legaldocml.business.builder;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.DocumentType;
import io.legaldocml.business.builder.support.SupportBuilder;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class AbstractBusinessPartBuilder<T extends AknObject> implements SupportBuilder<T> {

    private final BusinessBuilder<? extends DocumentType, ? extends BusinessBuilderAkomaNtosoContext> businessBuilder;
    private final T container;

    protected AbstractBusinessPartBuilder(BusinessBuilder<? extends DocumentType, ? extends BusinessBuilderAkomaNtosoContext> businessBuilder, T container) {
        this.businessBuilder = businessBuilder;
        this.container = container;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public <D extends DocumentType, B extends BusinessBuilderAkomaNtosoContext> BusinessBuilder<D, B> businessBuilder() {
        return (BusinessBuilder<D, B>) this.businessBuilder;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T parent() {
        return this.container;
    }

}
