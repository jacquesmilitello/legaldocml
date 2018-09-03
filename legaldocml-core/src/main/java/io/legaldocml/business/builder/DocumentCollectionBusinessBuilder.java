package io.legaldocml.business.builder;

import io.legaldocml.akn.element.DocumentCollection;
import io.legaldocml.business.BusinessProvider;
import io.legaldocml.module.akn.DefaultAkomaNtosoContext;
import io.legaldocml.module.akn.v3.AkomaNtosoModuleV3;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class DocumentCollectionBusinessBuilder<E extends BusinessBuilderAkomaNtosoContext> extends BusinessBuilder<DocumentCollection, E> {

    private final CollectionBodyBuilder bodyBuilder;

    private PrefaceBuilder prefaceBuilder;

    private PreambleBuilder preambleBuilder;

    public DocumentCollectionBusinessBuilder(BusinessProvider provider, DocumentCollection documentCollection) {
        super(provider, documentCollection);
        this.bodyBuilder = new CollectionBodyBuilder(this, this.getAkomaNtoso().getDocumentType().getBody());
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    protected E newAkomaNtosoContext() {
        BusinessBuilderAkomaNtosoContext context = new DefaultAkomaNtosoContext(getProvider());
        context.add(AkomaNtosoModuleV3.INSTANCE);
        return (E) context;
    }

    public final PrefaceBuilder preface() {
        if (this.prefaceBuilder == null) {
            this.prefaceBuilder = new PrefaceBuilder(this);
        }
        return this.prefaceBuilder;
    }

    public final PreambleBuilder preamble() {
        if (this.preambleBuilder == null) {
            this.preambleBuilder = new PreambleBuilder(this);
        }
        return this.preambleBuilder;
    }

    public final CollectionBodyBuilder body() {
        return this.bodyBuilder;
    }

}