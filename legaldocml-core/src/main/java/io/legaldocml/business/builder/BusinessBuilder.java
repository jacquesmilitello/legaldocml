package io.legaldocml.business.builder;

import io.legaldocml.akn.AkomaNtoso;
import io.legaldocml.akn.AkomaNtosoContext;
import io.legaldocml.akn.DocumentType;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class BusinessBuilder {

    private final AkomaNtoso<? extends DocumentType> akomaNtoso;

    private final MetaBuilder metaBuilder;

    public BusinessBuilder() {
        this.akomaNtoso = new AkomaNtoso<>(newAkomaNtosoContext());
        this.akomaNtoso.setDocumentType(newDocumenyType());
        this.metaBuilder = newMetaBuilder(this);
    }

    public final MetaBuilder getMetaBuilder() {
        return this.metaBuilder;
    }

    protected abstract AkomaNtosoContext newAkomaNtosoContext();

    protected abstract <T extends DocumentType> T newDocumenyType();

    protected abstract MetaBuilder newMetaBuilder(BusinessBuilder businessBuilder);

    @SuppressWarnings("unchecked")
    public <T extends DocumentType> AkomaNtoso<T> getAkomaNtoso() {
        return (AkomaNtoso<T>) akomaNtoso;
    }

}
