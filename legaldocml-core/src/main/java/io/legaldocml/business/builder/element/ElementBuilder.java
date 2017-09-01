package io.legaldocml.business.builder.element;

import io.legaldocml.business.builder.BusinessBuilder;
import io.legaldocml.business.builder.BusinessPartBuilder;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class ElementBuilder<T> extends BusinessPartBuilder {

    private final T parent;

    protected ElementBuilder(BusinessBuilder businessBuilder, T parent) {
        super(businessBuilder);
        this.parent = parent;
    }

    public final T getParent() {
        return this.parent;
    }

}
