package io.legaldocml.business.builder;

import io.legaldocml.akn.element.Debate;
import io.legaldocml.akn.element.Identification;
import io.legaldocml.business.BusinessProvider;
import io.legaldocml.iso.Iso3166;
import io.legaldocml.iso.Iso639;
import io.legaldocml.model.Country;
import io.legaldocml.model.Language;
import io.legaldocml.test.SonarJUnit4ClassRunner;
import io.legaldocml.util.DateHelper;
import io.legaldocml.util.Uri;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@RunWith(SonarJUnit4ClassRunner.class)
public class MetaBuilderTest {

    @Test
    public void testSetAknIdentifier() throws IOException {
        BusinessProvider provider = BusinessProvider.businessProvider("default");
        BusinessBuilder<Debate> builder = provider.newBuilder(Debate.ELEMENT);
        builder.getMetaBuilder().setAknIdentifier(provider.newAknIdentifier("work", "expression", "manifestation"));
        Identification identification = builder.getAkomaNtoso().getDocumentType().getMeta().getIdentification();
        Assert.assertEquals("work", identification.getFRBRWork().getFRBRthis().getValue());
        Assert.assertEquals("work/expression", identification.getFRBRExpression().getFRBRthis().getValue());
        Assert.assertEquals("work/expression/manifestation", identification.getFRBRManifestation().getFRBRthis().getValue());

    }

    @Test
    public void testAddLanguage() throws IOException {
        BusinessProvider provider = BusinessProvider.businessProvider("default");
        BusinessBuilder<Debate> builder = provider.newBuilder(Debate.ELEMENT);
        builder.getMetaBuilder().addLanguage(Iso639.ENGLISH);

        Identification identification = builder.getAkomaNtoso().getDocumentType().getMeta().getIdentification();
        Assert.assertEquals(1, identification.getFRBRExpression().getLanguages().size());
        Assert.assertEquals("en", identification.getFRBRExpression().getLanguages().get(0).getLanguage());

        builder.getMetaBuilder().addLanguage(Iso639.FRENCH, Language::getTerminology);
        Assert.assertEquals(2, identification.getFRBRExpression().getLanguages().size());
        Assert.assertEquals("en", identification.getFRBRExpression().getLanguages().get(0).getLanguage());
        Assert.assertEquals(Iso639.FRENCH.getTerminology(), identification.getFRBRExpression().getLanguages().get(1).getLanguage());
    }

    @Test
    public void testSetCountry() throws IOException {
        BusinessProvider provider = BusinessProvider.businessProvider("default");
        BusinessBuilder<Debate> builder = provider.newBuilder(Debate.ELEMENT);
        builder.getMetaBuilder().setCountry(Iso3166.BELGIUM);

        Identification identification = builder.getAkomaNtoso().getDocumentType().getMeta().getIdentification();
        Assert.assertEquals("be", identification.getFRBRWork().getFRBRcountry().getValue());

        builder.getMetaBuilder().setCountry(Iso3166.LUXEMBOURG, Country::getAlpha3);
        Assert.assertEquals("lux", identification.getFRBRWork().getFRBRcountry().getValue());
    }


