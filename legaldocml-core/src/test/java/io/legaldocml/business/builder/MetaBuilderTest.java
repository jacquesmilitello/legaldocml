package io.legaldocml.business.builder;

import io.legaldocml.akn.element.Debate;
import io.legaldocml.akn.element.FRBRlanguage;
import io.legaldocml.akn.element.Identification;
import io.legaldocml.akn.element.Step;
import io.legaldocml.akn.element.Workflow;
import io.legaldocml.akn.type.AgentRef;
import io.legaldocml.akn.type.ConceptRef;
import io.legaldocml.akn.type.Uri;
import io.legaldocml.business.BusinessProvider;
import io.legaldocml.iso.Iso3166;
import io.legaldocml.iso.Iso639;
import io.legaldocml.model.Country;
import io.legaldocml.model.Language;
import io.legaldocml.test.LoggerInstancePostProcessor;
import io.legaldocml.util.Dates;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Iterator;
import java.util.List;

import static io.legaldocml.akn.AknElements.DEBATE;
import static io.legaldocml.akn.AknElements.PORTION;
import static io.legaldocml.unsafe.UnsafeString.getChars;
import static java.time.OffsetDateTime.now;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
@ExtendWith(LoggerInstancePostProcessor.class)
class MetaBuilderTest {

    private BusinessProvider provider = BusinessProvider.businessProvider("default");


    @Test
    void testSetAknIdentifier() {
        BusinessBuilder<Debate, ?> builder = provider.newBuilder(DEBATE);
        builder.getMetaBuilder().setAknIdentifier(provider.newAknIdentifier("work", "expression", "manifestation"));
        Identification identification = builder.getAkomaNtoso().getDocumentType().getMeta().getIdentification();
        assertEquals("work", identification.getFRBRWork().getFRBRthis().getValue());
        assertEquals("work/expression", identification.getFRBRExpression().getFRBRthis().getValue());
        assertEquals("work/expression/manifestation", identification.getFRBRManifestation().getFRBRthis().getValue());

    }

    @Test
    void testSetAknIdentifierNull() {
        BusinessBuilder<Debate, ?> builder = provider.newBuilder(DEBATE);
        BusinessBuilderException ex = Assertions.assertThrows(BusinessBuilderException.class, () -> builder.getMetaBuilder().setAknIdentifier(null));
        Assertions.assertTrue(ex.getMessage().contains("null"));

    }

    @Test
    void testAddLanguage() {
        BusinessBuilder<Debate, ?> builder = provider.newBuilder(DEBATE);
        builder.getMetaBuilder().addLanguage(Iso639.ENGLISH);

        Identification identification = builder.getAkomaNtoso().getDocumentType().getMeta().getIdentification();
        assertEquals(1, identification.getFRBRExpression().getLanguages().size());
        assertEquals("en", identification.getFRBRExpression().getLanguages().iterator().next().getLanguage());

        builder.getMetaBuilder().addLanguage(Iso639.FRENCH, Language::getTerminology);
        assertEquals(2, identification.getFRBRExpression().getLanguages().size());

        Iterator<FRBRlanguage> iterator = identification.getFRBRExpression().getLanguages().iterator();

        assertEquals("en", iterator.next().getLanguage());
        assertEquals(Iso639.FRENCH.getTerminology(), iterator.next().getLanguage());
    }

    @Test
    void testSetCountry() {
        BusinessBuilder<Debate, ?> builder = provider.newBuilder(DEBATE);
        builder.getMetaBuilder().setCountry(Iso3166.BELGIUM);

        Identification identification = builder.getAkomaNtoso().getDocumentType().getMeta().getIdentification();
        assertEquals("be", identification.getFRBRWork().getFRBRcountry().getValue());

        builder.getMetaBuilder().setCountry(Iso3166.LUXEMBOURG, Country::getAlpha3);
        assertEquals("lux", identification.getFRBRWork().getFRBRcountry().getValue());
    }


