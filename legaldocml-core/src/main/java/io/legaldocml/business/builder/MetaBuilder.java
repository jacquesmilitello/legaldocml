package io.legaldocml.business.builder;

import io.legaldocml.akn.DocumentType;
import io.legaldocml.akn.element.Identification;
import io.legaldocml.akn.type.AgentRef;
import io.legaldocml.business.AknIdentifier;
import io.legaldocml.model.Language;
import io.legaldocml.util.DateHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

import static io.legaldocml.akn.util.FRBRHelper.newFRBRlanguage;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public class MetaBuilder<T extends DocumentType> {

    /**
     * SLF4J Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MetaBuilder.class);

    private final BusinessBuilder<T> businessBuilder;

    protected MetaBuilder(BusinessBuilder<T> businessBuilder, AgentRef source) {
        this.businessBuilder = businessBuilder;
        businessBuilder.getAkomaNtoso().getDocumentType().getMeta().getIdentification().setSource(source);
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

    public void addLanguage(Language language) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("addLanguage({})", language);
        }
        this.businessBuilder.getAkomaNtoso().getDocumentType().getMeta()
                .getIdentification().getFRBRExpression().add(newFRBRlanguage(language));
    }

    public void setDate(LocalDate date) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("setDate({})", date);
        }
        Identification identification = this.businessBuilder.getAkomaNtoso().getDocumentType().getMeta().getIdentification();
        identification.getFRBRWork().getFRBRdate().setDate(DateHelper.convert(date));
        identification.getFRBRExpression().getFRBRdate().setDate(DateHelper.convert(date));
        identification.getFRBRManifestation().getFRBRdate().setDate(DateHelper.convert(date));
    }

}