    @Test
    public void testSetDate() throws IOException {
        OffsetDateTime odt = DateHelper.convert(LocalDate.of(2011, 3, 9));

        BusinessProvider provider = BusinessProvider.businessProvider("default");
        BusinessBuilder<Debate> builder = provider.newBuilder(Debate.ELEMENT);
        builder.getMetaBuilder().setDate(odt.toLocalDate(), "test");

        Identification identification = builder.getAkomaNtoso().getDocumentType().getMeta().getIdentification();
        Assert.assertEquals(odt, identification.getFRBRWork().getFRBRdate().getDate());
        Assert.assertEquals("test", identification.getFRBRWork().getFRBRdate().getName());
        Assert.assertEquals(odt, identification.getFRBRExpression().getFRBRdate().getDate());
        Assert.assertEquals("test", identification.getFRBRExpression().getFRBRdate().getName());
        Assert.assertEquals(odt, identification.getFRBRManifestation().getFRBRdate().getDate());
        Assert.assertEquals("test", identification.getFRBRManifestation().getFRBRdate().getName());

        OffsetDateTime odt2 = DateHelper.convert(LocalDate.of(2014, 9, 17));

        builder.getMetaBuilder().setDate(odt2.toLocalDate(), "modif", MetaBuilder.FRBR_EXPRESSION);
        Assert.assertEquals(odt, identification.getFRBRWork().getFRBRdate().getDate());
        Assert.assertEquals("test", identification.getFRBRWork().getFRBRdate().getName());
        Assert.assertEquals(odt2, identification.getFRBRExpression().getFRBRdate().getDate());
        Assert.assertEquals("modif", identification.getFRBRExpression().getFRBRdate().getName());
        Assert.assertEquals(odt, identification.getFRBRManifestation().getFRBRdate().getDate());
        Assert.assertEquals("test", identification.getFRBRManifestation().getFRBRdate().getName());

        builder.getMetaBuilder().setDate(odt2.toLocalDate(), "modif-2", MetaBuilder.FRBR_MANIFESTATION);
        Assert.assertEquals(odt, identification.getFRBRWork().getFRBRdate().getDate());
        Assert.assertEquals("test", identification.getFRBRWork().getFRBRdate().getName());
        Assert.assertEquals(odt2, identification.getFRBRExpression().getFRBRdate().getDate());
        Assert.assertEquals("modif", identification.getFRBRExpression().getFRBRdate().getName());
        Assert.assertEquals(odt2, identification.getFRBRManifestation().getFRBRdate().getDate());
        Assert.assertEquals("modif-2", identification.getFRBRManifestation().getFRBRdate().getName());

        builder.getMetaBuilder().setDate(odt.toLocalDate(), "test", MetaBuilder.FRBR_MANIFESTATION);
        builder.getMetaBuilder().setDate(odt2.toLocalDate(), "modif-3",  MetaBuilder.FRBR_WORK);
        Assert.assertEquals(odt2, identification.getFRBRWork().getFRBRdate().getDate());
        Assert.assertEquals("modif-3", identification.getFRBRWork().getFRBRdate().getName());
        Assert.assertEquals(odt2, identification.getFRBRExpression().getFRBRdate().getDate());
        Assert.assertEquals("modif", identification.getFRBRExpression().getFRBRdate().getName());
        Assert.assertEquals(odt, identification.getFRBRManifestation().getFRBRdate().getDate());
        Assert.assertEquals("test", identification.getFRBRManifestation().getFRBRdate().getName());

    }

    @Test
    public void testAddAuthor() throws IOException {
        BusinessProvider provider = BusinessProvider.businessProvider("default");
        BusinessBuilder<Debate> builder = provider.newBuilder(Debate.ELEMENT);

        builder.getMetaBuilder().addAuthor(Uri.valueOf("#jacques"));

        Identification identification = builder.getAkomaNtoso().getDocumentType().getMeta().getIdentification();
        Assert.assertEquals(1, identification.getFRBRWork().getAuthors().size());
        Assert.assertEquals(1, identification.getFRBRExpression().getAuthors().size());
        Assert.assertEquals(1, identification.getFRBRManifestation().getAuthors().size());

        builder.getMetaBuilder().addAuthor(Uri.valueOf("#jacques"));
        Assert.assertEquals(1, identification.getFRBRWork().getAuthors().size());
        Assert.assertEquals(1, identification.getFRBRExpression().getAuthors().size());
        Assert.assertEquals(1, identification.getFRBRManifestation().getAuthors().size());

        builder.getMetaBuilder().addAuthor(Uri.valueOf("#jacques2"), MetaBuilder.FRBR_EXPRESSION);
        Assert.assertEquals(1, identification.getFRBRWork().getAuthors().size());
        Assert.assertEquals(2, identification.getFRBRExpression().getAuthors().size());
        Assert.assertEquals(1, identification.getFRBRManifestation().getAuthors().size());
        Assert.assertEquals("#jacques", identification.getFRBRExpression().getAuthors().get(0).getHref().toString());
        Assert.assertEquals("#jacques2", identification.getFRBRExpression().getAuthors().get(1).getHref().toString());

        builder.getMetaBuilder().addAuthor(Uri.valueOf("#jacques3"), MetaBuilder.FRBR_MANIFESTATION);
        Assert.assertEquals(1, identification.getFRBRWork().getAuthors().size());
        Assert.assertEquals(2, identification.getFRBRExpression().getAuthors().size());
        Assert.assertEquals(2, identification.getFRBRManifestation().getAuthors().size());
        Assert.assertEquals("#jacques", identification.getFRBRExpression().getAuthors().get(0).getHref().toString());
        Assert.assertEquals("#jacques2", identification.getFRBRExpression().getAuthors().get(1).getHref().toString());
        Assert.assertEquals("#jacques", identification.getFRBRManifestation().getAuthors().get(0).getHref().toString());
        Assert.assertEquals("#jacques3", identification.getFRBRManifestation().getAuthors().get(1).getHref().toString());
    }
}
