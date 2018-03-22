package io.legaldocml.business.builder;

import io.legaldocml.akn.AkomaNtosoContext;
import io.legaldocml.akn.element.Portion;
import io.legaldocml.akn.type.ReferenceRef;
import io.legaldocml.business.BusinessProvider;
import io.legaldocml.module.akn.v3.AkomaNtosoContextV3;
import io.legaldocml.unsafe.UnsafeString;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class PortionBusinessBuilder<T extends PortionBodyBuilder> extends BusinessBuilder<Portion> {

    private final T bodyBuilder;

    public PortionBusinessBuilder(BusinessProvider provider, Portion portion, HierarchyStrategy strategy) {
        super(provider, portion, strategy);
        this.bodyBuilder = newPortionBodyBuilder();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected AkomaNtosoContext newAkomaNtosoContext() {
        return new AkomaNtosoContextV3();
    }

    public final void setIncludedIn(String includedIn) {
        this.getAkomaNtoso().getDocumentType().setIncludedIn(ReferenceRef.raw(UnsafeString.getChars(includedIn)));
    }

    public final T getBodyBuilder() {
        return bodyBuilder;
    }

    protected abstract T newPortionBodyBuilder();

}