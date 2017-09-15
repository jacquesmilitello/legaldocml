package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknElements;
import io.legaldocml.akn.AkomaNtosoContext;
import io.legaldocml.akn.DocumentType;
import io.legaldocml.akn.group.ANblock;
import io.legaldocml.akn.group.ANcontainers;
import io.legaldocml.akn.group.ANheaderInline;
import io.legaldocml.akn.group.ANhier;
import io.legaldocml.akn.group.ANinline;
import io.legaldocml.akn.group.ANmarker;
import io.legaldocml.akn.group.ANsemanticInline;
import io.legaldocml.akn.group.ANsubFlow;
import io.legaldocml.akn.group.ANtitleInline;
import io.legaldocml.akn.group.AmendmentInline;
import io.legaldocml.akn.group.BasicContainers;
import io.legaldocml.akn.group.BlockElements;
import io.legaldocml.akn.group.ContainerElements;
import io.legaldocml.akn.group.DocRef;
import io.legaldocml.akn.group.HTMLMarker;
import io.legaldocml.akn.group.HTMLblock;
import io.legaldocml.akn.group.HTMLcontainers;
import io.legaldocml.akn.group.HTMLinline;
import io.legaldocml.akn.group.HierElements;
import io.legaldocml.akn.group.InlineCM;
import io.legaldocml.akn.group.InlineElements;
import io.legaldocml.akn.group.MarkerElements;
import io.legaldocml.akn.group.PreambleContainers;
import io.legaldocml.akn.group.PrefaceContainers;
import io.legaldocml.akn.group.SpeechSection;
import io.legaldocml.akn.group.SubFlowElements;
import io.legaldocml.akn.group.TLC;
import io.legaldocml.io.XmlReader;

import java.util.Map;
import java.util.function.Supplier;

