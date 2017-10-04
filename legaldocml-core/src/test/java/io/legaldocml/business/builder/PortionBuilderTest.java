package io.legaldocml.business.builder;

import io.legaldocml.ReaderHelper;
import io.legaldocml.XmlUnitHelper;
import io.legaldocml.akn.element.Portion;
import io.legaldocml.akn.element.TLCOrganization;
import io.legaldocml.akn.element.TLCPerson;
import io.legaldocml.akn.type.AgentRef;
import io.legaldocml.akn.type.NoWhiteSpace;
import io.legaldocml.akn.type.RoleRef;
import io.legaldocml.business.AknIdentifier;
import io.legaldocml.business.BusinessProvider;
import io.legaldocml.io.XmlProvider;
import io.legaldocml.iso.Iso3166;
import io.legaldocml.iso.Iso639;
import io.legaldocml.model.Language;
import io.legaldocml.util.Uri;
import org.junit.Test;
import org.w3c.dom.Document;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.channels.Channels;
import java.time.LocalDate;

import static io.legaldocml.akn.type.RoleRef.valueOf;
import static io.legaldocml.akn.util.TLCFactory.newTLCOrganization;
import static io.legaldocml.akn.util.TLCFactory.newTLCPerson;
import static io.legaldocml.business.util.AknReference.href;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public class PortionBuilderTest {

    private BusinessProvider provider = BusinessProvider.businessProvider("default");

    private static final AgentRef SOURCE = AgentRef.valueOf("vergottini");

    private static final TLCOrganization ORGANIZATION = newTLCOrganization(new NoWhiteSpace("olrc"), Uri.valueOf("/akn/us/ontology/organization/olrc"),"Office of the Law Revision Counsel");
    private static final TLCPerson PERSON_VERGOTTINI = newTLCPerson(new NoWhiteSpace("vergottini"), Uri.valueOf("/akn/us/ontology/person/somebody"),"Grant Vergottini");

    @Test
    public void test() throws IOException {
        PortionBusinessBuilder portionBuilder = new PortionBusinessBuilder(provider, new Portion(), DefaultHierachyStrategy.COMPLETE) {
            @Override
            protected MetaBuilder newMetaBuilder() {
                return new MetaBuilder(this, SOURCE);
            }
        };

        portionBuilder.setIncludedIn("/akn/us/act/title_9");

        MetaBuilder metaBuilder = portionBuilder.getMetaBuilder();

        metaBuilder.setAknIdentifier(new Identifier("/akn/us/usc/title_9/!main","/akn/us/usc/title_9/eng@2013-07-26/!main","/akn/us/usc/title_9/eng@2013-07-26~chp_3/!main.xml"));
        metaBuilder.addUri(new Identifier("/akn/us/usc/title_9","/akn/us/usc/title_9/eng@2013-07-26","/akn/us/usc/title_9/eng@2013-07-26~chp_3/!main.akn"));
        metaBuilder.setDate(LocalDate.of(1947,7,30),"Title 9", MetaBuilder.FRBR_WORK );
        metaBuilder.setDate(LocalDate.of(2013,7,26),"Chapter 3 of Title 9 (July 26, 2013)", MetaBuilder.FRBR_EXPRESSION );
        metaBuilder.setDate(LocalDate.of(2014,10,7),"Chapter 3 of Title 9 (July 26, 2013) -- XML Markup", MetaBuilder.FRBR_MANIFESTATION );

        metaBuilder.addAuthor(href(SOURCE, ORGANIZATION), MetaBuilder.FRBR_WORK).setAs(valueOf("author"));
        metaBuilder.addAuthor(href(SOURCE, ORGANIZATION), MetaBuilder.FRBR_EXPRESSION).setAs(valueOf("editor"));
        metaBuilder.addAuthor(href(SOURCE, PERSON_VERGOTTINI), MetaBuilder.FRBR_MANIFESTATION).setAs(RoleRef.raw("generator".toCharArray()));

        metaBuilder.setCountry(Iso3166.UNITED_STATES_OF_AMERICA);
        metaBuilder.addLanguage(Iso639.ENGLISH, Language::getTerminology);

        metaBuilder.setSubType("title");
        metaBuilder.addNumber("title_9");
        metaBuilder.addName("title");
        metaBuilder.setPrescriptive(false);
        metaBuilder.setAuthoritative(true);
        metaBuilder.setPortion("chp_3");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        XmlProvider.writerFactory(3).writePermissive(Channels.newChannel(baos), portionBuilder.getAkomaNtoso());

        Document expected = ReaderHelper.load("/xml/v3/us_Title9-Chap3-eng.xml");
        Document actual = ReaderHelper.load(new ByteArrayInputStream(baos.toByteArray()));

        XmlUnitHelper.compare(
                expected.getElementsByTagNameNS("http://docs.oasis-open.org/legaldocml/ns/akn/3.0", "identification").item(0),
                actual.getElementsByTagNameNS("http://docs.oasis-open.org/legaldocml/ns/akn/3.0", "identification").item(0)
        );

    }


    private static class Identifier extends AknIdentifier {

        private String work;
        private String expression;
        private String manifestation;

        public Identifier(String work, String expression, String manifestation) {
            this.work = work;
            this.expression = expression;
            this.manifestation = manifestation;
        }

        @Override
        public String work() {
            return this.work;
        }

        @Override
        public String expression() {
            return this.expression;
        }

        @Override
        public String expressionPart() {
            return null;
        }

        @Override
        public String manifestation() {
            return this.manifestation;
        }

        @Override
        public String manifestationPart() {
            return null;
        }

        @Override
        protected boolean doEquals(AknIdentifier aknIdentifier) {
            return false;
        }
    }
}
