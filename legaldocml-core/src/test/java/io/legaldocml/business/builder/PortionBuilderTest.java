package io.legaldocml.business.builder;

import io.legaldocml.ReaderHelper;
import io.legaldocml.XmlUnitHelper;
import io.legaldocml.akn.element.Block;
import io.legaldocml.akn.element.Chapter;
import io.legaldocml.akn.element.Content;
import io.legaldocml.akn.element.Heading;
import io.legaldocml.akn.element.Intro;
import io.legaldocml.akn.element.Mref;
import io.legaldocml.akn.element.P;
import io.legaldocml.akn.element.Paragraph;
import io.legaldocml.akn.element.Portion;
import io.legaldocml.akn.element.Section;
import io.legaldocml.akn.element.Span;
import io.legaldocml.akn.element.Subsection;
import io.legaldocml.akn.element.TLCOrganization;
import io.legaldocml.akn.element.TLCPerson;
import io.legaldocml.akn.element.TLCRole;
import io.legaldocml.akn.element.TocItem;
import io.legaldocml.akn.type.AgentRef;
import io.legaldocml.akn.type.NoWhiteSpace;
import io.legaldocml.akn.type.ReferenceRef;
import io.legaldocml.akn.type.RoleRef;
import io.legaldocml.akn.type.Uri;
import io.legaldocml.business.AknIdentifier;
import io.legaldocml.business.BusinessProvider;
import io.legaldocml.business.builder.element.BlocksBuilder;
import io.legaldocml.business.builder.element.HierarchyBuilder;
import io.legaldocml.business.builder.element.InlineReqTypeBuilder;
import io.legaldocml.business.builder.element.InlineTypeBuilder;
import io.legaldocml.business.builder.element.TocBuilder;
import io.legaldocml.io.XmlProvider;
import io.legaldocml.iso.Iso3166;
import io.legaldocml.iso.Iso639;
import io.legaldocml.model.Language;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.channels.Channels;
import java.time.LocalDate;

import static io.legaldocml.akn.util.TLCFactory.newTLCOrganization;
import static io.legaldocml.akn.util.TLCFactory.newTLCPerson;
import static io.legaldocml.akn.util.TLCFactory.newTLCRole;
import static io.legaldocml.business.builder.MetaBuilder.LOOKUP_FRBR_EXPRESSION;
import static io.legaldocml.business.builder.MetaBuilder.LOOKUP_FRBR_MANIFESTATION;
import static io.legaldocml.business.builder.MetaBuilder.LOOKUP_FRBR_WORK;
import static io.legaldocml.business.util.AknReference.as;
import static io.legaldocml.business.util.AknReference.href;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
class PortionBuilderTest {

    private BusinessProvider provider = BusinessProvider.businessProvider("default");

    private static final AgentRef SOURCE = AgentRef.valueOf("#vergottini");

    private static final TLCOrganization ORGANIZATION = newTLCOrganization(NoWhiteSpace.valueOf("olrc"), Uri.valueOf("/akn/us/ontology/organization/olrc"), "Office of the Law Revision Counsel");
    private static final TLCOrganization ORGANIZATION_2 = newTLCOrganization(NoWhiteSpace.valueOf("interAmericanCommercialArbitationCommission"), Uri.valueOf("/akn/us/ontology/organization/interAmericanCommercialArbitationCommission"), "Inter-American Commercial Arbitration Commission");

    private static final TLCPerson PERSON_VERGOTTINI = newTLCPerson(NoWhiteSpace.valueOf("vergottini"), Uri.valueOf("/akn/us/ontology/person/somebody"), "Grant Vergottini");

    private static final TLCRole ROLE_DRAFTER = newTLCRole(NoWhiteSpace.valueOf("drafter"), Uri.valueOf("/akn/us/ontology/role/drafter"), "Drafter");

