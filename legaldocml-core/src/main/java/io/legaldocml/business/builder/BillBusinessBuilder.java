package io.legaldocml.business.builder;

import io.legaldocml.akn.AkomaNtosoContext;
import io.legaldocml.akn.element.Bill;
import io.legaldocml.business.BusinessProvider;
import io.legaldocml.module.akn.DefaultAkomaNtosoContext;
import io.legaldocml.module.akn.v3.AkomaNtosoModuleV3;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class BillBusinessBuilder<E extends BusinessBuilderAkomaNtosoContext> extends BusinessBuilder<Bill,E> {

    private final BodyBuilder bodyBuilder;

    public BillBusinessBuilder(BusinessProvider provider, Bill bill) {
        super(provider, bill);
        this.bodyBuilder = new BodyBuilder(this, this.getAkomaNtoso().getDocumentType().getBody());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected E newAkomaNtosoContext() {
        DefaultAkomaNtosoContext context = new DefaultAkomaNtosoContext(getProvider());
        context.add(AkomaNtosoModuleV3.INSTANCE);
        return (E) context;
    }

    public BodyBuilder getBodyBuilder() {
        return bodyBuilder;
    }

}