import static io.legaldocml.akn.AknElements.*;
import static io.legaldocml.akn.AknElements.ACT;
import static io.legaldocml.akn.AknElements.AMENDMENT;
import static io.legaldocml.akn.AknElements.AMENDMENT_LIST;
import static io.legaldocml.akn.AknElements.BILL;
import static io.legaldocml.akn.AknElements.BLOCK;
import static io.legaldocml.akn.AknElements.BLOCK_CONTAINER;
import static io.legaldocml.akn.AknElements.BLOCK_LIST;
import static io.legaldocml.akn.AknElements.BOOK;
import static io.legaldocml.akn.AknElements.CHANGE;
import static io.legaldocml.akn.AknElements.CHAPTER;
import static io.legaldocml.akn.AknElements.CITATIONS;
import static io.legaldocml.akn.AknElements.CLAUSE;
import static io.legaldocml.akn.AknElements.COMMUNICATION;
import static io.legaldocml.akn.AknElements.CONCEPT;
import static io.legaldocml.akn.AknElements.CONTAINER;
import static io.legaldocml.akn.AknElements.COURT_TYPE;
import static io.legaldocml.akn.AknElements.DATE;
import static io.legaldocml.akn.AknElements.DEBATE;
import static io.legaldocml.akn.AknElements.DEBATE_REPORT;
import static io.legaldocml.akn.AknElements.DEBATE_SECTION;
import static io.legaldocml.akn.AknElements.DECLARATION_OF_VOTE;
import static io.legaldocml.akn.AknElements.DECORATION;
import static io.legaldocml.akn.AknElements.DEF;
import static io.legaldocml.akn.AknElements.DEL;
import static io.legaldocml.akn.AknElements.DIV;
import static io.legaldocml.akn.AknElements.DIVISION;
import static io.legaldocml.akn.AknElements.DOC;
import static io.legaldocml.akn.AknElements.DOCKET_NUMBER;
import static io.legaldocml.akn.AknElements.DOCUMENT_COLLECTION;
import static io.legaldocml.akn.AknElements.DOC_AUTHORITY;
import static io.legaldocml.akn.AknElements.DOC_COMMITTEE;
import static io.legaldocml.akn.AknElements.DOC_DATE;
import static io.legaldocml.akn.AknElements.DOC_INTRODUCER;
import static io.legaldocml.akn.AknElements.DOC_JURISDICTION;
import static io.legaldocml.akn.AknElements.DOC_NUMBER;
import static io.legaldocml.akn.AknElements.DOC_PROPONENT;
import static io.legaldocml.akn.AknElements.DOC_PURPOSE;
import static io.legaldocml.akn.AknElements.DOC_STAGE;
import static io.legaldocml.akn.AknElements.DOC_STATUS;
import static io.legaldocml.akn.AknElements.DOC_TITLE;
import static io.legaldocml.akn.AknElements.DOC_TYPE;
import static io.legaldocml.akn.AknElements.EMBEDDED_STRUCTURE;
import static io.legaldocml.akn.AknElements.EMBEDDED_TEXT;
import static io.legaldocml.akn.AknElements.ENTITY;
import static io.legaldocml.akn.AknElements.EOL;
import static io.legaldocml.akn.AknElements.EOP;
import static io.legaldocml.akn.AknElements.EVENT;
import static io.legaldocml.akn.AknElements.FILL_IN;
import static io.legaldocml.akn.AknElements.FOREIGN;
import static io.legaldocml.akn.AknElements.FORMULA;
import static io.legaldocml.akn.AknElements.HAS_ATTACHMENT;
import static io.legaldocml.akn.AknElements.H_CONTAINER;
import static io.legaldocml.akn.AknElements.INDENT;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Groups {

    static final ImmutableMap<String, Supplier<DocumentType>> DOCUMENT_TYPE_V2;

    static final ImmutableMap<String, Supplier<DocumentType>> DOCUMENT_TYPE;

    static {
        DOCUMENT_TYPE_V2 = ImmutableMap.<String, Supplier<DocumentType>>builder()
                .put(ACT, Act::new)
                .put(AMENDMENT, Amendment::new)
                .put(AMENDMENT_LIST, AmendmentList::new)
                .put(BILL, Bill::new)
                .put(JUDGMENT, Judgment::new)
                .put(DEBATE_REPORT, DebateReport::new)
                .put(DOC, Doc::new)
                .put(DEBATE, Debate::new)
                .put(DOCUMENT_COLLECTION, DocumentCollection::new)
                .put(OFFICIAL_GAZETTE, OfficialGazette::new)
                .build();

        DOCUMENT_TYPE = ImmutableMap.<String, Supplier<DocumentType>>builder()
                .put(ACT, Act::new)
                .put(AMENDMENT, Amendment::new)
                .put(AMENDMENT_LIST, AmendmentList::new)
                .put(BILL, Bill::new)
                .put(JUDGMENT, Judgment::new)
                .put(DEBATE_REPORT, DebateReport::new)
                .put(DOC, Doc::new)
                .put(DEBATE, Debate::new)
                .put(DOCUMENT_COLLECTION, DocumentCollection::new)
                .put(OFFICIAL_GAZETTE, OfficialGazette::new)
                .put(PORTION, Portion::new)
                .put(Statement.ELEMENT, Statement::new)
                .build();
    }

    /**
     * Hide Constructor.
     */
    private Groups() {
    }


    public static <T, E extends T> Map<String, Supplier<E>> convert(Map<String, Supplier<T>> toConvert) {
        ImmutableMap.Builder<String, Supplier<E>> builder = ImmutableMap.builder();
        for (Map.Entry<String, Supplier<T>> entry : toConvert.entrySet()) {
            builder.put(entry.getKey(), (Supplier<E>) entry.getValue());
        }
        return builder.build();
    }

    public static <T, E extends T> Map<String, Supplier<T>> convertSuper(Map<String, Supplier<E>> toConvert) {
        ImmutableMap.Builder<String, Supplier<T>> builder = ImmutableMap.builder();
        for (Map.Entry<String, Supplier<E>> entry : toConvert.entrySet()) {
            builder.put(entry.getKey(), (Supplier<T>) entry.getValue());
        }
        return builder.build();
    }


    public static Map<String, Supplier<BlockElements>> blockElements() {
        ImmutableMap.Builder<String, Supplier<BlockElements>> builder = ImmutableMap.builder();
        // <xsd:group ref="ANblock"/>
        builder.putAll(convertSuper(ANblock()));
        // <xsd:group ref="HTMLblock"/>
        builder.putAll(convertSuper(HTMLblock()));
        // <xsd:element ref="foreign"/>
        builder.put(FOREIGN, Foreign::new);
        // <xsd:element ref="block"/>
        builder.put(BLOCK, Block::new);
        return builder.build();
    }

    public static Map<String, Supplier<ANblock>> ANblock() {
        ImmutableMap.Builder<String, Supplier<ANblock>> builder = ImmutableMap.builder();
        //<xsd:element ref="blockList"/>
        builder.put(BLOCK_LIST, BlockList::new);
        // <xsd:element ref="blockContainer"/>
        builder.put(BLOCK_CONTAINER, BlockContainer::new);
        // <xsd:element ref="toc"/>
        builder.put(TOC, Toc::new);
        // <xsd:element ref="tblock"/>
        builder.put(Tblock.ELEMENT, Tblock::new);
        return builder.build();
    }


    public static Map<String, Supplier<BasicContainers>> basicContainers() {
        ImmutableMap.Builder<String, Supplier<BasicContainers>> builder = ImmutableMap.builder();
        // <xsd:element ref="longTitle"/>
        builder.put(LONG_TITLE, LongTitle::new);
        // <xsd:element ref="formula"/>
        builder.put(FORMULA, Formula::new);
        // <xsd:element ref="container"/>
        builder.put(CONTAINER, Container::new);
        return builder.build();
    }

    public static Map<String, Supplier<PrefaceContainers>> prefaceContainers() {
        ImmutableMap.Builder<String, Supplier<PrefaceContainers>> builder = ImmutableMap.builder();
        // <xsd:element ref="longTitle"/>
        builder.put(LONG_TITLE, LongTitle::new);
        // <xsd:element ref="formula"/>
        builder.put(FORMULA, Formula::new);
        // <xsd:element ref="container"/>
        builder.put(CONTAINER, Container::new);
        return builder.build();
    }


    public static Map<String, Supplier<InlineCM>> inlineCM() {

        ImmutableMap.Builder<String, Supplier<InlineCM>> builder = ImmutableMap.builder();
        //<xsd:group ref="inlineElements"/>
        builder.putAll(convertSuper(inlineElements()));
        //<xsd:group ref="markerElements"/>
        builder.putAll(convertSuper(markerElements()));
        //<xsd:group ref="popupElements"/>
        builder.putAll(convertSuper(popupElements()));
        return builder.build();
    }

    public static Map<String, Supplier<HTMLblock>> HTMLblock() {

        ImmutableMap.Builder<String, Supplier<HTMLblock>> builder = ImmutableMap.builder();
        // <xsd:element ref="ul"/>
        builder.put(Ul.ELEMENT, Ul::new);
        // <xsd:element ref="ol"/>
        builder.put(OL, Ol::new);
        // <xsd:element ref="table"/>
        builder.put(Table.ELEMENT, Table::new);
        // <xsd:element ref="p"/>
        builder.put(AknElements.P, P::new);
        return builder.build();
    }


    public static Map<String, Supplier<SubFlowElements>> popupElements() {
        ImmutableMap.Builder<String, Supplier<SubFlowElements>> builder = ImmutableMap.builder();
        // <xsd:group ref="ANsubFlow"/>
        builder.putAll(convertSuper(ANsubFlow()));
        // <xsd:element ref="popup"/>
        //builder.put(Popup.ELEMENT, Popup::new);
         //<xsd:element ref="subFlow"/>
        builder.put(SubFlow.ELEMENT, SubFlow::new);
        return builder.build();
    }


    public static Map<String, Supplier<InlineElements>> inlineElements() {

        ImmutableMap.Builder<String, Supplier<InlineElements>> builder = ImmutableMap.builder();
        //<xsd:group ref="ANtitleInlineVisitor"/>
        builder.putAll(convertSuper(ANinline()));
        //<xsd:group ref="HTMLinline"/>
        builder.putAll(convertSuper(HTMLinline()));
        //<xsd:group ref="ANtitleInline"/>
        builder.putAll(convertSuper(ANtitleInline()));
        //<xsd:group ref="ANsemanticInline"/>
        builder.putAll(convertSuper(ANsemanticInline()));
        //<xsd:group ref="ANheaderInline"/>
        builder.putAll(convertSuper(ANheaderInline()));
        //<xsd:group ref="amendmentInline"/>
        builder.putAll(convertSuper(amendmentInline()));
        //<xsd:element ref="inline"/>
        builder.put(INLINE, Inline::new);

        return builder.build();
    }

    private static Map<String, Supplier<HTMLinline>> HTMLinline() {

        ImmutableMap.Builder<String, Supplier<HTMLinline>> builder = ImmutableMap.builder();
        // <xsd:element ref="b"/>
        builder.put(B, B::new);
        // <xsd:element ref="i"/>
        builder.put(AknElements.I, I::new);
        // <xsd:element ref="a"/>
        builder.put(A, A::new);
        // <xsd:element ref="u"/>
        builder.put(U.ELEMENT, U::new);
        // <xsd:element ref="sub"/>
        builder.put(Sub.ELEMENT, Sub::new);
        // <xsd:element ref="sup"/>
        builder.put(Sup.ELEMENT, Sup::new);
        // <xsd:element ref="abbr"/>
        builder.put(ABBR, Abbr::new);
        // <xsd:element ref="span"/>
        builder.put(Span.ELEMENT, Span::new);

        return builder.build();
    }

    private static Map<String, Supplier<ANinline>> ANinline() {

        ImmutableMap.Builder<String, Supplier<ANinline>> builder = ImmutableMap.builder();
        // <xsd:element ref="ref"/>
        builder.put(Ref.ELEMENT, Ref::new);
        // <xsd:element ref="mref"/>
        builder.put(MREF, Mref::new);
        // <xsd:element ref="rref"/>
        builder.put(Rref.ELEMENT, Rref::new);
        // <xsd:element ref="mod"/>
        builder.put(MOD, Mod::new);
        // <xsd:element ref="mmod"/>
        builder.put(MMOD, Mmod::new);
        // <xsd:element ref="rmod"/>
        builder.put(Rmod.ELEMENT, Rmod::new);
        // <xsd:element ref="remark"/>
        builder.put(Remark.ELEMENT, Remark::new);
        // <xsd:element ref="recordedTime"/>
        builder.put(RecordedTime.ELEMENT, RecordedTime::new);
        // <xsd:element ref="vote"/>
        builder.put(Vote.ELEMENT, Vote::new);
        // <xsd:element ref="outcome"/>
        builder.put(OUTCOME, Outcome::new);
        // <xsd:element ref="ins"/>
        builder.put(INS, Ins::new);
        // <xsd:element ref="del"/>
        builder.put(DEL, Del::new);
        // <xsd:element ref="omissis"/>
        builder.put(OMISSIS, Omissis::new);
        // <xsd:element ref="embeddedText"/>
        builder.put(EMBEDDED_TEXT, EmbeddedText::new);
        // <xsd:element ref="embeddedStructure"/>
        builder.put(EMBEDDED_STRUCTURE, EmbeddedStructure::new);
        // <xsd:element ref="opinion"/>
        builder.put(OPINION, Opinion::new);
        // <xsd:element ref="placeholder"/>
        builder.put(PLACE_HOLDER, Placeholder::new);
        // <xsd:element ref="fillIn"/>
        builder.put(FILL_IN, FillIn::new);
        // <xsd:element ref="decoration"/>
        builder.put(DECORATION, Decoration::new);

        // V2
        builder.put(ExtractText.ELEMENT, ExtractText::new);
        builder.put(ExtractStructure.ELEMENT, ExtractStructure::new);
        return builder.build();
    }

    private static Map<String, Supplier<AmendmentInline>> amendmentInline() {
        ImmutableMap.Builder<String, Supplier<AmendmentInline>> builder = ImmutableMap.builder();
        // <xsd:element ref="affectedDocument" />
        builder.put(AFFECTED_DOCUMENT, AffectedDocument::new);
        // <xsd:element ref="relatedDocument" />
        builder.put(RelatedDocument.ELEMENT, RelatedDocument::new);
        // <xsd:element ref="change" />
        builder.put(CHANGE, Change::new);
        return builder.build();
    }

    private static Map<String, Supplier<HTMLMarker>> HTMLMarker() {
        ImmutableMap.Builder<String, Supplier<HTMLMarker>> builder = ImmutableMap.builder();
        // <xsd:element ref="img" />
        builder.put(IMG, Img::new);
        // <xsd:element ref="br"/>
        builder.put(BR, Br::new);
        return builder.build();
    }

    public static Map<String, Supplier<MarkerElements>> markerElements() {
        ImmutableMap.Builder<String, Supplier<MarkerElements>> builder = ImmutableMap.builder();
        // <xsd:group ref="ANmarker"/>
        builder.putAll(convertSuper(ANmarker()));
        // <xsd:group ref="HTMLmarker"/>
        builder.putAll(convertSuper(HTMLMarker()));
        // <xsd:element ref="marker"/>
        builder.put(MARKER, Marker::new);
        return builder.build();
    }

    public static Map<String, Supplier<ANmarker>> ANmarker() {
        ImmutableMap.Builder<String, Supplier<ANmarker>> builder = ImmutableMap.builder();
        // <xsd:element ref="noteRef" />
        builder.put(NOTE_REF, NoteRef::new);
        // <xsd:element ref="eol" />
        builder.put(EOL, Eol::new);
        // <xsd:element ref="eop" />
        builder.put(EOP, Eop::new);
        return builder.build();
    }

    public static Map<String, Supplier<ANsemanticInline>> ANsemanticInline() {
        ImmutableMap.Builder<String, Supplier<ANsemanticInline>> builder = ImmutableMap.builder();
        // <xsd:element ref="date" />
        builder.put(DATE, Date::new);
        // <xsd:element ref="time" />
        builder.put(Time.ELEMENT, Time::new);
        // <xsd:element ref="person" />
        builder.put(PERSON, Person::new);
        // <xsd:element ref="organization" />
        builder.put(ORGANIZATION, Organization::new);
        // <xsd:element ref="concept" />
        builder.put(CONCEPT, Concept::new);
        // <xsd:element ref="object" />
        builder.put(OBJECT, Object::new);
        // <xsd:element ref="event" />
        builder.put(EVENT, Event::new);
        // <xsd:element ref="location" />
        builder.put(LOCATION, Location::new);
        // <xsd:element ref="process" />
        builder.put(Process.ELEMENT, Process::new);
        // <xsd:element ref="role" />
        builder.put(Role.ELEMENT, Role::new);
        // <xsd:element ref="term" />
        builder.put(Term.ELEMENT, Term::new);
        // <xsd:element ref="quantity" />
        builder.put(Quantity.ELEMENT, Quantity::new);
        // <xsd:element ref="def" />
        builder.put(DEF, Def::new);
        // <xsd:element ref="entity" />
        builder.put(ENTITY, Entity::new);
        return builder.build();
    }

    public static Map<String, Supplier<ANheaderInline>> ANheaderInline() {
        ImmutableMap.Builder<String, Supplier<ANheaderInline>> builder = ImmutableMap.builder();
        // <xsd:element ref="courtType"/>
        builder.put(COURT_TYPE, CourtType::new);
        // <xsd:element ref="neutralCitation"/>
        builder.put(NEUTRAL_CITATION, NeutralCitation::new);
        // <xsd:element ref="party"/>
        builder.put(PARTY, Party::new);
        // <xsd:element ref="judge"/>
        builder.put(JUDGE, Judge::new);
        // <xsd:element ref="lawyer"/>
        builder.put(LAWYER, Lawyer::new);
        // <xsd:element ref="signature"/>
        builder.put(Signature.ELEMENT, Signature::new);
        // <xsd:element ref="argument"/>
        builder.put(ARGUMENT, Argument::new);
        return builder.build();
    }

    public static Map<String, Supplier<ANtitleInline>> ANtitleInline() {
        ImmutableMap.Builder<String, Supplier<ANtitleInline>> builder = ImmutableMap.builder();
        // <xsd:element ref="docType" />
        builder.put(DOC_TYPE, DocType::new);
        //<xsd:element ref="docTitle" />
        builder.put(DOC_TITLE, DocTitle::new);
        // <xsd:element ref="docNumber" />
        builder.put(DOC_NUMBER, DocNumber::new);
        // <xsd:element ref="docProponent" />
        builder.put(DOC_PROPONENT, DocProponent::new);
        // <xsd:element ref="docDate" />
        builder.put(DOC_DATE, DocDate::new);
        // <xsd:element ref="legislature" />
        builder.put(LEGISLATURE, Legislature::new);
        // <xsd:element ref="session" />
        builder.put(Session.ELEMENT, Session::new);
        // <xsd:element ref="shortTitle"/>
        builder.put(ShortTitle.ELEMENT, ShortTitle::new);
        // <xsd:element ref="docAuthority"/>
        builder.put(DOC_AUTHORITY, DocAuthority::new);
        // <xsd:element ref="docPurpose" />
        builder.put(DOC_PURPOSE, DocPurpose::new);
        // <xsd:element ref="docCommittee" />
        builder.put(DOC_COMMITTEE, DocCommittee::new);
        // <xsd:element ref="docIntroducer" />
        builder.put(DOC_INTRODUCER, DocIntroducer::new);
        // <xsd:element ref="docStage" />
        builder.put(DOC_STAGE, DocStage::new);
        // <xsd:element ref="docStatus" />
        builder.put(DOC_STATUS, DocStatus::new);
        // <xsd:element ref="docJurisdiction" />
        builder.put(DOC_JURISDICTION, DocJurisdiction::new);
        // <xsd:element ref="docketNumber" />
        builder.put(DOCKET_NUMBER, DocketNumber::new);
        return builder.build();
    }

    private static Map<String, Supplier<ANsubFlow>> ANsubFlow() {
        ImmutableMap.Builder<String, Supplier<ANsubFlow>> builder = ImmutableMap.builder();
        // <xsd:element ref="authorialNote"/>
        builder.put(AUTHORIAL_NOTE, AuthorialNote::new);
        return builder.build();
    }

    static Map<String, Supplier<DocRef>> docRefs() {
        ImmutableMap.Builder<String, Supplier<DocRef>> builder = ImmutableMap.builder();
        // <xsd:element ref="original"/>
        builder.put(ORIGINAL, Original::new);
        // <xsd:element ref="passiveRef"/>
        builder.put(PASSIVE_REF, PassiveRef::new);
        // <xsd:element ref="activeRef"/>
        builder.put(ACTIVE_REF, ActiveRef::new);
        // <xsd:element ref="jurisprudence"/>
        builder.put(JURISPRUDENCE, Jurisprudence::new);
        // <xsd:element ref="hasAttachment"/>
        builder.put(HAS_ATTACHMENT, HasAttachment::new);
        // <xsd:element ref="attachmentOf"/>
        builder.put(ATTACHMENT_OF, AttachmentOf::new);
        return builder.build();
    }

    static Map<String, Supplier<TLC>> TLCs() {
        ImmutableMap.Builder<String, Supplier<TLC>> builder = ImmutableMap.builder();
        // <xsd:element ref="TLCPerson"/>
        builder.put(TLCPerson.ELEMENT, TLCPerson::new);
        // <xsd:element ref="TLCOrganization"/>
        builder.put(TLCOrganization.ELEMENT, TLCOrganization::new);
        // <xsd:element ref="TLCConcept"/>
        builder.put(TLCConcept.ELEMENT, TLCConcept::new);
        // <xsd:element ref="TLCObject"/>
        builder.put(TLCObject.ELEMENT, TLCObject::new);
        // <xsd:element ref="TLCEvent"/>
        builder.put(TLCEvent.ELEMENT, TLCEvent::new);
        // <xsd:element ref="TLCLocation"/>
        builder.put(TLCLocation.ELEMENT, TLCLocation::new);
        // <xsd:element ref="TLCProcess"/>
        builder.put(TLCProcess.ELEMENT, TLCProcess::new);
        // <xsd:element ref="TLCRole"/>
        builder.put(TLCRole.ELEMENT, TLCRole::new);
        // <xsd:element ref="TLCTerm"/>
        builder.put(TLCTerm.ELEMENT, TLCTerm::new);
        // <xsd:element ref="TLCReference"/>
        builder.put(TLCReference.ELEMENT, TLCReference::new);
        return builder.build();
    }

    public static Map<String, Supplier<PreambleContainers>> preambleContainers() {
        ImmutableMap.Builder<String, Supplier<PreambleContainers>> builder = ImmutableMap.builder();
        // <xsd:element ref="recitals"/>
        builder.put(Recitals.ELEMENT, Recitals::new);
        // <xsd:element ref="citations"/>
        builder.put(CITATIONS, Citations::new);
        // <xsd:element ref="formula"/>
        builder.put(FORMULA, Formula::new);
        // <xsd:element ref="container"/>
        builder.put(CONTAINER, Container::new);
        return builder.build();
    }

    public static Map<String, Supplier<HierElements>> hierElements() {
        ImmutableMap.Builder<String, Supplier<HierElements>> builder = ImmutableMap.builder();
        // <xsd:group ref="ANhier"/>
        builder.putAll(convertSuper(ANhier()));
        // <xsd:element ref="hcontainer"/>
        builder.put(H_CONTAINER, Hcontainer::new);
        return builder.build();
    }

    public static Map<String, Supplier<ANhier>> ANhier() {
        ImmutableMap.Builder<String, Supplier<ANhier>> builder = ImmutableMap.builder();
        // <xsd:element ref="clause"/>
        builder.put(CLAUSE, Clause::new);
        // <xsd:element ref="section"/>
        builder.put(Section.ELEMENT, Section::new);
        // <xsd:element ref="part"/>
        builder.put(PART, Part::new);
        // <xsd:element ref="paragraph"/>
        builder.put(PARAGRAPH, Paragraph::new);
        // <xsd:element ref="chapter"/>
        builder.put(CHAPTER, Chapter::new);
        // <xsd:element ref="title"/>
        builder.put(Title.ELEMENT, Title::new);
        // <xsd:element ref="article"/>
        builder.put(ARTICLE, Article::new);
        // <xsd:element ref="book"/>
        builder.put(BOOK, Book::new);
        // <xsd:element ref="tome"/>
        builder.put(Tome.ELEMENT, Tome::new);
        // <xsd:element ref="division"/>
        builder.put(DIVISION, Division::new);
        // <xsd:element ref="list"/>
        builder.put(LIST, List::new);
        // <xsd:element ref="point"/>
        builder.put(POINT, Point::new);
        // <xsd:element ref="indent"/>
        builder.put(INDENT, Indent::new);
        // <xsd:element ref="alinea"/>
        builder.put(ALINEA, Alinea::new);
        // <xsd:element ref="rule"/>
        builder.put(Rule.ELEMENT, Rule::new);
        // <xsd:element ref="subrule"/>
        builder.put(SubRule.ELEMENT, SubRule::new);
        // <xsd:element ref="proviso"/>
        builder.put(Proviso.ELEMENT, Proviso::new);
        // <xsd:element ref="subsection"/>
        builder.put(SubSection.ELEMENT, SubSection::new);
        // <xsd:element ref="subpart"/>
        builder.put(SubPart.ELEMENT, SubPart::new);
        // <xsd:element ref="subparagraph"/>
        builder.put(SubParagraph.ELEMENT, SubParagraph::new);
        // <xsd:element ref="subchapter"/>
        builder.put(SubChapter.ELEMENT, SubChapter::new);
        // <xsd:element ref="subtitle"/>
        builder.put(SubTitle.ELEMENT, SubTitle::new);
        // <xsd:element ref="subdivision"/>
        builder.put(SubDivision.ELEMENT, SubDivision::new);
        // <xsd:element ref="subclause"/>
        builder.put(SubClause.ELEMENT, SubClause::new);
        // <xsd:element ref="sublist"/>
        builder.put(SubList.ELEMENT, SubList::new);
        // <xsd:element ref="level"/>
        builder.put(LEVEL, Level::new);
        // <xsd:element ref="transitional"/>
        builder.put(Transitional.ELEMENT, Transitional::new);
        return builder.build();
    }

    public static Map<String, Supplier<ContainerElements>> containerElements() {
        ImmutableMap.Builder<String, Supplier<ContainerElements>> builder = ImmutableMap.builder();
        // <xsd:group ref="speechSection"/>
        builder.putAll(convertSuper(speechSection()));
        // <xsd:group ref="HTMLcontainers"/>
        builder.putAll(convertSuper(HTMLcontainers()));
        // <xsd:element ref="container"/>
        builder.put(CONTAINER, Container::new);
        return builder.build();
    }

    public static Map<String, Supplier<HTMLcontainers>> HTMLcontainers() {
        ImmutableMap.Builder<String, Supplier<HTMLcontainers>> builder = ImmutableMap.builder();
        // <xsd:element ref="div"/>
        builder.put(DIV, Div::new);
        return builder.build();
    }

    public static Map<String, Supplier<ANcontainers>> ANcontainers() {
        ImmutableMap.Builder<String, Supplier<ANcontainers>> builder = ImmutableMap.builder();
        //<xsd:element ref="speechGroup"/
        builder.put(SpeechGroup.ELEMENT, SpeechGroup::new);
        // <xsd:element ref="speech"/>
        builder.put(Speech.ELEMENT, Speech::new);
        // <xsd:element ref="question"/>
        builder.put(Question.ELEMENT, Question::new);
        // <xsd:element ref="answer"/>
        builder.put(ANSWER, Answer::new);
        // <xsd:element ref="other"/>
        builder.put(OTHER, Other::new);
        // <xsd:element ref="scene"/>
        builder.put(Scene.ELEMENT, Scene::new);
        // <xsd:element ref="narrative"/>
        builder.put(NARRATIVE, Narrative::new);
        // <xsd:element ref="summary"/>
        builder.put(Summary.ELEMENT, Summary::new);
        return builder.build();
    }

    public static Map<String, Supplier<SpeechSection>> speechSection() {
        ImmutableMap.Builder<String, Supplier<SpeechSection>> builder = ImmutableMap.builder();
        // <xsd:element ref="administrationOfOath"/>
        builder.put(ADMINISTATION_OF_OATH, AdministrationOfOath::new);
        // <xsd:element ref="rollCall"/>
        builder.put(RollCall.ELEMENT, RollCall::new);
        // <xsd:element ref="prayers"/>
        builder.put(PRAYERS, Prayers::new);
        // <xsd:element ref="oralStatements"/>
        builder.put(ORAL_STATEMENTS, OralStatements::new);
        // <xsd:element ref="writtenStatements"/>
        builder.put(WrittenStatements.ELEMENT, WrittenStatements::new);
        // <xsd:element ref="personalStatements"/>
        builder.put(PERSONAL_STATEMENTS, PersonalStatements::new);
        // <xsd:element ref="ministerialStatements"/>
        builder.put(MINISTERIAL_STATEMENTS, MinisterialStatements::new);
        // <xsd:element ref="resolutions"/>
        builder.put(Resolutions.ELEMENT, Resolutions::new);
        // <xsd:element ref="nationalInterest"/>
        builder.put(NATIONAL_INTEREST, NationalInterest::new);
        // <xsd:element ref="declarationOfVote"/>
        builder.put(DECLARATION_OF_VOTE, DeclarationOfVote::new);
        // <xsd:element ref="communication"/>
        builder.put(COMMUNICATION, Communication::new);
        // <xsd:element ref="petitions"/>
        builder.put(PETITIONS, Petitions::new);
        // <xsd:element ref="papers"/>
        builder.put(PAPERS, Papers::new);
        // <xsd:element ref="noticesOfMotion"/>
        builder.put(NOTICES_OF_MOTION, NoticesOfMotion::new);
        // <xsd:element ref="questions"/>
        builder.put(Questions.ELEMENT, Questions::new);
        // <xsd:element ref="address"/>
        builder.put(ADDRESS, Address::new);
        // <xsd:element ref="proceduralMotions"/>
        builder.put(ProceduralMotions.ELEMENT, ProceduralMotions::new);
        // <xsd:element ref="pointOfOrder"/>
        builder.put(POINT_OF_ORDER, PointOfOrder::new);
        // <xsd:element ref="adjournment"/>
        builder.put(ADJOURNMENT, Adjournment::new);
        // <xsd:element ref="debateSection"/>
        builder.put(DEBATE_SECTION, DebateSection::new);
        return builder.build();
    }


    public static ImmutableMap<String, Supplier<DocumentType>> getDocumentTypes(XmlReader reader) {
        if (reader.<AkomaNtosoContext>getContext().getAkoXmlModule().getVersion() == 2) {
            return DOCUMENT_TYPE_V2;
        }
        return DOCUMENT_TYPE;
    }
}