    @Test
    void testSetDate() {
        OffsetDateTime odt = Dates.convert(LocalDate.of(2011, 3, 9));

        BusinessBuilder<Debate, ?> builder = provider.newBuilder(DEBATE);
        builder.getMetaBuilder().setDate(odt.toLocalDate(), "test");

        Identification identification = builder.getAkomaNtoso().getDocumentType().getMeta().getIdentification();
        assertEquals(odt, identification.getFRBRWork().getFRBRdate().getDate());
        assertEquals("test", identification.getFRBRWork().getFRBRdate().getName());
        assertEquals(odt, identification.getFRBRExpression().getFRBRdate().getDate());
        assertEquals("test", identification.getFRBRExpression().getFRBRdate().getName());
        assertEquals(odt, identification.getFRBRManifestation().getFRBRdate().getDate());
        assertEquals("test", identification.getFRBRManifestation().getFRBRdate().getName());

        OffsetDateTime odt2 = Dates.convert(LocalDate.of(2014, 9, 17));

        builder.getMetaBuilder().setDate(odt2.toLocalDate(), "modif", MetaBuilder.LOOKUP_FRBR_EXPRESSION);
        assertEquals(odt, identification.getFRBRWork().getFRBRdate().getDate());
        assertEquals("test", identification.getFRBRWork().getFRBRdate().getName());
        assertEquals(odt2, identification.getFRBRExpression().getFRBRdate().getDate());
        assertEquals("modif", identification.getFRBRExpression().getFRBRdate().getName());
        assertEquals(odt, identification.getFRBRManifestation().getFRBRdate().getDate());
        assertEquals("test", identification.getFRBRManifestation().getFRBRdate().getName());

        builder.getMetaBuilder().setDate(odt2.toLocalDate(), "modif-2", MetaBuilder.LOOKUP_FRBR_MANIFESTATION);
        assertEquals(odt, identification.getFRBRWork().getFRBRdate().getDate());
        assertEquals("test", identification.getFRBRWork().getFRBRdate().getName());
        assertEquals(odt2, identification.getFRBRExpression().getFRBRdate().getDate());
        assertEquals("modif", identification.getFRBRExpression().getFRBRdate().getName());
        assertEquals(odt2, identification.getFRBRManifestation().getFRBRdate().getDate());
        assertEquals("modif-2", identification.getFRBRManifestation().getFRBRdate().getName());

        builder.getMetaBuilder().setDate(odt.toLocalDate(), "test", MetaBuilder.LOOKUP_FRBR_MANIFESTATION);
        builder.getMetaBuilder().setDate(odt2.toLocalDate(), "modif-3", MetaBuilder.LOOKUP_FRBR_WORK);
        assertEquals(odt2, identification.getFRBRWork().getFRBRdate().getDate());
        assertEquals("modif-3", identification.getFRBRWork().getFRBRdate().getName());
        assertEquals(odt2, identification.getFRBRExpression().getFRBRdate().getDate());
        assertEquals("modif", identification.getFRBRExpression().getFRBRdate().getName());
        assertEquals(odt, identification.getFRBRManifestation().getFRBRdate().getDate());
        assertEquals("test", identification.getFRBRManifestation().getFRBRdate().getName());

    }

    @Test
    void testAddAuthor() {
        BusinessProvider provider = BusinessProvider.businessProvider("default");
        BusinessBuilder<Debate, ?> builder = provider.newBuilder(DEBATE);

        builder.getMetaBuilder().addAuthor(Uri.valueOf("#jacques"));

        Identification identification = builder.getAkomaNtoso().getDocumentType().getMeta().getIdentification();
        assertEquals(1, identification.getFRBRWork().getAuthors().size());
        assertEquals(1, identification.getFRBRExpression().getAuthors().size());
        assertEquals(1, identification.getFRBRManifestation().getAuthors().size());

        builder.getMetaBuilder().addAuthor(Uri.valueOf("#jacques2"), MetaBuilder.LOOKUP_FRBR_EXPRESSION);
        assertEquals(1, identification.getFRBRWork().getAuthors().size());
        assertEquals(2, identification.getFRBRExpression().getAuthors().size());
        assertEquals(1, identification.getFRBRManifestation().getAuthors().size());
        assertEquals("#jacques", identification.getFRBRExpression().getAuthors().get(0).getHref().toString());
        assertEquals("#jacques2", identification.getFRBRExpression().getAuthors().get(1).getHref().toString());

        builder.getMetaBuilder().addAuthor(Uri.valueOf("#jacques3"), MetaBuilder.LOOKUP_FRBR_MANIFESTATION);
        assertEquals(1, identification.getFRBRWork().getAuthors().size());
        assertEquals(2, identification.getFRBRExpression().getAuthors().size());
        assertEquals(2, identification.getFRBRManifestation().getAuthors().size());
        assertEquals("#jacques", identification.getFRBRExpression().getAuthors().get(0).getHref().toString());
        assertEquals("#jacques2", identification.getFRBRExpression().getAuthors().get(1).getHref().toString());
        assertEquals("#jacques", identification.getFRBRManifestation().getAuthors().get(0).getHref().toString());
        assertEquals("#jacques3", identification.getFRBRManifestation().getAuthors().get(1).getHref().toString());
    }

    @Test
    void testAddStep() {
        BusinessProvider provider = BusinessProvider.businessProvider("default");
        BusinessBuilder<Debate, ?> builder = provider.newBuilder(PORTION);
        ConceptRef concept1 = new ConceptRef(getChars("concept1"));
        ConceptRef concept2 = new ConceptRef(getChars("concept2"));
        ConceptRef concept3 = new ConceptRef(getChars("concept3"));
        AgentRef op = AgentRef.valueOf("#jacques");
        builder.getMetaBuilder().addStep(now(), op, concept1);
        builder.getMetaBuilder().addStep(now(), op, concept2);
        builder.getMetaBuilder().addStep(now(), op, concept3);
        Workflow workflow = builder.getMetaBuilder().getMeta().getWorkflow(op);
        assertNotNull(workflow);
        List<Step> steps = workflow.getSteps().collect(toList());
        assertNotNull(steps);
        assertEquals(3, steps.size());
    }
}
