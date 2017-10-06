package io.legaldocml.business.builder;

import io.legaldocml.ReaderHelper;
import io.legaldocml.XmlUnitHelper;
import io.legaldocml.akn.element.P;
import io.legaldocml.akn.element.TLCPerson;
import io.legaldocml.akn.element.TLCRole;
import io.legaldocml.akn.type.AgentRef;
import io.legaldocml.akn.type.NoWhiteSpace;
import io.legaldocml.business.BusinessProvider;
import io.legaldocml.io.XmlProvider;
import io.legaldocml.test.SonarJUnit4ClassRunner;
import io.legaldocml.util.Uri;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.w3c.dom.Document;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.channels.Channels;

import static io.legaldocml.akn.AknElements.DEBATE;
import static io.legaldocml.akn.util.TLCFactory.newTLCPerson;
import static io.legaldocml.akn.util.TLCFactory.newTLCRole;
import static io.legaldocml.business.util.AknReference.as;
import static io.legaldocml.business.util.AknReference.refersTo;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
@RunWith(SonarJUnit4ClassRunner.class)
public class CoverPageBuilderTest {

    @Test
    public void testItSenatoDdl2013() throws IOException {

        BusinessProvider provider = BusinessProvider.businessProvider("default");
        BusinessBuilder debateBusinessBuilder = provider.newBuilder(DEBATE);

        AgentRef source = AgentRef.valueOf("redattore");
        TLCPerson person1 = newTLCPerson(new NoWhiteSpace("person_1"), Uri.raw("http://dati.senato./akn/it/osr/Persona"), "FINOCCHIARO");
        TLCPerson person2 = newTLCPerson(new NoWhiteSpace("person_2"), Uri.raw("http://dati.senato./akn/it/osr/Persona"), "ZANDA");
        TLCRole role = newTLCRole(new NoWhiteSpace("role_1"), Uri.raw("http://dati.senato./akn/it/osr/Senatore"), "Senatore");


        CoverPageBuilder coverPageBuilder = new CoverPageBuilder(debateBusinessBuilder);
        coverPageBuilder.p().text("SENATO DELLA REPUBBLICA");
        coverPageBuilder.p().docType().text("DISEGNO DI LEGGE");
        coverPageBuilder.p().docNumber().text("N. 356");
        coverPageBuilder.p().docTitle().text("Modifiche al testo unico di cui al decreto del Presidente della Repubblica 30 marzo 1957, n. 361, in materia di elezione della Camera dei deputati, e al testo unico di cui al decreto legislativo 20 dicembre 1993, n. 533, in materia di elezione del Senato della Repubblica, nonché delega al Governo per la determinazione dei collegi uninominali");

        InlineTypeBuilder<P> pBuilder = coverPageBuilder.p();
        pBuilder.text("d’iniziativa del senatori ");
        pBuilder.docProponent(refersTo(source, person1), as(source, role)).text("FINOCCHIARO");
        pBuilder.text(" e ");
        pBuilder.docProponent(refersTo(source, person2), as(source, role)).text("ZANDA");
        coverPageBuilder.p().b().text("COMUNICATO ALLA PRESIDENZA IL 29 MARZO 2013");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        XmlProvider.writerFactory(3).writePermissive(Channels.newChannel(baos), debateBusinessBuilder.getAkomaNtoso());

        Document expected = ReaderHelper.load("/xml/v3/it_senato_ddl_2013.xml");
        Document actual = ReaderHelper.load(new ByteArrayInputStream(baos.toByteArray()));

        XmlUnitHelper.compare(
                expected.getElementsByTagNameNS("http://docs.oasis-open.org/legaldocml/ns/akn/3.0", "coverPage").item(0),
                actual.getElementsByTagNameNS("http://docs.oasis-open.org/legaldocml/ns/akn/3.0", "coverPage").item(0)
        );

        Assert.assertEquals(1, actual.getElementsByTagNameNS("http://docs.oasis-open.org/legaldocml/ns/akn/3.0", "TLCRole").getLength());
        Assert.assertEquals(2, actual.getElementsByTagNameNS("http://docs.oasis-open.org/legaldocml/ns/akn/3.0", "TLCPerson").getLength());

        XmlUnitHelper.compare(
                expected.getElementsByTagNameNS("http://docs.oasis-open.org/legaldocml/ns/akn/3.0", "TLCRole").item(0),
                actual.getElementsByTagNameNS("http://docs.oasis-open.org/legaldocml/ns/akn/3.0", "TLCRole").item(0)
        );

        XmlUnitHelper.compare(
                expected.getElementsByTagNameNS("http://docs.oasis-open.org/legaldocml/ns/akn/3.0", "TLCPerson").item(0),
                actual.getElementsByTagNameNS("http://docs.oasis-open.org/legaldocml/ns/akn/3.0", "TLCPerson").item(0)
        );

        XmlUnitHelper.compare(
                expected.getElementsByTagNameNS("http://docs.oasis-open.org/legaldocml/ns/akn/3.0", "TLCPerson").item(1),
                actual.getElementsByTagNameNS("http://docs.oasis-open.org/legaldocml/ns/akn/3.0", "TLCPerson").item(1)
        );


    }
}
