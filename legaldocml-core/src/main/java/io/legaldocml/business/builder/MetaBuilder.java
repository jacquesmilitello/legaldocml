package io.legaldocml.business.builder;

import io.legaldocml.akn.DocumentType;
import io.legaldocml.business.AknIdentifier;
import io.legaldocml.iso.Language;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.legaldocml.akn.util.FRBRHelper.newFRBRlanguage;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class MetaBuilder<T extends DocumentType> {

    /**
     * SLF4J Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MetaBuilder.class);

    private final BusinessBuilder<T> businessBuilder;

    protected MetaBuilder(BusinessBuilder<T> businessBuilder) {
        this.businessBuilder = businessBuilder;
    }

    public void setAknIdentifier(AknIdentifier identifier) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("setAknIdentifier({}]", identifier);
        }

        if (identifier == null) {
            throw new BusinessBuilderException("argument identifier is null");
        }
        identifier.apply(this.businessBuilder.getAkomaNtoso());
    }

    public void setLanguage(Language language) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("setLanguage({})", language);
        }
        this.businessBuilder.getAkomaNtoso().getDocumentType().getMeta()
                .getIdentification().getFRBRExpression().add(newFRBRlanguage(language));
    }

}
