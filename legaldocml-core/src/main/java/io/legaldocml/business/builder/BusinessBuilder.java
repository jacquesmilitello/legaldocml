package io.legaldocml.business.builder;

import io.legaldocml.akn.AkomaNtoso;
import io.legaldocml.akn.DocumentType;
import io.legaldocml.akn.type.AgentRef;
import io.legaldocml.business.BusinessProvider;
import io.legaldocml.model.Language;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class BusinessBuilder<T extends DocumentType, E extends BusinessBuilderAkomaNtosoContext> {

    private final BusinessProvider provider;
    private final AkomaNtoso<T> akomaNtoso;
    private final E context;
    private final MetaBuilder metaBuilder;
    private final AgentRef source;
    private Language mainLanguage;

    public BusinessBuilder(BusinessProvider provider, T documentType) {
        this.provider = provider;
        this.context = newAkomaNtosoContext();
        this.akomaNtoso = new AkomaNtoso<>(context);
        this.akomaNtoso.setDocumentType(documentType);
        this.metaBuilder = newMetaBuilder();
        this.source = this.metaBuilder.getMeta().getIdentification().getSource();
    }

    public final MetaBuilder getMetaBuilder() {
        return this.metaBuilder;
    }

    public final BusinessProvider getProvider() {
        return this.provider;
    }

    public final AgentRef getSource() {
        return source;
    }

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

    public final E getContext() {
        return this.context;
    }

    public final AkomaNtoso<T> getAkomaNtoso() {
        return akomaNtoso;
    }

    protected abstract E newAkomaNtosoContext();

    protected abstract MetaBuilder newMetaBuilder();

}
