package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.DocumentType;
import io.legaldocml.akn.group.ANblock;
import io.legaldocml.akn.group.ANcontainers;
import io.legaldocml.akn.group.ANheaderInline;
import io.legaldocml.akn.group.ANhier;
import io.legaldocml.akn.group.ANinline;
import io.legaldocml.akn.group.ANmarker;
import io.legaldocml.akn.group.ANsubFlow;
import io.legaldocml.akn.group.ANsemanticInline;
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
import io.legaldocml.akn.group.SubFlowElements;
import io.legaldocml.akn.group.PreambleContainers;
import io.legaldocml.akn.group.PrefaceContainers;
import io.legaldocml.akn.group.SpeechSection;
import io.legaldocml.akn.group.TLC;
import io.legaldocml.io.XmlReader;

import java.util.Map;
import java.util.function.Supplier;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Groups {

    static final ImmutableMap<String, Supplier<DocumentType>> DOCUMENT_TYPE_V2;

    static final ImmutableMap<String, Supplier<DocumentType>> DOCUMENT_TYPE;

    static {
        DOCUMENT_TYPE_V2 = ImmutableMap.<String, Supplier<DocumentType>>builder()
                .put(Act.ELEMENT, Act::new)
                .put(Amendment.ELEMENT, Amendment::new)
                .put(AmendmentList.ELEMENT, AmendmentList::new)
                .put(Bill.ELEMENT, Bill::new)
                .put(Judgment.ELEMENT, Judgment::new)
                .put(DebateReport.ELEMENT, DebateReport::new)
                .put(Doc.ELEMENT, Doc::new)
                .put(Debate.ELEMENT, Debate::new)
                .put(DocumentCollection.ELEMENT, DocumentCollection::new)
                .put(OfficialGazette.ELEMENT, OfficialGazette::new)
                .build();

        DOCUMENT_TYPE = ImmutableMap.<String, Supplier<DocumentType>>builder()
                .put(Act.ELEMENT, Act::new)
                .put(Amendment.ELEMENT, Amendment::new)
                .put(AmendmentList.ELEMENT, AmendmentList::new)
                .put(Bill.ELEMENT, Bill::new)
                .put(Judgment.ELEMENT, Judgment::new)
                .put(DebateReport.ELEMENT, DebateReport::new)
                .put(Doc.ELEMENT, Doc::new)
                .put(Debate.ELEMENT, Debate::new)
                .put(DocumentCollection.ELEMENT, DocumentCollection::new)
                .put(OfficialGazette.ELEMENT, OfficialGazette::new)
                .put(Portion.ELEMENT, Portion::new)
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
        // &lt;xsd:group ref="ANblock"/&gt;
        builder.putAll(convertSuper(ANblock()));
        // &lt;xsd:group ref="HTMLblock"/&gt;
        builder.putAll(convertSuper(HTMLblock()));
        // &lt;xsd:element ref="foreign"/&gt;
        builder.put(Foreign.ELEMENT, Foreign::new);
        // &lt;xsd:element ref="block"/&gt;
        builder.put(Block.ELEMENT, Block::new);
        return builder.build();
    }

    public static Map<String, Supplier<ANblock>> ANblock() {
        ImmutableMap.Builder<String, Supplier<ANblock>> builder = ImmutableMap.builder();
        //&lt;xsd:element ref="blockList"/&gt;
        builder.put(BlockList.ELEMENT, BlockList::new);
        // &lt;xsd:element ref="blockContainer"/&gt;
        builder.put(BlockContainer.ELEMENT, BlockContainer::new);
        // &lt;xsd:element ref="toc"/&gt;
        builder.put(Toc.ELEMENT, Toc::new);
        // &lt;xsd:element ref="tblock"/&gt;
        builder.put(Tblock.ELEMENT, Tblock::new);
        return builder.build();
    }


    public static Map<String, Supplier<BasicContainers>> basicContainers() {
        ImmutableMap.Builder<String, Supplier<BasicContainers>> builder = ImmutableMap.builder();
        // &lt;xsd:element ref="longTitle"/&gt;
        builder.put(LongTitle.ELEMENT, LongTitle::new);
        // &lt;xsd:element ref="formula"/&gt;
        builder.put(Formula.ELEMENT, Formula::new);
        // &lt;xsd:element ref="container"/&gt;
        builder.put(Container.ELEMENT, Container::new);
        return builder.build();
    }

    public static Map<String, Supplier<PrefaceContainers>> prefaceContainers() {
        ImmutableMap.Builder<String, Supplier<PrefaceContainers>> builder = ImmutableMap.builder();
        // &lt;xsd:element ref="longTitle"/&gt;
        builder.put(LongTitle.ELEMENT, LongTitle::new);
        // &lt;xsd:element ref="formula"/&gt;
        builder.put(Formula.ELEMENT, Formula::new);
        // &lt;xsd:element ref="container"/&gt;
        builder.put(Container.ELEMENT, Container::new);
        return builder.build();
    }


    public static Map<String, Supplier<InlineCM>> inlineCM() {

        ImmutableMap.Builder<String, Supplier<InlineCM>> builder = ImmutableMap.builder();
        //&lt;xsd:group ref="inlineElements"/&gt;
        builder.putAll(convertSuper(inlineElements()));
        //&lt;xsd:group ref="markerElements"/&gt;
        builder.putAll(convertSuper(markerElements()));
        //&lt;xsd:group ref="popupElements"/&gt;
        builder.putAll(convertSuper(popupElements()));
        return builder.build();
    }

    public static Map<String, Supplier<HTMLblock>> HTMLblock() {

        ImmutableMap.Builder<String, Supplier<HTMLblock>> builder = ImmutableMap.builder();
        // &lt;xsd:element ref="ul"/&gt;
        builder.put(Ul.ELEMENT, Ul::new);
        // &lt;xsd:element ref="ol"/&gt;
        builder.put(Ol.ELEMENT, Ol::new);
        // &lt;xsd:element ref="table"/&gt;
        builder.put(Table.ELEMENT, Table::new);
        // &lt;xsd:element ref="p"/&gt;
        builder.put(P.ELEMENT, P::new);
        return builder.build();
    }


    public static Map<String, Supplier<SubFlowElements>> popupElements() {
        ImmutableMap.Builder<String, Supplier<SubFlowElements>> builder = ImmutableMap.builder();
        // &lt;xsd:group ref="ANsubFlow"/&gt;
        builder.putAll(convertSuper(ANsubFlow()));
        // &lt;xsd:element ref="popup"/&gt;
        builder.put(Popup.ELEMENT, Popup::new);
        return builder.build();
    }


    public static Map<String, Supplier<InlineElements>> inlineElements() {

        ImmutableMap.Builder<String, Supplier<InlineElements>> builder = ImmutableMap.builder();
        //&lt;xsd:group ref="ANtitleInlineVisitor"/&gt;
        builder.putAll(convertSuper(ANinline()));
        //&lt;xsd:group ref="HTMLinline"/&gt;
        builder.putAll(convertSuper(HTMLinline()));
        //&lt;xsd:group ref="ANtitleInline"/&gt;
        builder.putAll(convertSuper(ANtitleInline()));
        //&lt;xsd:group ref="ANsemanticInline"/&gt;
        builder.putAll(convertSuper(ANsemanticInline()));
        //&lt;xsd:group ref="ANheaderInline"/&gt;
        builder.putAll(convertSuper(ANheaderInline()));
        //&lt;xsd:group ref="amendmentInline"/&gt;
        builder.putAll(convertSuper(amendmentInline()));
        //&lt;xsd:element ref="inline"/&gt;
        builder.put(Inline.ELEMENT, Inline::new);

        return builder.build();
    }

    private static Map<String, Supplier<HTMLinline>> HTMLinline() {

        ImmutableMap.Builder<String, Supplier<HTMLinline>> builder = ImmutableMap.builder();
        // &lt;xsd:element ref="b"/&gt;
        builder.put(B.ELEMENT, B::new);
        // &lt;xsd:element ref="i"/&gt;
        builder.put(I.ELEMENT, I::new);
        // &lt;xsd:element ref="a"/&gt;
        builder.put(A.ELEMENT, A::new);
        // &lt;xsd:element ref="u"/&gt;
        builder.put(U.ELEMENT, U::new);
        // &lt;xsd:element ref="sub"/&gt;
        builder.put(Sub.ELEMENT, Sub::new);
        // &lt;xsd:element ref="sup"/&gt;
        builder.put(Sup.ELEMENT, Sup::new);
        // &lt;xsd:element ref="abbr"/&gt;
        builder.put(Abbr.ELEMENT, Abbr::new);
        // &lt;xsd:element ref="span"/&gt;
        builder.put(Span.ELEMENT, Span::new);

        return builder.build();
    }

    private static Map<String, Supplier<ANinline>> ANinline() {

        ImmutableMap.Builder<String, Supplier<ANinline>> builder = ImmutableMap.builder();
        // &lt;xsd:element ref="ref"/&gt;
        builder.put(Ref.ELEMENT, Ref::new);
        // &lt;xsd:element ref="mref"/&gt;
        builder.put(Mref.ELEMENT, Mref::new);
        // &lt;xsd:element ref="rref"/&gt;
        builder.put(Rref.ELEMENT, Rref::new);
        // &lt;xsd:element ref="mod"/&gt;
        builder.put(Mod.ELEMENT, Mod::new);
        // &lt;xsd:element ref="mmod"/&gt;
        builder.put(Mmod.ELEMENT, Mmod::new);
        // &lt;xsd:element ref="rmod"/&gt;
        builder.put(Rmod.ELEMENT, Rmod::new);
        // &lt;xsd:element ref="remark"/&gt;
        builder.put(Remark.ELEMENT, Remark::new);
        // &lt;xsd:element ref="recordedTime"/&gt;
        builder.put(RecordedTime.ELEMENT, RecordedTime::new);
        // &lt;xsd:element ref="vote"/&gt;
        builder.put(Vote.ELEMENT, Vote::new);
        // &lt;xsd:element ref="outcome"/&gt;
        builder.put(Outcome.ELEMENT, Outcome::new);
        // &lt;xsd:element ref="ins"/&gt;
        builder.put(Ins.ELEMENT, Ins::new);
        // &lt;xsd:element ref="del"/&gt;
        builder.put(Del.ELEMENT, Del::new);
        // &lt;xsd:element ref="omissis"/&gt;
        builder.put(Omissis.ELEMENT, Omissis::new);
        // &lt;xsd:element ref="embeddedText"/&gt;
        builder.put(EmbeddedText.ELEMENT, EmbeddedText::new);
        // &lt;xsd:element ref="embeddedStructure"/&gt;
        builder.put(EmbeddedStructure.ELEMENT, EmbeddedStructure::new);
        // &lt;xsd:element ref="opinion"/&gt;
        builder.put(Opinion.ELEMENT, Opinion::new);
        // &lt;xsd:element ref="placeholder"/&gt;
        builder.put(Placeholder.ELEMENT, Placeholder::new);
        // &lt;xsd:element ref="fillIn"/&gt;
        builder.put(FillIn.ELEMENT, FillIn::new);
        // &lt;xsd:element ref="decoration"/&gt;
        builder.put(Decoration.ELEMENT, Decoration::new);

        // V2
        builder.put(ExtractText.ELEMENT, ExtractText::new);
        builder.put(ExtractStructure.ELEMENT, ExtractStructure::new);
        return builder.build();
    }

    private static Map<String, Supplier<AmendmentInline>> amendmentInline() {
        ImmutableMap.Builder<String, Supplier<AmendmentInline>> builder = ImmutableMap.builder();
        // &lt;xsd:element ref="affectedDocument" /&gt;
        builder.put(AffectedDocument.ELEMENT, AffectedDocument::new);
        // &lt;xsd:element ref="relatedDocument" /&gt;
        builder.put(RelatedDocument.ELEMENT, RelatedDocument::new);
        // &lt;xsd:element ref="change" /&gt;
        builder.put(Change.ELEMENT, Change::new);
        return builder.build();
    }

    private static Map<String, Supplier<HTMLMarker>> HTMLMarker() {
        ImmutableMap.Builder<String, Supplier<HTMLMarker>> builder = ImmutableMap.builder();
        // &lt;xsd:element ref="img" /&gt;
        builder.put(Img.ELEMENT, Img::new);
        // &lt;xsd:element ref="br"/&gt;
        builder.put(Br.ELEMENT, Br::new);
        return builder.build();
    }

    public static Map<String, Supplier<MarkerElements>> markerElements() {
        ImmutableMap.Builder<String, Supplier<MarkerElements>> builder = ImmutableMap.builder();
        // &lt;xsd:group ref="ANmarker"/&gt;
        builder.putAll(convertSuper(ANmarker()));
        // &lt;xsd:group ref="HTMLmarker"/&gt;
        builder.putAll(convertSuper(HTMLMarker()));
        // &lt;xsd:element ref="marker"/&gt;
        return builder.build();
    }

    public static Map<String, Supplier<ANmarker>> ANmarker() {
        ImmutableMap.Builder<String, Supplier<ANmarker>> builder = ImmutableMap.builder();
        // &lt;xsd:element ref="noteRef" /&gt;
        builder.put(NoteRef.ELEMENT, NoteRef::new);
        // &lt;xsd:element ref="eol" /&gt;
        builder.put(Eol.ELEMENT, Eol::new);
        // &lt;xsd:element ref="eop" /&gt;
        builder.put(Eop.ELEMENT, Eop::new);
        return builder.build();
    }

    public static Map<String, Supplier<ANsemanticInline>> ANsemanticInline() {
        ImmutableMap.Builder<String, Supplier<ANsemanticInline>> builder = ImmutableMap.builder();
        // &lt;xsd:element ref="date" /&gt;
        builder.put(Date.ELEMENT, Date::new);
        // &lt;xsd:element ref="time" /&gt;
        builder.put(Time.ELEMENT, Time::new);
        // &lt;xsd:element ref="person" /&gt;
        builder.put(Person.ELEMENT, Person::new);
        // &lt;xsd:element ref="organization" /&gt;
        builder.put(Organization.ELEMENT, Organization::new);
        // &lt;xsd:element ref="concept" /&gt;
        builder.put(Concept.ELEMENT, Concept::new);
        // &lt;xsd:element ref="object" /&gt;
        builder.put(Object.ELEMENT, Object::new);
        // &lt;xsd:element ref="event" /&gt;
        builder.put(Event.ELEMENT, Event::new);
        // &lt;xsd:element ref="location" /&gt;
        builder.put(Location.ELEMENT, Location::new);
        // &lt;xsd:element ref="process" /&gt;
        builder.put(Process.ELEMENT, Process::new);
        // &lt;xsd:element ref="role" /&gt;
        builder.put(Role.ELEMENT, Role::new);
        // &lt;xsd:element ref="term" /&gt;
        builder.put(Term.ELEMENT, Term::new);
        // &lt;xsd:element ref="quantity" /&gt;
        builder.put(Quantity.ELEMENT, Quantity::new);
        // &lt;xsd:element ref="def" /&gt;
        builder.put(Def.ELEMENT, Def::new);
        // &lt;xsd:element ref="entity" /&gt;
        builder.put(Entity.ELEMENT, Entity::new);
        return builder.build();
    }

    public static Map<String, Supplier<ANheaderInline>> ANheaderInline() {
        ImmutableMap.Builder<String, Supplier<ANheaderInline>> builder = ImmutableMap.builder();
        // &lt;xsd:element ref="courtType"/&gt;
        builder.put(CourtType.ELEMENT, CourtType::new);
        // &lt;xsd:element ref="neutralCitation"/&gt;
        builder.put(NeutralCitation.ELEMENT, NeutralCitation::new);
        // &lt;xsd:element ref="party"/&gt;
        builder.put(Party.ELEMENT, Party::new);
        // &lt;xsd:element ref="judge"/&gt;
        builder.put(Judge.ELEMENT, Judge::new);
        // &lt;xsd:element ref="lawyer"/&gt;
        builder.put(Lawyer.ELEMENT, Lawyer::new);
        // &lt;xsd:element ref="signature"/&gt;
        builder.put(Signature.ELEMENT, Signature::new);
        // &lt;xsd:element ref="argument"/&gt;
        builder.put(Argument.ELEMENT, Argument::new);
        return builder.build();
    }

    public static Map<String, Supplier<ANtitleInline>> ANtitleInline() {
        ImmutableMap.Builder<String, Supplier<ANtitleInline>> builder = ImmutableMap.builder();
        // &lt;xsd:element ref="docType" /&gt;
        builder.put(DocType.ELEMENT, DocType::new);
        //&lt;xsd:element ref="docTitle" /&gt;
        builder.put(DocTitle.ELEMENT, DocTitle::new);
        // &lt;xsd:element ref="docNumber" /&gt;
        builder.put(DocNumber.ELEMENT, DocNumber::new);
        // &lt;xsd:element ref="docProponent" /&gt;
        builder.put(DocProponent.ELEMENT, DocProponent::new);
        // &lt;xsd:element ref="docDate" /&gt;
        builder.put(DocDate.ELEMENT, DocDate::new);
        // &lt;xsd:element ref="legislature" /&gt;
        builder.put(Legislature.ELEMENT, Legislature::new);
        // &lt;xsd:element ref="session" /&gt;
        builder.put(Session.ELEMENT, Session::new);
        // &lt;xsd:element ref="shortTitle"/&gt;
        builder.put(ShortTitle.ELEMENT, ShortTitle::new);
        // &lt;xsd:element ref="docPurpose" /&gt;
        builder.put(DocPurpose.ELEMENT, DocPurpose::new);
        // &lt;xsd:element ref="docCommittee" /&gt;
        builder.put(DocCommittee.ELEMENT, DocCommittee::new);
        // &lt;xsd:element ref="docIntroducer" /&gt;
        builder.put(DocIntroducer.ELEMENT, DocIntroducer::new);
        // &lt;xsd:element ref="docStage" /&gt;
        builder.put(DocStage.ELEMENT, DocStage::new);
        // &lt;xsd:element ref="docStatus" /&gt;
        builder.put(DocStatus.ELEMENT, DocStatus::new);
        // &lt;xsd:element ref="docJurisdiction" /&gt;
        builder.put(DocJurisdiction.ELEMENT, DocJurisdiction::new);
        // &lt;xsd:element ref="docketNumber" /&gt;
        builder.put(DocketNumber.ELEMENT, DocketNumber::new);
        return builder.build();
    }

    private static Map<String, Supplier<ANsubFlow>> ANsubFlow() {
        ImmutableMap.Builder<String, Supplier<ANsubFlow>> builder = ImmutableMap.builder();
        // &lt;xsd:element ref="authorialNote"/&gt;
        builder.put(AuthorialNote.ELEMENT, AuthorialNote::new);
        return builder.build();
    }

    static Map<String, Supplier<DocRef>> docRefs() {
        ImmutableMap.Builder<String, Supplier<DocRef>> builder = ImmutableMap.builder();
        // &lt;xsd:element ref="original"/&gt;
        builder.put(Original.ELEMENT, Original::new);
        // &lt;xsd:element ref="passiveRef"/&gt;
        builder.put(PassiveRef.ELEMENT, PassiveRef::new);
        // &lt;xsd:element ref="activeRef"/&gt;
        builder.put(ActiveRef.ELEMENT, ActiveRef::new);
        // &lt;xsd:element ref="jurisprudence"/&gt;
        builder.put(Jurisprudence.ELEMENT, Jurisprudence::new);
        // &lt;xsd:element ref="hasAttachment"/&gt;
        builder.put(HasAttachment.ELEMENT, HasAttachment::new);
        // &lt;xsd:element ref="attachmentOf"/&gt;
        builder.put(AttachmentOf.ELEMENT, AttachmentOf::new);
        return builder.build();
    }

    static Map<String, Supplier<TLC>> TLCs() {
        ImmutableMap.Builder<String, Supplier<TLC>> builder = ImmutableMap.builder();
        // &lt;xsd:element ref="TLCPerson"/&gt;
        builder.put(TLCPerson.ELEMENT, TLCPerson::new);
        // &lt;xsd:element ref="TLCOrganization"/&gt;
        builder.put(TLCOrganization.ELEMENT, TLCOrganization::new);
        // &lt;xsd:element ref="TLCConcept"/&gt;
        builder.put(TLCConcept.ELEMENT, TLCConcept::new);
        // &lt;xsd:element ref="TLCObject"/&gt;
        builder.put(TLCObject.ELEMENT, TLCObject::new);
        // &lt;xsd:element ref="TLCEvent"/&gt;
        builder.put(TLCEvent.ELEMENT, TLCEvent::new);
        // &lt;xsd:element ref="TLCLocation"/&gt;
        builder.put(TLCLocation.ELEMENT, TLCLocation::new);
        // &lt;xsd:element ref="TLCProcess"/&gt;
        builder.put(TLCProcess.ELEMENT, TLCProcess::new);
        // &lt;xsd:element ref="TLCRole"/&gt;
        builder.put(TLCRole.ELEMENT, TLCRole::new);
        // &lt;xsd:element ref="TLCTerm"/&gt;
        builder.put(TLCTerm.ELEMENT, TLCTerm::new);
        // &lt;xsd:element ref="TLCReference"/&gt;
        builder.put(TLCReference.ELEMENT, TLCReference::new);
        return builder.build();
    }

    public static Map<String, Supplier<PreambleContainers>> preambleContainers() {
        ImmutableMap.Builder<String, Supplier<PreambleContainers>> builder = ImmutableMap.builder();
        // &lt;xsd:element ref="recitals"/&gt;
        builder.put(Recitals.ELEMENT, Recitals::new);
        // &lt;xsd:element ref="citations"/&gt;
        builder.put(Citations.ELEMENT, Citations::new);
        // &lt;xsd:element ref="formula"/&gt;
        builder.put(Formula.ELEMENT, Formula::new);
        // &lt;xsd:element ref="container"/&gt;
        builder.put(Container.ELEMENT, Container::new);
        return builder.build();
    }

    public static Map<String, Supplier<HierElements>> hierElements() {
        ImmutableMap.Builder<String, Supplier<HierElements>> builder = ImmutableMap.builder();
        // &lt;xsd:group ref="ANhier"/&gt;
        builder.putAll(convertSuper(ANhier()));
        // &lt;xsd:element ref="hcontainer"/&gt;
        builder.put(Hcontainer.ELEMENT, Hcontainer::new);
        return builder.build();
    }

    public static Map<String, Supplier<ANhier>> ANhier() {
        ImmutableMap.Builder<String, Supplier<ANhier>> builder = ImmutableMap.builder();
        // &lt;xsd:element ref="clause"/&gt;
        builder.put(Clause.ELEMENT, Clause::new);
        // &lt;xsd:element ref="section"/&gt;
        builder.put(Section.ELEMENT, Section::new);
        // &lt;xsd:element ref="part"/&gt;
        builder.put(Part.ELEMENT, Part::new);
        // &lt;xsd:element ref="paragraph"/&gt;
        builder.put(Paragraph.ELEMENT, Paragraph::new);
        // &lt;xsd:element ref="chapter"/&gt;
        builder.put(Chapter.ELEMENT, Chapter::new);
        // &lt;xsd:element ref="title"/&gt;
        builder.put(Title.ELEMENT, Title::new);
        // &lt;xsd:element ref="article"/&gt;
        builder.put(Article.ELEMENT_ARTICLE, Article::new);
        // &lt;xsd:element ref="book"/&gt;
        builder.put(Book.ELEMENT, Book::new);
        // &lt;xsd:element ref="tome"/&gt;
        builder.put(Tome.ELEMENT, Tome::new);
        // &lt;xsd:element ref="division"/&gt;
        builder.put(Division.ELEMENT, Division::new);
        // &lt;xsd:element ref="list"/&gt;
        builder.put(List.ELEMENT, List::new);
        // &lt;xsd:element ref="point"/&gt;
        builder.put(Point.ELEMENT, Point::new);
        // &lt;xsd:element ref="indent"/&gt;
        builder.put(Indent.ELEMENT, Indent::new);
        // &lt;xsd:element ref="alinea"/&gt;
        builder.put(Alinea.ELEMENT, Alinea::new);
        // &lt;xsd:element ref="rule"/&gt;
        builder.put(Rule.ELEMENT, Rule::new);
        // &lt;xsd:element ref="subrule"/&gt;
        builder.put(SubRule.ELEMENT, SubRule::new);
        // &lt;xsd:element ref="proviso"/&gt;
        builder.put(Proviso.ELEMENT, Proviso::new);
        // &lt;xsd:element ref="subsection"/&gt;
        builder.put(SubSection.ELEMENT, SubSection::new);
        // &lt;xsd:element ref="subpart"/&gt;
        builder.put(SubPart.ELEMENT, SubPart::new);
        // &lt;xsd:element ref="subparagraph"/&gt;
        builder.put(SubParagraph.ELEMENT, SubParagraph::new);
        // &lt;xsd:element ref="subchapter"/&gt;
        builder.put(SubChapter.ELEMENT, SubChapter::new);
        // &lt;xsd:element ref="subtitle"/&gt;
        builder.put(SubTitle.ELEMENT, SubTitle::new);
        // &lt;xsd:element ref="subdivision"/&gt;
        builder.put(SubDivision.ELEMENT, SubDivision::new);
        // &lt;xsd:element ref="subclause"/&gt;
        builder.put(SubClause.ELEMENT, SubClause::new);
        // &lt;xsd:element ref="sublist"/&gt;
        builder.put(SubList.ELEMENT, SubList::new);
        // &lt;xsd:element ref="level"/&gt;
        builder.put(Level.ELEMENT, Level::new);
        // &lt;xsd:element ref="transitional"/&gt;
        builder.put(Transitional.ELEMENT, Transitional::new);
        return builder.build();
    }

    public static Map<String, Supplier<ContainerElements>> containerElements() {
        ImmutableMap.Builder<String, Supplier<ContainerElements>> builder = ImmutableMap.builder();
        // &lt;xsd:group ref="speechSection"/&gt;
        builder.putAll(convertSuper(speechSection()));
        // &lt;xsd:group ref="HTMLcontainers"/&gt;
        builder.putAll(convertSuper(HTMLcontainers()));
        // &lt;xsd:element ref="container"/&gt;
        builder.put(Container.ELEMENT, Container::new);
        return builder.build();
    }

    public static Map<String, Supplier<HTMLcontainers>> HTMLcontainers() {
        ImmutableMap.Builder<String, Supplier<HTMLcontainers>> builder = ImmutableMap.builder();
        // &lt;xsd:element ref="div"/&gt;
        builder.put(Div.ELEMENT, Div::new);
        return builder.build();
    }

    public static Map<String, Supplier<ANcontainers>> ANcontainers() {
        ImmutableMap.Builder<String, Supplier<ANcontainers>> builder = ImmutableMap.builder();
        //&lt;xsd:element ref="speechGroup"/
        builder.put(SpeechGroup.ELEMENT, SpeechGroup::new);
        // &lt;xsd:element ref="speech"/&gt;
        builder.put(Speech.ELEMENT, Speech::new);
        // &lt;xsd:element ref="question"/&gt;
        builder.put(Question.ELEMENT, Question::new);
        // &lt;xsd:element ref="answer"/&gt;
        builder.put(Answer.ELEMENT, Answer::new);
        // &lt;xsd:element ref="other"/&gt;
        builder.put(Other.ELEMENT, Other::new);
        // &lt;xsd:element ref="scene"/&gt;
        builder.put(Scene.ELEMENT, Scene::new);
        // &lt;xsd:element ref="narrative"/&gt;
        builder.put(Narrative.ELEMENT, Narrative::new);
        // &lt;xsd:element ref="summary"/&gt;
        builder.put(Summary.ELEMENT, Summary::new);
        return builder.build();
    }

    public static Map<String, Supplier<SpeechSection>> speechSection() {
        ImmutableMap.Builder<String, Supplier<SpeechSection>> builder = ImmutableMap.builder();
        // &lt;xsd:element ref="administrationOfOath"/&gt;
        builder.put(AdministrationOfOath.ELEMENT, AdministrationOfOath::new);
        // &lt;xsd:element ref="rollCall"/&gt;
        builder.put(RollCall.ELEMENT, RollCall::new);
        // &lt;xsd:element ref="prayers"/&gt;
        builder.put(Prayers.ELEMENT, Prayers::new);
        // &lt;xsd:element ref="oralStatements"/&gt;
        builder.put(OralStatements.ELEMENT, OralStatements::new);
        // &lt;xsd:element ref="writtenStatements"/&gt;
        builder.put(WrittenStatements.ELEMENT, WrittenStatements::new);
        // &lt;xsd:element ref="personalStatements"/&gt;
        builder.put(PersonalStatements.ELEMENT, PersonalStatements::new);
        // &lt;xsd:element ref="ministerialStatements"/&gt;
        builder.put(MinisterialStatements.ELEMENT, MinisterialStatements::new);
        // &lt;xsd:element ref="resolutions"/&gt;
        builder.put(Resolutions.ELEMENT, Resolutions::new);
        // &lt;xsd:element ref="nationalInterest"/&gt;
        builder.put(NationalInterest.ELEMENT, NationalInterest::new);
        // &lt;xsd:element ref="declarationOfVote"/&gt;
        builder.put(DeclarationOfVote.ELEMENT, DeclarationOfVote::new);
        // &lt;xsd:element ref="communication"/&gt;
        builder.put(Communication.ELEMENT, Communication::new);
        // &lt;xsd:element ref="petitions"/&gt;
        builder.put(Petitions.ELEMENT, Petitions::new);
        // &lt;xsd:element ref="papers"/&gt;
        builder.put(Papers.ELEMENT, Papers::new);
        // &lt;xsd:element ref="noticesOfMotion"/&gt;
        builder.put(NoticesOfMotion.ELEMENT, NoticesOfMotion::new);
        // &lt;xsd:element ref="questions"/&gt;
        builder.put(Questions.ELEMENT, Questions::new);
        // &lt;xsd:element ref="address"/&gt;
        builder.put(Address.ELEMENT, Address::new);
        // &lt;xsd:element ref="proceduralMotions"/&gt;
        builder.put(ProceduralMotions.ELEMENT, ProceduralMotions::new);
        // &lt;xsd:element ref="pointOfOrder"/&gt;
        builder.put(PointOfOrder.ELEMENT, PointOfOrder::new);
        // &lt;xsd:element ref="adjournment"/&gt;
        builder.put(Adjournment.ELEMENT, Adjournment::new);
        // &lt;xsd:element ref="debateSection"/&gt;
        builder.put(DebateSection.ELEMENT, DebateSection::new);
        return builder.build();
    }


    public static ImmutableMap<String, Supplier<DocumentType>> getDocumentTypes(XmlReader reader) {
        if (reader.getContext().getAkoXmlModule().getVersion() == 2) {
            return DOCUMENT_TYPE_V2;
        }
        return DOCUMENT_TYPE;
    }
}
