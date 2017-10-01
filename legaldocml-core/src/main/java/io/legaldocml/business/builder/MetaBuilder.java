package io.legaldocml.business.builder;

import io.legaldocml.akn.AknElements;
import io.legaldocml.akn.element.CoreProperties;
import io.legaldocml.akn.element.FRBRauthor;
import io.legaldocml.akn.element.FRBRdate;
import io.legaldocml.akn.element.Identification;
import io.legaldocml.akn.type.AgentRef;
import io.legaldocml.akn.util.AknList;
import io.legaldocml.akn.util.FRBRHelper;
import io.legaldocml.business.AknIdentifier;
import io.legaldocml.business.util.AknReference;
import io.legaldocml.model.Country;
import io.legaldocml.model.Language;
import io.legaldocml.util.DateHelper;
import io.legaldocml.util.Uri;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.function.Function;

import static io.legaldocml.akn.util.FRBRHelper.newFRBRlanguage;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public class MetaBuilder {

    public static final Function<Identification, CoreProperties> FRBR_WORK = new CorePropertiesGetter(AknElements.FRBR_WORK) {
        /**
         * {@inheritDoc}
         */
        @Override
        public CoreProperties apply(Identification identification) {
            return identification.getFRBRWork();
        }
    };

    public static final Function<Identification, CoreProperties> FRBR_EXPRESSION = new CorePropertiesGetter(AknElements.FRBR_EXPRESSION) {
        /**
         * {@inheritDoc}
         */
        @Override
        public CoreProperties apply(Identification identification) {
            return identification.getFRBRExpression();
        }
    };

    public static final Function<Identification, CoreProperties> FRBR_MANIFESTATION = new CorePropertiesGetter(AknElements.FRBR_MANIFESTATION) {
        /**
         * {@inheritDoc}
         */
        @Override
        public CoreProperties apply(Identification identification) {
            return identification.getFRBRManifestation();
        }
    };

    /**
     * SLF4J Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MetaBuilder.class);

    private final BusinessBuilder businessBuilder;

    private final Identification identification;

    protected MetaBuilder(BusinessBuilder businessBuilder, AgentRef source) {
        this.businessBuilder = businessBuilder;
        this.identification = businessBuilder.getAkomaNtoso().getDocumentType().getMeta().getIdentification();
        this.identification.setSource(source);
    }

    public final void setAknIdentifier(AknIdentifier identifier) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("setAknIdentifier({}]", identifier);
        }

        if (identifier == null) {
            throw new BusinessBuilderException("argument identifier is null");
        }
        identifier.apply(this.businessBuilder.getAkomaNtoso());
    }

    public final void addLanguage(Language language) {
        this.addLanguage(language, Language::getCode);
    }

    public final void addLanguage(Language language, Function<Language, String> mapper) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("addLanguage({}) -> {}", language, mapper.apply(language));
        }
        this.identification.getFRBRExpression().add(newFRBRlanguage(language,mapper));
    }

    public final void setDate(LocalDate date, String name) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("setDate({})", date);
        }
        setDate(date, name, FRBR_WORK);
        setDate(date, name, FRBR_EXPRESSION);
        setDate(date, name, FRBR_MANIFESTATION);
    }

    public void setDate(LocalDate date, String name, Function<Identification, CoreProperties> map) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("setDate({})", date);
        }

        FRBRdate frbr = map.apply(this.identification).getFRBRdate();
        frbr.setDate(DateHelper.convert(date));
        frbr.setName(name);
    }

    public final void setCountry(Country country) {
        this.setCountry(country, Country::getAlpha2);
    }

    public final void setCountry(Country country, Function<Country, String> mapper) {
        if (LOGGER.isDebugEnabled()) {

            LOGGER.debug("setCountry({})", country);
        }
        this.identification.getFRBRWork().getFRBRcountry().setValue(mapper.apply(country));
    }

    public final void addAuthor(Uri href) {
        addAuthor(href, FRBR_WORK);
        addAuthor(href, FRBR_EXPRESSION);
        addAuthor(href, FRBR_MANIFESTATION);
    }

    public final void addAuthor(Uri href, Function<Identification, CoreProperties> type) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("addAuthor({}) for ({})", href, type);
        }
        FRBRauthor frbRauthor = FRBRHelper.newFRBRauthor(href);

        AknList<FRBRauthor> authors = type.apply(this.identification).getAuthors();

        if (!authors.contains(frbRauthor)) {
            authors.add(frbRauthor);
        } else {
            if (LOGGER.isWarnEnabled()) {
                LOGGER.warn("author [{}] already exists in [{}]", frbRauthor, type);
            }
        }
    }

    public final void addAuthor(AknReference reference, Function<Identification, CoreProperties> type) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("addAuthor({}) for ({})", reference, type);
        }

        FRBRauthor frbRauthor = new FRBRauthor();
        reference.accept(frbRauthor, this.businessBuilder.getAkomaNtoso());

      //  frbRauthor.setHref(Uri.valueOf("tete"));

        AknList<FRBRauthor> authors = type.apply(this.identification).getAuthors();

        if (!authors.contains(frbRauthor)) {
            authors.add(frbRauthor);
        } else {
            if (LOGGER.isWarnEnabled()) {
                LOGGER.warn("author [{}] already exists in [{}]", frbRauthor, type);
            }
        }

    }

    public void addUri(AknIdentifier identifier) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("addUri({})", identifier);
        }

        this.identification.getFRBRWork().addFRBRuri(FRBRHelper.newFRBRuri(identifier.work()));
        this.identification.getFRBRExpression().addFRBRuri(FRBRHelper.newFRBRuri(identifier.expression()));
        this.identification.getFRBRManifestation().addFRBRuri(FRBRHelper.newFRBRuri(identifier.manifestation()));
    }


    private abstract static class CorePropertiesGetter implements Function<Identification, CoreProperties> {

        private final String name;

        private CorePropertiesGetter(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return this.name;
        }
    }

}
