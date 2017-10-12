package io.legaldocml.business.builder;

import io.legaldocml.akn.AkomaNtosoContext;
import io.legaldocml.akn.element.Bill;
import io.legaldocml.akn.element.Portion;
import io.legaldocml.akn.type.ReferenceRef;
import io.legaldocml.business.BusinessProvider;
import io.legaldocml.module.akn.v3.AkomaNtosoContextV3;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class BillBusinessBuilder extends BusinessBuilder implements BusinessPartBuilder<Portion> {

    private final BodyBuilder bodyBuilder;

    public BillBusinessBuilder(BusinessProvider provider, Bill bill, HierarchyStrategy strategy) {
        super(provider, bill, strategy);
        this.bodyBuilder = new BodyBuilder(this, this.<Bill>getAkomaNtoso().getDocumentType().getBody());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected AkomaNtosoContext newAkomaNtosoContext() {
        return new AkomaNtosoContextV3();
    }

    public final void setIncludedIn(String includedIn) {
        this.<Portion>getAkomaNtoso().getDocumentType().setIncludedIn(ReferenceRef.valueOf(includedIn));
    }

    public BodyBuilder getBodyBuilder() {
        return bodyBuilder;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BusinessBuilder businessBuilder() {
        return this;
    }

}