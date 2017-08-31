package io.legaldocml.business.builder;

import io.legaldocml.akn.AkomaNtoso;
import io.legaldocml.akn.AkomaNtosoContext;
import io.legaldocml.akn.DocumentType;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class BusinessBuilder {

    private final AkomaNtoso<DocumentType> akomaNtoso;

    private final MetaBuilder metaBuilder;

    public BusinessBuilder(DocumentType documentType) {
        this.akomaNtoso = new AkomaNtoso<>(newAkomaNtosoContext());
        this.akomaNtoso.setDocumentType(documentType);
        this.metaBuilder = newMetaBuilder();
    }

    public final MetaBuilder getMetaBuilder() {
        return this.metaBuilder;
    }

    protected abstract AkomaNtosoContext newAkomaNtosoContext();

    protected abstract MetaBuilder newMetaBuilder();

    @SuppressWarnings("unchecked")
    public <T extends DocumentType> AkomaNtoso<T> getAkomaNtoso() {
        return (AkomaNtoso<T>) akomaNtoso;
    }

}
