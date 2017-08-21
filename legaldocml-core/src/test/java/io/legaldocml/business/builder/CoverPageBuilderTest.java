package io.legaldocml.business.builder;

import io.legaldocml.ReaderHelper;
import io.legaldocml.XmlUnitHelper;
import io.legaldocml.akn.element.CoverPage;
import io.legaldocml.akn.element.Debate;
import io.legaldocml.akn.element.TLCPerson;
import io.legaldocml.akn.element.TLCRole;
import io.legaldocml.akn.type.AgentRef;
import io.legaldocml.akn.type.NoWhiteSpace;
import io.legaldocml.business.BusinessProvider;
import io.legaldocml.business.builder.BusinessBuilder;
import io.legaldocml.business.builder.CoverPageBuilder;
import io.legaldocml.io.XmlProvider;
import io.legaldocml.util.Uri;
import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.Document;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.channels.Channels;

import static io.legaldocml.akn.util.TLCFactory.newTLCPerson;
import static io.legaldocml.akn.util.TLCFactory.newTLCRole;
import static io.legaldocml.business.util.AknReference.as;
import static io.legaldocml.business.util.AknReference.refersTo;
import static io.legaldocml.unsafe.UnsafeString.getChars;

public class CoverPageBuilderTest {

    @Test
    public void testItSenatoDdl2013() throws IOException {

        BusinessBuilder<Debate> debateBusinessBuilder = BusinessProvider.newBusinessBuilder("default", Debate.class);

        CoverPage coverPage = new CoverPage();
        debateBusinessBuilder.getAkomaNtoso().getDocumentType().setCoverPage(coverPage);

        AgentRef source = new AgentRef(getChars("#redattore"));

        TLCPerson person1 = newTLCPerson(new NoWhiteSpace("person_1"), Uri.valueOf("http://dati.senato./akn/it/osr/Persona"),"FINOCCHIARO");
        TLCPerson person2 = newTLCPerson(new NoWhiteSpace("person_2"), Uri.valueOf("http://dati.senato./akn/it/osr/Persona"),"ZANDA");
        TLCRole role = newTLCRole(new NoWhiteSpace("role_1"),Uri.valueOf("http://dati.senato./akn/it/osr/Senatore"),"Senatore");


        CoverPageBuilder coverPageBuilder = new CoverPageBuilder(debateBusinessBuilder, coverPage);
        coverPageBuilder.p().text("SENATO DELLA REPUBBLICA");
        coverPageBuilder.p().docType("DISEGNO DI LEGGE");
        coverPageBuilder.p().docNumber("N. 356");
        coverPageBuilder.p().docTitle("Modifiche al testo unico di cui al decreto del Presidente della Repubblica 30 marzo 1957, n. 361, in materia di elezione della Camera dei deputati, e al testo unico di cui al decreto legislativo 20 dicembre 1993, n. 533, in materia di elezione del Senato della Repubblica, nonché delega al Governo per la determinazione dei collegi uninominali");
        coverPageBuilder.p().text("d’iniziativa del senatori ")
                .docProponent("FINOCCHIARO", refersTo(source, person1), as(source, role))
                .text(" e ")
                .docProponent("ZANDA", refersTo(source, person2), as(source, role));
        coverPageBuilder.p().b("COMUNICATO ALLA PRESIDENZA IL 29 MARZO 2013");


        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        XmlProvider.writerFactory(3).writePermissive(Channels.newChannel(baos), debateBusinessBuilder.getAkomaNtoso());

        Document expected = ReaderHelper.load("/xml/v3/it_senato_ddl_2013.xml");
        Document actual = ReaderHelper.load(new ByteArrayInputStream(baos.toByteArray()));

        XmlUnitHelper.compare(
                expected.getElementsByTagNameNS("http://docs.oasis-open.org/legaldocml/ns/akn/3.0","coverPage").item(0),
                actual.getElementsByTagNameNS("http://docs.oasis-open.org/legaldocml/ns/akn/3.0","coverPage").item(0)
                );

        Assert.assertEquals(1, actual.getElementsByTagNameNS("http://docs.oasis-open.org/legaldocml/ns/akn/3.0","TLCRole").getLength());
        Assert.assertEquals(2, actual.getElementsByTagNameNS("http://docs.oasis-open.org/legaldocml/ns/akn/3.0","TLCPerson").getLength());

        XmlUnitHelper.compare(
                expected.getElementsByTagNameNS("http://docs.oasis-open.org/legaldocml/ns/akn/3.0","TLCRole").item(0),
                actual.getElementsByTagNameNS("http://docs.oasis-open.org/legaldocml/ns/akn/3.0","TLCRole").item(0)
        );

        XmlUnitHelper.compare(
                expected.getElementsByTagNameNS("http://docs.oasis-open.org/legaldocml/ns/akn/3.0","TLCPerson").item(0),
                actual.getElementsByTagNameNS("http://docs.oasis-open.org/legaldocml/ns/akn/3.0","TLCPerson").item(0)
        );

        XmlUnitHelper.compare(
                expected.getElementsByTagNameNS("http://docs.oasis-open.org/legaldocml/ns/akn/3.0","TLCPerson").item(1),
                actual.getElementsByTagNameNS("http://docs.oasis-open.org/legaldocml/ns/akn/3.0","TLCPerson").item(1)
        );


    }
}
