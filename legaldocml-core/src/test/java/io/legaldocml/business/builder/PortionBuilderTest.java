package io.legaldocml.business.builder;

import io.legaldocml.akn.element.Portion;
import io.legaldocml.akn.element.TLCOrganization;
import io.legaldocml.akn.type.AgentRef;
import io.legaldocml.akn.type.NoWhiteSpace;
import io.legaldocml.akn.util.TLCFactory;
import io.legaldocml.business.AknIdentifier;
import io.legaldocml.business.BusinessProvider;
import io.legaldocml.business.util.AknReference;
import io.legaldocml.io.XmlProvider;
import io.legaldocml.util.Uri;
import org.junit.Test;

import java.io.IOException;
import java.nio.channels.Channels;
import java.time.LocalDate;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public class PortionBuilderTest {

    private BusinessProvider provider = BusinessProvider.businessProvider("default");

    private static final AgentRef SOURCE = AgentRef.valueOf("vergottini");

    private static final TLCOrganization ORGANIZATION = TLCFactory.newTLCOrganization(new NoWhiteSpace("olrc"), Uri.valueOf("/akn/us/ontology/organization/olrc"),"Office of the Law Revision Counsel");

    @Test
    public void test() throws IOException {
        PortionBusinessBuilder portionBuilder = new PortionBusinessBuilder(provider, new Portion(), DefaultHierachyStrategy.COMPLETE) {
            @Override
            protected MetaBuilder newMetaBuilder() {
                return new MetaBuilder(this, SOURCE);
            }
        };

        portionBuilder.setIncludedIn("/akn/us/act/title_9");
        portionBuilder.getMetaBuilder().setAknIdentifier(new Identifier("/akn/us/usc/title_9/!main","/akn/us/usc/title_9/eng@2013-07-26/!main","/akn/us/usc/title_9/eng@2013-07-26~chp_3/!main.xml"));
        portionBuilder.getMetaBuilder().addUri(new Identifier("/akn/us/usc/title_9","/akn/us/usc/title_9/eng@2013-07-26","/akn/us/usc/title_9/eng@2013-07-26~chp_3/!main.akn"));
        portionBuilder.getMetaBuilder().setDate(LocalDate.of(1947,7,30),"Title 9", MetaBuilder.FRBR_WORK );
        portionBuilder.getMetaBuilder().setDate(LocalDate.of(2013,7,26),"Chapter 3 of Title 9 (July 26, 2013)", MetaBuilder.FRBR_EXPRESSION );
        portionBuilder.getMetaBuilder().setDate(LocalDate.of(2014,10,7),"Chapter 3 of Title 9 (July 26, 2013) -- XML Markup", MetaBuilder.FRBR_MANIFESTATION );

        portionBuilder.getMetaBuilder().addAuthor(AknReference.href(SOURCE, ORGANIZATION), MetaBuilder.FRBR_WORK);

        XmlProvider.writerFactory(3).writePermissive(Channels.newChannel(System.out), portionBuilder.getAkomaNtoso());
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
