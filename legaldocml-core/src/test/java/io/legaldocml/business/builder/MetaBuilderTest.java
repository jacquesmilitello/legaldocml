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
        builder.getMetaBuilder().setDate(odt.toLocalDate());

        Identification identification = builder.getAkomaNtoso().getDocumentType().getMeta().getIdentification();
        Assert.assertEquals(odt, identification.getFRBRWork().getFRBRdate().getDate());
        Assert.assertEquals(odt, identification.getFRBRExpression().getFRBRdate().getDate());
        Assert.assertEquals(odt, identification.getFRBRManifestation().getFRBRdate().getDate());


    }
}
