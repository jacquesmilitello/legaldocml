package io.legaldocml.business.builder;

import io.legaldocml.akn.DocumentType;
import io.legaldocml.akn.element.Analysis;
import io.legaldocml.akn.element.CoreProperties;
import io.legaldocml.akn.element.FRBRauthor;
import io.legaldocml.akn.element.FRBRauthoritative;
import io.legaldocml.akn.element.FRBRdate;
import io.legaldocml.akn.element.FRBRname;
import io.legaldocml.akn.element.FRBRnumber;
import io.legaldocml.akn.element.FRBRportion;
import io.legaldocml.akn.element.FRBRprescriptive;
import io.legaldocml.akn.element.FRBRsubtype;
import io.legaldocml.akn.element.Identification;
import io.legaldocml.akn.element.Meta;
import io.legaldocml.akn.element.Proprietary;
import io.legaldocml.akn.element.References;
import io.legaldocml.akn.element.Step;
import io.legaldocml.akn.element.Workflow;
import io.legaldocml.akn.type.AgentRef;
import io.legaldocml.akn.type.ConceptRef;
import io.legaldocml.akn.type.EidRef;
import io.legaldocml.akn.type.Uri;
import io.legaldocml.akn.util.AknList;
import io.legaldocml.akn.util.FRBRHelper;
import io.legaldocml.business.AknIdentifier;
import io.legaldocml.business.util.AknReference;
import io.legaldocml.model.Country;
import io.legaldocml.model.Language;
import io.legaldocml.util.Dates;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.function.Function;

import static io.legaldocml.akn.util.FRBRHelper.newFRBRlanguage;
import static io.legaldocml.akn.util.FRBRHelper.newFRBRuri;
import static io.legaldocml.akn.util.Metas.analysis;
import static io.legaldocml.akn.util.Metas.proprietary;
import static io.legaldocml.akn.util.Metas.references;
import static io.legaldocml.akn.util.Metas.workflow;
import static io.legaldocml.unsafe.UnsafeString.getChars;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 * @author <a href="mailto:mustapha.charboub@gmail.com">Mustapha CHARBOUB</a>
 */
public final class MetaBuilder {

    public static final Function<Identification, CoreProperties> LOOKUP_FRBR_WORK = Identification::getFRBRWork;
    public static final Function<Identification, CoreProperties> LOOKUP_FRBR_EXPRESSION = Identification::getFRBRExpression;
    public static final Function<Identification, CoreProperties> LOOKUP_FRBR_MANIFESTATION = Identification::getFRBRManifestation;

