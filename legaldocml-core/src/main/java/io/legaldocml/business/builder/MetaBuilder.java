package io.legaldocml.business.builder;

import io.legaldocml.akn.DocumentType;
import io.legaldocml.business.AknIdentifier;
import io.legaldocml.iso.Language;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class MetaBuilder<T extends DocumentType> {

    private final BusinessBuilder<T> businessBuilder;

    protected MetaBuilder(BusinessBuilder<T> businessBuilder) {
        this.businessBuilder = businessBuilder;
    }

    public void setAknIdentifier(AknIdentifier identifier) {
        if (identifier == null) {
            throw new BusinessBuilderException();
        }
        identifier.apply(this.businessBuilder.getAkomaNtoso());
    }

    public void setLanguage(Language language) {

    }
}