    @Test
    void testMeta() throws IOException {
        PortionBusinessBuilder<?, ?> portionBuilder = new PortionBusinessBuilder<PortionBodyBuilder, BusinessBuilderAkomaNtosoContext>(provider, new Portion()) {
            @Override
            protected MetaBuilder newMetaBuilder() {
                return new MetaBuilder(this, SOURCE);
            }

            @Override
            protected PortionBodyBuilder newPortionBodyBuilder() {
                return new PortionBodyBuilder(this, this.getAkomaNtoso().getDocumentType().getPortionBody());
            }
        };

        portionBuilder.setIncludedIn(ReferenceRef.valueOf("/akn/us/act/title_9"));

        MetaBuilder metaBuilder = portionBuilder.getMetaBuilder();

        metaBuilder.setAknIdentifier(new Identifier("/akn/us/usc/title_9/!main", "/akn/us/usc/title_9/eng@2013-07-26/!main", "/akn/us/usc/title_9/eng@2013-07-26~chp_3/!main.xml"));
        metaBuilder.addUri(new Identifier("/akn/us/usc/title_9", "/akn/us/usc/title_9/eng@2013-07-26", "/akn/us/usc/title_9/eng@2013-07-26~chp_3/!main.akn"));
        metaBuilder.setDate(LocalDate.of(1947, 7, 30), "Title 9", LOOKUP_FRBR_WORK);
        metaBuilder.setDate(LocalDate.of(2013, 7, 26), "Chapter 3 of Title 9 (July 26, 2013)", LOOKUP_FRBR_EXPRESSION);
        metaBuilder.setDate(LocalDate.of(2014, 10, 7), "Chapter 3 of Title 9 (July 26, 2013) -- XML Markup", LOOKUP_FRBR_MANIFESTATION);

        metaBuilder.addAuthor(LOOKUP_FRBR_WORK, href(SOURCE, ORGANIZATION), as(SOURCE, ROLE_DRAFTER));
        metaBuilder.addAuthor(LOOKUP_FRBR_EXPRESSION, href(SOURCE, ORGANIZATION)).setAs(RoleRef.valueOf("#editor"));
        metaBuilder.addAuthor(LOOKUP_FRBR_MANIFESTATION, href(SOURCE, PERSON_VERGOTTINI)).setAs(RoleRef.valueOf("generator"));

        metaBuilder.setCountry(Iso3166.UNITED_STATES_OF_AMERICA);
        metaBuilder.addLanguage(Iso639.ENGLISH, Language::getTerminology);

        metaBuilder.setSubType("title");
        metaBuilder.addNumber("title_9");
        metaBuilder.addName("title");
        metaBuilder.setPrescriptive(false);
        metaBuilder.setAuthoritative(true);
        metaBuilder.setPortion("#chp_3");

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
    void testBody() throws IOException {
        PortionBusinessBuilder<?, ?> portionBuilder = new PortionBusinessBuilder<PortionBodyBuilder, BusinessBuilderAkomaNtosoContext>(provider, new Portion()) {
            @Override
            protected MetaBuilder newMetaBuilder() {
                return new MetaBuilder(this, SOURCE);
            }

            @Override
            protected PortionBodyBuilder newPortionBodyBuilder() {
                return new PortionBodyBuilder(this, this.getAkomaNtoso().getDocumentType().getPortionBody());
            }
        };
        portionBuilder.setIncludedIn(ReferenceRef.valueOf("/akn/us/act/title_9"));

        PortionBodyBuilder bodyBuilder = portionBuilder.getBodyBuilder();

        HierarchyBuilder<Chapter> chapter = bodyBuilder.chapter();
        chapter.num().text("CHAPTER 3—");
        chapter.heading().text("INTER-AMERICAN CONVENTION ON INTERNATIONAL COMMERCIAL ARBITRATION");

        addToc(chapter.intro().toc());
        addSection_0(chapter.section((t -> {
            t.setGUID(NoWhiteSpace.valueOf("idd1d2d527-f639-11e2-8470-abc29ba29c4d"));
            t.setEid(NoWhiteSpace.valueOf("sec_301"));
        })));

        addSection_1(chapter.section((t -> {
            t.setGUID(NoWhiteSpace.valueOf("idd1d2d52a-f639-11e2-8470-abc29ba29c4d"));
            t.setEid(NoWhiteSpace.valueOf("sec_302"));
        })));
        addSection_2(chapter.section((t -> {
            t.setGUID(NoWhiteSpace.valueOf("idd1d2fc3c-f639-11e2-8470-abc29ba29c4d"));
            t.setEid(NoWhiteSpace.valueOf("sec_303"));
        })));
        addSection_3(chapter.section((t -> {
            t.setGUID(NoWhiteSpace.valueOf("idd1d2fc40-f639-11e2-8470-abc29ba29c4d"));
            t.setEid(NoWhiteSpace.valueOf("sec_304"));
        })));
        addSection_4(chapter.section((t -> {
            t.setGUID(NoWhiteSpace.valueOf("idd1d32352-f639-11e2-8470-abc29ba29c4d"));
            t.setEid(NoWhiteSpace.valueOf("sec_305"));
        })));

        //new DefaultXmlWriterFactoryV3().writePermissive(Channels.newChannel(System.out), portionBuilder.getAkomaNtoso());

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        XmlProvider.writerFactory(3).writePermissive(Channels.newChannel(baos), portionBuilder.getAkomaNtoso());

        Document expected = ReaderHelper.load("/xml/v3/us_Title9-Chap3-eng.xml");
        Document actual = ReaderHelper.load(new ByteArrayInputStream(baos.toByteArray()));

        XmlUnitHelper.compare(
                expected.getElementsByTagNameNS("http://docs.oasis-open.org/legaldocml/ns/akn/3.0", "intro").item(0),
                actual.getElementsByTagNameNS("http://docs.oasis-open.org/legaldocml/ns/akn/3.0", "intro").item(0)
        );


        for (int i = 0; i < 5; i++) {
            XmlUnitHelper.compare(
                    expected.getElementsByTagNameNS("http://docs.oasis-open.org/legaldocml/ns/akn/3.0", "section").item(i),
                    actual.getElementsByTagNameNS("http://docs.oasis-open.org/legaldocml/ns/akn/3.0", "section").item(i)
            );
        }

    }


    private static void addToc(TocBuilder toc) {


        InlineTypeBuilder<TocItem> item;

        /*
         * <tocItem href="" level="1">
         *   <span>Sec.</span>
         * </tocItem>
         */
        toc.item(Uri.EMPTY, "1").span().text("Sec.");

        /*
         * <tocItem href="#sec_301" level="1">
         *   <span>301.</span>
         *   <span>Enforcement of Convention.</span>
         * </tocItem>
         */
        item = toc.item(Uri.valueOf("#sec_301"), "1");
        item.span().text("301.");
        item.span().text("Enforcement of Convention.");


        /*
         * <tocItem href="#sec_302" level="1">
         *   <span>302.</span>
         *   <span>Incorporation by reference.</span>
         * </tocItem>
         */
        item = toc.item(Uri.valueOf("#sec_302"), "1");
        item.span().text("302.");
        item.span().text("Incorporation by reference.");

        /*
         * <tocItem href="#sec_303" level="1">
         *   <span>303.</span>
         *   <span>Order to compel arbitration; appointment of arbitrators; locale.</span>
         * </tocItem>
         */
        item = toc.item(Uri.valueOf("#sec_303"), "1");
        item.span().text("303.");
        item.span().text("Order to compel arbitration; appointment of arbitrators; locale.");

        /*
         * <tocItem href="#sec_304" level="1">
         *   <span>304.</span>
         *   <span>Recognition and enforcement of foreign arbitral decisions and awards; reciprocity.</span>
         * </tocItem>
         */
        item = toc.item(Uri.valueOf("#sec_304"), "1");
        item.span().text("304.");
        item.span().text("Recognition and enforcement of foreign arbitral decisions and awards; reciprocity.");

        /*
         * <tocItem href="#sec_305" level="1">
         *   <span>305.</span>
         *   <span>Relationship between the <ref href="/akn/oas/act/1975__b_35/eng@1975-01-30"> Inter-American Convention</ref> and the  <ref href="/akn/un/act/1958NYConvention/eng@1958-06-10">Convention on the
         *     Recognition and Enforcement of Foreign Arbitral Awards of June 10, 1958</ref>.
         *   </span>
         * </tocItem>
         */
        item = toc.item(Uri.valueOf("#sec_305"), "1");
        item.span().text("305.");

        InlineTypeBuilder<Span> span = item.span();
        span.text("Relationship between the ").ref(Uri.valueOf("/akn/oas/act/1975__b_35/eng@1975-01-30")).text(" Inter-American Convention");
        span.text(" and the  ").ref(Uri.valueOf("/akn/un/act/1958NYConvention/eng@1958-06-10")).text("Convention on the Recognition and Enforcement of Foreign Arbitral Awards of June 10, 1958");
        span.text(".");

        /*
         * <tocItem href="#sec_306" level="1">
         *   <span>306.</span>
         *   <span>Applicable rules of <organization refersTo="#interAmericanCommercialArbitationCommission">Inter-American Commercial Arbitration Commission</organization>.</span>
         * </tocItem>
         */
        item = toc.item(Uri.valueOf("#sec_306"), "1");
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
        item = toc.item(Uri.valueOf("#sec_307"), "1");
        item.span().text("307.");
        item.span().text("Chapter 1; residual application.");
    }

    private static void addSection_0(HierarchyBuilder<Section> section) {

        section.num().text("§ 301.");
        section.heading().text("Enforcement of Convention");

        BlocksBuilder<Content> content = section.content();

        InlineTypeBuilder<P> p = content.p();
        p.text("The ").ref(Uri.valueOf("/akn/oas/act/1975__b_35/eng@1975-01-30")).text("Inter-American Convention on International Commercial Arbitration of January 30, 1975");
        p.text(", shall be enforced in United States courts in accordance with this").ref(Uri.valueOf("#chp_3")).text("chapter");
        p.text(".");

        InlineTypeBuilder<Block> block = content.block(t -> t.setName("sourceCredit"));
        block.text("(Added ").ref(Uri.valueOf("/akn/us/act/pl_101/369/eng@1990-08-15#sec_1")).text("Pub. L. 101–369, § 1 , Aug. 15, 1990");
        block.text(" , ").ref(Uri.valueOf("/akn/us/act/stat_104/448")).text("104 Stat. 448");
        block.text(" .)");
    }

    private static void addSection_1(HierarchyBuilder<Section> section) {

        section.num().text("§ 302.");
        section.heading().text("Incorporation by reference");

        BlocksBuilder<Content> content = section.content();
        InlineTypeBuilder<P> p = content.p();

        InlineTypeBuilder<Mref> mref = p.mref();
        mref.text("Sections ").ref(Uri.valueOf("/akn/us/act/title_9#sec_202")).text("202");
        mref.text(", ").ref(Uri.valueOf("/akn/us/act/title_9#sec_203")).text("203");
        mref.text(", ").ref(Uri.valueOf("/akn/us/act/title_9#sec_204")).text("204");
        mref.text(", ").ref(Uri.valueOf("/akn/us/act/title_9#sec_205")).text("205");
        mref.text(", and ").ref(Uri.valueOf("/akn/us/act/title_9#sec_207")).text("207");
        mref.text(" of this title");

        p.text(" shall apply to this chapter as if specifically set forth herein, except that for the");
        p.text(" purposes of this chapter “the Convention” shall mean the Inter-American");
        p.text("  Convention.");

        InlineTypeBuilder<Block> block = content.block(t -> t.setName("sourceCredit"));
        block.text("(Added ").ref(Uri.valueOf("/akn/us/act/pl_101/369/eng@1990-08-15#sec_1")).text("Pub. L. 101–369, § 1 , Aug. 15, 1990");
        block.text(" , ").ref(Uri.valueOf("/akn/us/act/stat_104/448")).text("104 Stat. 448");
        block.text(" .)");

    }

    private static void addSection_2(HierarchyBuilder<Section> section) {

        section.num().text("§ 303.");
        section.heading().text("Order to compel arbitration; appointment of arbitrators; locale");

        HierarchyBuilder<Subsection> subSection = section.subsection((t -> {
            t.setGUID(NoWhiteSpace.valueOf("idd1d2fc3d-f639-11e2-8470-abc29ba29c4d"));
            t.setEid(NoWhiteSpace.valueOf("sec_303__subsec_a"));
        }));
        subSection.num().text("(a)");
        subSection.content().p().text("A court having jurisdiction under this chapter may direct that arbitration be held in")
                .text(" accordance with the agreement at any place therein provided for, whether that place is")
                .text(" within or without the United States. The court may also appoint")
                .text(" arbitrators in accordance with the provisions of the agreement.");

        subSection = section.subsection((t -> {
            t.setGUID(NoWhiteSpace.valueOf("idd1d2fc3e-f639-11e2-8470-abc29ba29c4d"));
            t.setEid(NoWhiteSpace.valueOf("sec_303__subsec_b"));
        }));
        subSection.num().text("(b)");
        InlineTypeBuilder<P> p = subSection.content().p();
        p.text("In the event the agreement does not make provision for the place of arbitration or the")
                .text(" appointment of arbitrators, the court shall direct that the arbitration shall be held")
                .text(" and the arbitrators be appointed in accordance with");
        p.ref(Uri.valueOf("/akn/oas/act/1975__b_35/eng@1975-01-30#art_3")).text("Article 3 of the Inter-American Convention");
        p.text(".");

    }

    private static void addSection_3(HierarchyBuilder<Section> section) {

        section.num().text("§ 304.");
        section.heading().text("Recognition and enforcement of foreign arbitral decisions and awards; reciprocity");

        BlocksBuilder<Content> content = section.content();
        InlineTypeBuilder<P> p = content.p();

        p.text("Arbitral decisions or awards made in the territory of a foreign State shall, on the basis of");
        p.text(" reciprocity, be recognized and enforced under this chapter only if that State has ratified");
        p.text(" or acceded to the");
        p.ref(Uri.valueOf("/akn/oas/act/1975__b_35/eng@1975-01-30")).text("Inter-American Convention");
        p.text(".");

        InlineTypeBuilder<Block> block = content.block(t -> t.setName("sourceCredit"));
        block.text("(Added ").ref(Uri.valueOf("/akn/us/act/pl_101/369/eng@1990-08-15#sec_1")).text("Pub. L. 101–369, § 1 , Aug. 15, 1990");
        block.text(" , ").ref(Uri.valueOf("/akn/us/act/stat_104/449")).text("104 Stat. 449");
        block.text(" .)");

    }

    private static void addSection_4(HierarchyBuilder<Section> section) {

        section.num().text("§ 305.");
        InlineReqTypeBuilder<Heading> heading = section.heading();
        heading.text("Relationship between the ").ref(Uri.valueOf("/akn/oas/act/1975__b_35/eng@1975-01-30")).text("Inter-American Convention");
        heading.text(" and the").ref(Uri.valueOf("/akn/un/act/1958NYConvention/eng@1958-06-10")).text("Convention on the Recognition and  Enforcement of Foreign Arbitral Awards of June 10, 1958");

        BlocksBuilder<Intro> intro = section.intro();
        InlineTypeBuilder<P> p = intro.p();
        p.text("When the requirements for application of both the ").ref(Uri.valueOf("/akn/oas/act/1975__b_35/eng@1975-01-30")).text("Inter-American Convention");
        p.text(" and the  ").ref(Uri.valueOf("/akn/un/act/1958NYConvention/eng@1958-06-10")).text("Convention on the Recognition and Enforcement of Foreign Arbitral Awards of June 10, 1958");
        p.text(", are met, determination as to which Convention applies shall, unless otherwise expressly agreed, be made as follows:");


        HierarchyBuilder<Paragraph> paragraph = section.paragraph(t -> {
            t.setGUID(NoWhiteSpace.valueOf("idd1d32353-f639-11e2-8470-abc29ba29c4d"));
            t.setEid(NoWhiteSpace.valueOf("sec_305__para_1"));
        });

        paragraph.num().text("(1)");
        paragraph.content().p().text("If a majority of the parties to the arbitration agreement are citizens of a State or")
                .text(" States that have ratified or acceded to the Inter-American Convention and are member")
                .text(" States of the Organization of American States, the Inter-American Convention shall apply.");

        paragraph = section.paragraph(t -> {
            t.setGUID(NoWhiteSpace.valueOf("idd1d32354-f639-11e2-8470-abc29ba29c4d"));
            t.setEid(NoWhiteSpace.valueOf("sec_305__para_2"));
        });

        paragraph.num().text("(2)");
        p = paragraph.content().p();
        p.text("In all other cases the ").ref(Uri.valueOf("/akn/un/act/1958NYConvention/eng@1958-06-10")).text("Convention  on the Recognition and Enforcement of Foreign Arbitral Awards of June 10, 1958");
        p.text(", shall apply.");
    }


    private static class Identifier extends AknIdentifier {

        private String work;
        private String expression;
        private String manifestation;

        Identifier(String work, String expression, String manifestation) {
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