    /**
     * SLF4J Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MetaBuilder.class);

    private final BusinessBuilder<? extends DocumentType, ? extends BusinessBuilderAkomaNtosoContext> businessBuilder;

    private final Meta meta;

    public MetaBuilder(BusinessBuilder<? extends DocumentType, ? extends BusinessBuilderAkomaNtosoContext> businessBuilder, AgentRef source) {
        this.businessBuilder = businessBuilder;
        this.meta = businessBuilder.getAkomaNtoso().getDocumentType().getMeta();
        this.meta.getIdentification().setSource(source);
    }

    public Meta getMeta() {
        return this.meta;
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
            LOGGER.debug("addLanguage({}) -> [{}]", language, mapper.apply(language));
        }
        this.meta.getIdentification().getFRBRExpression().add(newFRBRlanguage(language, mapper));
    }

    public void setDate(LocalDate date, String name) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("setDate({})", date);
        }
        setDate(date, name, LOOKUP_FRBR_WORK);
        setDate(date, name, LOOKUP_FRBR_EXPRESSION);
        setDate(date, name, LOOKUP_FRBR_MANIFESTATION);
    }

    public void setDate(LocalDate date, String name, Function<Identification, CoreProperties> map) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("setDate({})", date);
        }

        FRBRdate frbr = map.apply(this.meta.getIdentification()).getFRBRdate();
        frbr.setDate(Dates.convert(date));
        frbr.setName(name);
    }

    public void setCountry(Country country) {
        this.setCountry(country, Country::getAlpha2);
    }

    public void setCountry(Country country, Function<Country, String> mapper) {
        if (LOGGER.isDebugEnabled()) {

            LOGGER.debug("setCountry({})", country);
        }
        this.meta.getIdentification().getFRBRWork().getFRBRcountry().setValue(mapper.apply(country));
    }

    public void addAuthor(Uri href) {
        addAuthor(href, LOOKUP_FRBR_WORK);
        addAuthor(href, LOOKUP_FRBR_EXPRESSION);
        addAuthor(href, LOOKUP_FRBR_MANIFESTATION);
    }

    public void addAuthor(Uri href, Function<Identification, CoreProperties> type) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("addAuthor({}) for ({})", href, type);
        }
        FRBRauthor frbRauthor = FRBRHelper.newFRBRauthor(href);

        AknList<FRBRauthor> authors = type.apply(this.meta.getIdentification()).getAuthors();

        if (!authors.contains(frbRauthor)) {
            authors.add(frbRauthor);
        } else {
            if (LOGGER.isWarnEnabled()) {
                LOGGER.warn("author [{}] already exists in [{}]", frbRauthor, type);
            }
        }
    }

    public void addStep(OffsetDateTime date, AgentRef source, ConceptRef outcome) {
        Workflow workflow = workflow(this.meta, source);
        Step step = new Step();
        step.setBy(source);
        step.setOutcome(outcome);
        step.setDate(date);
        workflow.addStep(step);

    }

    public FRBRauthor addAuthor(Function<Identification, CoreProperties> type, AknReference... references) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("addAuthor({}) for ({})", references, type);
        }

        FRBRauthor frbRauthor = new FRBRauthor();

        for (AknReference ref : references) {
            ref.accept(frbRauthor, this.businessBuilder.getAkomaNtoso());
        }

        AknList<FRBRauthor> authors = type.apply(this.meta.getIdentification()).getAuthors();

        if (!authors.contains(frbRauthor)) {
            authors.add(frbRauthor);
        } else {
            if (LOGGER.isWarnEnabled()) {
                LOGGER.warn("author [{}] already exists in [{}]", frbRauthor, type);
            }
        }

        return frbRauthor;

    }

    public void addUri(AknIdentifier identifier) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("addUri({})", identifier);
        }

        this.meta.getIdentification().getFRBRWork().addFRBRuri(newFRBRuri(identifier.work()));
        this.meta.getIdentification().getFRBRExpression().addFRBRuri(newFRBRuri(identifier.expression()));
        this.meta.getIdentification().getFRBRManifestation().addFRBRuri(newFRBRuri(identifier.manifestation()));
    }

    public FRBRsubtype setSubType(String value) {

        FRBRsubtype subtype = new FRBRsubtype();
        subtype.setValue(value);

        this.meta.getIdentification().getFRBRWork().setSubtype(subtype);
        return subtype;
    }

    public FRBRnumber addNumber(String value) {
        FRBRnumber number = new FRBRnumber();
        number.setValue(value);
        this.meta.getIdentification().getFRBRWork().add(number);
        return number;
    }

    public FRBRname addName(String value) {
        FRBRname name = new FRBRname();
        name.setValue(value);
        this.meta.getIdentification().getFRBRWork().add(name);
        return name;
    }

    public FRBRprescriptive setPrescriptive(boolean value) {
        FRBRprescriptive prescriptive = new FRBRprescriptive();
        prescriptive.setValue(value);
        this.meta.getIdentification().getFRBRWork().setPrescriptive(prescriptive);
        return prescriptive;
    }

    public FRBRauthoritative setAuthoritative(boolean value) {
        FRBRauthoritative authoritative = new FRBRauthoritative();
        authoritative.setValue(value);
        this.meta.getIdentification().getFRBRWork().setAuthoritative(authoritative);
        return authoritative;
    }

    public FRBRportion setPortion(String from) {
        FRBRportion portion = new FRBRportion();
        portion.setFrom(EidRef.valueOf(from));
        this.meta.getIdentification().getFRBRManifestation().setPortion(portion);
        return portion;
    }

    public References getReferences(AgentRef source) {
        return references(meta, source);
    }

    public References getReferences() {
        return getReferences(this.meta.getIdentification().getSource());
    }

    public Proprietary getProprietary(AgentRef source) {
        return proprietary(meta, source);
    }

    public Workflow getWorkFlow(AgentRef source) {
        return workflow(meta, source);
    }

    public Analysis getAnalysis(AgentRef source) {
        return analysis(this.meta, source);
    }

    public Analysis getAnalysis() {
        return analysis(this.meta, this.meta.getIdentification().getSource());
    }

}