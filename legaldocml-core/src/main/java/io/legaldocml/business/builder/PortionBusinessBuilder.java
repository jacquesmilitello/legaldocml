package io.legaldocml.business.builder;

import io.legaldocml.akn.AkomaNtosoContext;
import io.legaldocml.akn.DocumentType;
import io.legaldocml.akn.element.Portion;
import io.legaldocml.akn.type.ReferenceRef;
import io.legaldocml.business.BusinessProvider;
import io.legaldocml.module.akn.v3.AkomaNtosoContextV3;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class PortionBusinessBuilder extends BusinessBuilder {

    private String includedIn;

    public PortionBusinessBuilder(BusinessProvider provider, DocumentType documentType, HierarchyStrategy strategy) {
        super(provider, documentType, strategy);
    }

    @Override
    protected AkomaNtosoContext newAkomaNtosoContext() {
        return new AkomaNtosoContextV3();
    }

    public final void setIncludedIn(String includedIn) {
        this.<Portion>getAkomaNtoso().getDocumentType().setIncludedIn(ReferenceRef.valueOf(includedIn));
    }
}