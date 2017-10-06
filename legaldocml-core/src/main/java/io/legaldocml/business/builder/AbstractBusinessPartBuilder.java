package io.legaldocml.business.builder;

import io.legaldocml.akn.AknObject;
import io.legaldocml.business.builder.support.SupportBuilder;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class AbstractBusinessPartBuilder<T extends AknObject> implements SupportBuilder<T> {

    private final BusinessBuilder businessBuilder;
    private final T container;

    protected AbstractBusinessPartBuilder(BusinessBuilder businessBuilder, T container) {
        this.businessBuilder = businessBuilder;
        this.container = container;
    }

    public final BusinessBuilder getBusinessBuilder() {
        return businessBuilder;
    }

    @Override
    public final BusinessBuilder businessBuilder() {
        return businessBuilder;
    }

    @Override
    public T parent() {
        return this.container;
    }

}
