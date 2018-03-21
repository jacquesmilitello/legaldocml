package io.legaldocml.business.builder;

import io.legaldocml.akn.AkomaNtosoContext;
import io.legaldocml.akn.element.Portion;
import io.legaldocml.akn.type.ReferenceRef;
import io.legaldocml.business.BusinessProvider;
import io.legaldocml.module.akn.DefaultAkomaNtosoContext;
import io.legaldocml.module.akn.v3.AkomaNtosoModuleV3;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class PortionBusinessBuilder<T extends PortionBodyBuilder> extends BusinessBuilder<Portion> {

    private final T bodyBuilder;

    public PortionBusinessBuilder(BusinessProvider provider, Portion portion) {
        super(provider, portion);
        this.bodyBuilder = newPortionBodyBuilder();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected AkomaNtosoContext newAkomaNtosoContext() {
        AkomaNtosoContext context = new DefaultAkomaNtosoContext(getProvider());
        context.add(AkomaNtosoModuleV3.INSTANCE);
        return context;
    }

    public final void setIncludedIn(String includedIn) {
        this.getAkomaNtoso().getDocumentType().setIncludedIn(ReferenceRef.valueOf(includedIn));
    }

    public final T getBodyBuilder() {
        return bodyBuilder;
    }

    protected abstract T newPortionBodyBuilder();

}