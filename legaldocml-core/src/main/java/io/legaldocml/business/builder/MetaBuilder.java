package io.legaldocml.business.builder;

import io.legaldocml.akn.DocumentType;
import io.legaldocml.akn.element.Identification;
import io.legaldocml.akn.type.AgentRef;
import io.legaldocml.business.AknIdentifier;
import io.legaldocml.model.Country;
import io.legaldocml.model.Language;
import io.legaldocml.util.DateHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.function.Function;

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

    private final Identification identification;

    protected MetaBuilder(BusinessBuilder<T> businessBuilder, AgentRef source) {
        this.businessBuilder = businessBuilder;
        this.identification = businessBuilder.getAkomaNtoso().getDocumentType().getMeta().getIdentification();
        this.identification.setSource(source);
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
        this.addLanguage(language, Language::getCode);
    }

    public void addLanguage(Language language, Function<Language, String> mapper) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("addLanguage({}) -> {}", language, mapper.apply(language));
        }
        this.identification.getFRBRExpression().add(newFRBRlanguage(mapper.apply(language)));
    }

    public void setDate(LocalDate date) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("setDate({})", date);
        }
        this.identification.getFRBRWork().getFRBRdate().setDate(DateHelper.convert(date));
        this.identification.getFRBRExpression().getFRBRdate().setDate(DateHelper.convert(date));
        this.identification.getFRBRManifestation().getFRBRdate().setDate(DateHelper.convert(date));
    }

    public void setCountry(Country country) {
        this.setCountry(country, Country::getAlpha2);
    }

    public void setCountry(Country country, Function<Country, String> mapper) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("setCountry({})", country);
        }
        this.identification.getFRBRWork().getFRBRcountry().setValue(mapper.apply(country));
    }

}
