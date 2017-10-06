package io.legaldocml.business.builder;

import io.legaldocml.ReaderHelper;
import io.legaldocml.XmlUnitHelper;
import io.legaldocml.akn.element.Block;
import io.legaldocml.akn.element.Chapter;
import io.legaldocml.akn.element.Content;
import io.legaldocml.akn.element.Mref;
import io.legaldocml.akn.element.P;
import io.legaldocml.akn.element.Portion;
import io.legaldocml.akn.element.Section;
import io.legaldocml.akn.element.Span;
import io.legaldocml.akn.element.Subsection;
import io.legaldocml.akn.element.TLCOrganization;
import io.legaldocml.akn.element.TLCPerson;
import io.legaldocml.akn.element.TocItem;
import io.legaldocml.akn.type.AgentRef;
import io.legaldocml.akn.type.NoWhiteSpace;
import io.legaldocml.akn.type.RoleRef;
import io.legaldocml.business.AknIdentifier;
import io.legaldocml.business.BusinessProvider;
import io.legaldocml.business.builder.element.TocBuilder;
import io.legaldocml.io.XmlProvider;
import io.legaldocml.iso.Iso3166;
import io.legaldocml.iso.Iso639;
import io.legaldocml.model.Language;
import io.legaldocml.module.akn.v3.DefaultXmlWriterFactoryV3;
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

    private static final TLCOrganization ORGANIZATION = newTLCOrganization(new NoWhiteSpace("olrc"), Uri.valueOf("/akn/us/ontology/organization/olrc"), "Office of the Law Revision Counsel");
    private static final TLCOrganization ORGANIZATION_2 = newTLCOrganization(new NoWhiteSpace("interAmericanCommercialArbitationCommission"), Uri.valueOf("/akn/us/ontology/organization/interAmericanCommercialArbitationCommission"), "Inter-American Commercial Arbitration Commission");

    private static final TLCPerson PERSON_VERGOTTINI = newTLCPerson(new NoWhiteSpace("vergottini"), Uri.valueOf("/akn/us/ontology/person/somebody"), "Grant Vergottini");

    @Test
    public void testMeta() throws IOException {
        PortionBusinessBuilder portionBuilder = new PortionBusinessBuilder(provider, new Portion(), DefaultHierachyStrategy.COMPLETE) {
            @Override
            protected MetaBuilder newMetaBuilder() {
                return new MetaBuilder(this, SOURCE);
            }
        };

        portionBuilder.setSource(SOURCE);
        portionBuilder.setIncludedIn("/akn/us/act/title_9");

        MetaBuilder metaBuilder = portionBuilder.getMetaBuilder();

        metaBuilder.setAknIdentifier(new Identifier("/akn/us/usc/title_9/!main", "/akn/us/usc/title_9/eng@2013-07-26/!main", "/akn/us/usc/title_9/eng@2013-07-26~chp_3/!main.xml"));
        metaBuilder.addUri(new Identifier("/akn/us/usc/title_9", "/akn/us/usc/title_9/eng@2013-07-26", "/akn/us/usc/title_9/eng@2013-07-26~chp_3/!main.akn"));
        metaBuilder.setDate(LocalDate.of(1947, 7, 30), "Title 9", MetaBuilder.FRBR_WORK);
        metaBuilder.setDate(LocalDate.of(2013, 7, 26), "Chapter 3 of Title 9 (July 26, 2013)", MetaBuilder.FRBR_EXPRESSION);
        metaBuilder.setDate(LocalDate.of(2014, 10, 7), "Chapter 3 of Title 9 (July 26, 2013) -- XML Markup", MetaBuilder.FRBR_MANIFESTATION);

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

    @Test
    public void testBody() throws IOException {
        PortionBusinessBuilder portionBuilder = new PortionBusinessBuilder(provider, new Portion(), DefaultHierachyStrategy.COMPLETE) {
            @Override
            protected MetaBuilder newMetaBuilder() {
                return new MetaBuilder(this, SOURCE);
            }
        };
        portionBuilder.setSource(SOURCE);

        portionBuilder.setIncludedIn("/akn/us/act/title_9");

        PortionBodyBuilder bodyBuilder = portionBuilder.getBodyBuilder();

        HierarchyBuilder<Chapter> chapter = bodyBuilder.chapter();
        chapter.num().text("CHAPTER 3—");
        chapter.heading().text("INTER-AMERICAN CONVENTION ON INTERNATIONAL COMMERCIAL ARBITRATION");

        addToc(chapter.intro().toc());
        addSection_0(chapter.section((t ->  {
            t.setGUID(new NoWhiteSpace("idd1d2d527-f639-11e2-8470-abc29ba29c4d"));
            t.setEid(new NoWhiteSpace("sec_301"));
        })));

        addSection_1(chapter.section((t ->  {
            t.setGUID(new NoWhiteSpace("idd1d2d52a-f639-11e2-8470-abc29ba29c4d"));
            t.setEid(new NoWhiteSpace("sec_302"));
        })));
        addSection_2(chapter.section((t ->  {
            t.setGUID(new NoWhiteSpace("idd1d2fc3c-f639-11e2-8470-abc29ba29c4d"));
            t.setEid(new NoWhiteSpace("sec_303"));
        })));
        new DefaultXmlWriterFactoryV3().writePermissive(Channels.newChannel(System.out), portionBuilder.getAkomaNtoso());


        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        XmlProvider.writerFactory(3).writePermissive(Channels.newChannel(baos), portionBuilder.getAkomaNtoso());

        Document expected = ReaderHelper.load("/xml/v3/us_Title9-Chap3-eng.xml");
        Document actual = ReaderHelper.load(new ByteArrayInputStream(baos.toByteArray()));

        XmlUnitHelper.compare(
                expected.getElementsByTagNameNS("http://docs.oasis-open.org/legaldocml/ns/akn/3.0", "intro").item(0),
                actual.getElementsByTagNameNS("http://docs.oasis-open.org/legaldocml/ns/akn/3.0", "intro").item(0)
        );


        for (int i = 0; i < 3 ; i++) {
            XmlUnitHelper.compare(
                    expected.getElementsByTagNameNS("http://docs.oasis-open.org/legaldocml/ns/akn/3.0", "section").item(i),
                    actual.getElementsByTagNameNS("http://docs.oasis-open.org/legaldocml/ns/akn/3.0", "section").item(i)
            );
        }

    }


    private static void addToc(TocBuilder toc) {


        InlineTypeBuilder<TocItem> item;

        /**
         * <tocItem href="" level="1">
         *   <span>Sec.</span>
         * </tocItem>
         */
        toc.item(Uri.EMPTY, "1").span().text("Sec.");

        /**
         * <tocItem href="#sec_301" level="1">
         *   <span>301.</span>
         *   <span>Enforcement of Convention.</span>
         * </tocItem>
         */
        item = toc.item(Uri.valueOf("sec_301"), "1");
        item.span().text("301.");
        item.span().text("Enforcement of Convention.");


        /**
         * <tocItem href="#sec_302" level="1">
         *   <span>302.</span>
         *   <span>Incorporation by reference.</span>
         * </tocItem>
         */
        item = toc.item(Uri.valueOf("sec_302"), "1");
        item.span().text("302.");
        item.span().text("Incorporation by reference.");

        /**
         * <tocItem href="#sec_303" level="1">
         *   <span>303.</span>
         *   <span>Order to compel arbitration; appointment of arbitrators; locale.</span>
         * </tocItem>
         */
        item = toc.item(Uri.valueOf("sec_303"), "1");
        item.span().text("303.");
        item.span().text("Order to compel arbitration; appointment of arbitrators; locale.");

        /**
         * <tocItem href="#sec_304" level="1">
         *   <span>304.</span>
         *   <span>Recognition and enforcement of foreign arbitral decisions and awards; reciprocity.</span>
         * </tocItem>
         */
        item = toc.item(Uri.valueOf("sec_304"), "1");
        item.span().text("304.");
        item.span().text("Recognition and enforcement of foreign arbitral decisions and awards; reciprocity.");

        /**
         * <tocItem href="#sec_305" level="1">
         *   <span>305.</span>
         *   <span>Relationship between the <ref href="/akn/oas/act/1975__b_35/eng@1975-01-30"> Inter-American Convention</ref> and the  <ref href="/akn/un/act/1958NYConvention/eng@1958-06-10">Convention on the
         *     Recognition and Enforcement of Foreign Arbitral Awards of June 10, 1958</ref>.
         *   </span>
         * </tocItem>
         */
        item = toc.item(Uri.valueOf("sec_305"), "1");
        item.span().text("305.");

        InlineTypeBuilder<Span> span = item.span();
        span.text("Relationship between the ").ref(Uri.raw("/akn/oas/act/1975__b_35/eng@1975-01-30".toCharArray())).text(" Inter-American Convention");
        span.text(" and the  ").ref(Uri.raw("/akn/un/act/1958NYConvention/eng@1958-06-10".toCharArray())).text("Convention on the Recognition and Enforcement of Foreign Arbitral Awards of June 10, 1958");
        span.text(".");

        /**
         * <tocItem href="#sec_306" level="1">
         *   <span>306.</span>
         *   <span>Applicable rules of <organization refersTo="#interAmericanCommercialArbitationCommission">Inter-American Commercial Arbitration Commission</organization>.</span>
         * </tocItem>
         */
        item = toc.item(Uri.valueOf("sec_306"), "1");
        item.span().text("306.");
        span = item.span();
        span.text("Applicable rules of ").organization(ORGANIZATION_2).text("Inter-American Commercial Arbitration Commission");
        span.text(".");

        /*
         * <tocItem href="#sec_307" level="1">
         *   <span>307.</span>
         *   <span>Chapter 1; residual application.</span>
         * </tocItem>
         */
        item = toc.item(Uri.valueOf("sec_307"), "1");
        item.span().text("307.");
        item.span().text("Chapter 1; residual application.");
    }

    private static void addSection_0(HierarchyBuilder<Section> section) {

        section.num().text("§ 301.");
        section.heading().text("Enforcement of Convention");

        BlocksBuilder<Content> content = section.content();

        InlineTypeBuilder<P> p = content.p();
        p.text("The ").ref(Uri.raw("/akn/oas/act/1975__b_35/eng@1975-01-30")).text("Inter-American Convention on International Commercial Arbitration of January 30, 1975");
        p.text(", shall be enforced in United States courts in accordance with this").ref(Uri.valueOf("chp_3")).text("chapter");
        p.text(".");

        InlineTypeBuilder<Block> block = content.block(t-> t.setName("sourceCredit"));
        block.text("(Added ").ref(Uri.raw("/akn/us/act/pl_101/369/eng@1990-08-15#sec_1")).text("Pub. L. 101–369, § 1 , Aug. 15, 1990");
        block.text(" , ").ref(Uri.raw("/akn/us/act/stat_104/448")).text("104 Stat. 448");
        block.text(" .)");
    }

    private static void addSection_1(HierarchyBuilder<Section> section) {

        section.num().text("§ 302.");
        section.heading().text("Incorporation by reference");

        BlocksBuilder<Content> content = section.content();
        InlineTypeBuilder<P> p = content.p();

        InlineTypeBuilder<Mref> mref = p.mref();
        mref.text("Sections ").ref(Uri.raw("/akn/us/act/title_9#sec_202")).text("202");
        mref.text(", ").ref(Uri.raw("/akn/us/act/title_9#sec_203")).text("203");
        mref.text(", ").ref(Uri.raw("/akn/us/act/title_9#sec_204")).text("204");
        mref.text(", ").ref(Uri.raw("/akn/us/act/title_9#sec_205")).text("205");
        mref.text(", and ").ref(Uri.raw("/akn/us/act/title_9#sec_207")).text("207");
        mref.text(" of this title");

        p.text(" shall apply to this chapter as if specifically set forth herein, except that for the");
        p.text(" purposes of this chapter “the Convention” shall mean the Inter-American");
        p.text("  Convention.");

        InlineTypeBuilder<Block> block = content.block(t-> t.setName("sourceCredit"));
        block.text("(Added ").ref(Uri.raw("/akn/us/act/pl_101/369/eng@1990-08-15#sec_1")).text("Pub. L. 101–369, § 1 , Aug. 15, 1990");
        block.text(" , ").ref(Uri.raw("/akn/us/act/stat_104/448")).text("104 Stat. 448");
        block.text(" .)");

    }

    private static void addSection_2(HierarchyBuilder<Section> section) {

        section.num().text("§ 303.");
        section.heading().text("Order to compel arbitration; appointment of arbitrators; locale");

        HierarchyBuilder<Subsection> subSection = section.subsection((t ->  {
            t.setGUID(new NoWhiteSpace("idd1d2fc3d-f639-11e2-8470-abc29ba29c4d"));
            t.setEid(new NoWhiteSpace("sec_303__subsec_a"));
        }));
        subSection.num().text("(a)");
        subSection.content().p().text("A court having jurisdiction under this chapter may direct that arbitration be held in")
        .text(" accordance with the agreement at any place therein provided for, whether that place is")
        .text(" within or without the United States. The court may also appoint")
        .text(" arbitrators in accordance with the provisions of the agreement.");

        subSection = section.subsection((t ->  {
            t.setGUID(new NoWhiteSpace("idd1d2fc3e-f639-11e2-8470-abc29ba29c4d"));
            t.setEid(new NoWhiteSpace("sec_303__subsec_b"));
        }));
        subSection.num().text("(b)");
        InlineTypeBuilder<P> p = subSection.content().p();
        p.text("In the event the agreement does not make provision for the place of arbitration or the")
         .text(" appointment of arbitrators, the court shall direct that the arbitration shall be held")
         .text(" and the arbitrators be appointed in accordance with");
        p.ref(Uri.raw("/akn/oas/act/1975__b_35/eng@1975-01-30#art_3")).text("Article 3 of the Inter-American Convention");
        p.text(".");

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
