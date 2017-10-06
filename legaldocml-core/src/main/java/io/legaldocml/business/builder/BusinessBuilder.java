package io.legaldocml.business.builder;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.AkomaNtoso;
import io.legaldocml.akn.AkomaNtosoContext;
import io.legaldocml.akn.DocumentType;
import io.legaldocml.akn.type.AgentRef;
import io.legaldocml.business.BusinessProvider;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class BusinessBuilder {

    private final BusinessProvider provider;

    private final AkomaNtoso<DocumentType> akomaNtoso;

    private final MetaBuilder metaBuilder;

    private final HierarchyStrategy strategy;

    private AgentRef source;

    public BusinessBuilder(BusinessProvider provider, DocumentType documentType,HierarchyStrategy strategy) {
        this.provider = provider;
        this.akomaNtoso = new AkomaNtoso<>(newAkomaNtosoContext());
        this.akomaNtoso.setDocumentType(documentType);
        this.metaBuilder = newMetaBuilder();
        this.strategy = strategy;
    }

    public final MetaBuilder getMetaBuilder() {
        return this.metaBuilder;
    }

    public final BusinessProvider getProvider() {
        return this.provider;
    }

    public final HierarchyStrategy getStrategy() {
        return this.strategy;
    }

    public AgentRef getSource() {
        return source;
    }

    public void setSource(AgentRef source) {
        this.source = source;
    }

    protected abstract AkomaNtosoContext newAkomaNtosoContext();

    protected abstract MetaBuilder newMetaBuilder();

    @SuppressWarnings("unchecked")
    public final <T extends DocumentType> AkomaNtoso<T> getAkomaNtoso() {
        return (AkomaNtoso<T>) akomaNtoso;
    }

    public final <T extends BusinessPartBuilder> T newPartBuilder(AknObject parent, String businessPartBuilderName) {
        return this.provider.newPartBuilder(this, parent, businessPartBuilderName);
    }

}
