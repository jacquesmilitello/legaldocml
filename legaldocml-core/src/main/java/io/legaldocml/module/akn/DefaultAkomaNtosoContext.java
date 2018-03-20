package io.legaldocml.module.akn;

import io.legaldocml.akn.AkomaNtosoContext;
import io.legaldocml.business.BusinessProvider;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class DefaultAkomaNtosoContext extends AkomaNtosoContext {

    private final BusinessProvider provider;

    public DefaultAkomaNtosoContext() {
        this(BusinessProvider.businessProvider("default"));
    }

    public DefaultAkomaNtosoContext(BusinessProvider provider) {
        this.provider = provider;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BusinessProvider businessProvider() {
        return provider;
    }
}
