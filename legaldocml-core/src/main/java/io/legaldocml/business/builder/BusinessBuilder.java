package io.legaldocml.business.builder;

import io.legaldocml.akn.AkomaNtoso;
import io.legaldocml.akn.AkomaNtosoContext;
import io.legaldocml.akn.DocumentType;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class BusinessBuilder<T extends DocumentType> {

    private final AkomaNtoso<T> akomaNtoso;

    private final MetaBuilder<T> metaBuilder;

    private final Class<T> documentTypeClass;

    public BusinessBuilder(Class<T> documentTypeClass) {
        this.documentTypeClass = documentTypeClass;
        this.akomaNtoso = new AkomaNtoso<>(newAkomaNtosoContext());
        this.akomaNtoso.setDocumentType(newDocumenyType());
        this.metaBuilder = newMetaBuilder(this);
    }

    public final MetaBuilder<T> getMetaBuilder() {
        return this.metaBuilder;
    }

    public final Class<T> getDocumentTypeClass() {
        return documentTypeClass;
    }

    protected abstract AkomaNtosoContext newAkomaNtosoContext();

    protected abstract T newDocumenyType();

    protected abstract MetaBuilder<T> newMetaBuilder(BusinessBuilder<T> businessBuilder);

    public AkomaNtoso<T> getAkomaNtoso() {
        return akomaNtoso;
    }

}
