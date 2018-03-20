package io.legaldocml.business.builder;

import io.legaldocml.akn.AkomaNtoso;
import io.legaldocml.akn.AkomaNtosoContext;
import io.legaldocml.akn.DocumentType;
import io.legaldocml.akn.type.AgentRef;
import io.legaldocml.business.BusinessProvider;
import io.legaldocml.model.Language;
import io.legaldocml.module.akn.v3.AkomaNtosoModuleV3;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class BusinessBuilder<T extends DocumentType> {

    private final BusinessProvider provider;

    private final AkomaNtoso<T> akomaNtoso;

    private final MetaBuilder metaBuilder;

    private final HierarchyStrategy strategy;

    private final AgentRef source;

    private Language mainLanguage;

    public BusinessBuilder(BusinessProvider provider, T documentType, HierarchyStrategy strategy) {
        this.provider = provider;
        this.akomaNtoso = new AkomaNtoso<>(newAkomaNtosoContext());
        this.akomaNtoso.setDocumentType(documentType);
        this.metaBuilder = newMetaBuilder();
        this.strategy = strategy;
        this.source = this.metaBuilder.getMeta().getIdentification().getSource();
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

    public final AgentRef getSource() {
        return source;
    }
//
//    public final void setSource(AgentRef source) {
//        this.source = source;
//    }

    public final Language getMainLanguage() {
        return mainLanguage;
    }

    public final void setMainLanguage(Language mainLanguage) {
        if (this.mainLanguage != null) {
            throw new BusinessBuilderException("Main Language [" + this.mainLanguage + "] already set.");
        }
        this.mainLanguage = mainLanguage;
        getMetaBuilder().addLanguage(this.mainLanguage, Language::getTerminology);
    }

    protected abstract AkomaNtosoContext newAkomaNtosoContext();

    protected abstract MetaBuilder newMetaBuilder();

    public final AkomaNtoso<T> getAkomaNtoso() {
        return akomaNtoso;
    }

}
