package io.legaldocml.akn;

import com.google.common.collect.ImmutableMap;

import java.lang.reflect.Field;

/**
 * All default attributes name.
 *
 * Based on the pattern :
 * According to Joshua Bloch, author of "Effective Java":
 * ```
 * The constant interface pattern is a poor use of interfaces.
 * That a class uses some constants internally is an implementation detail.
 * Implementing a constant interface causes this implementation detail to leak into the class's exported API.
 * It is of no consequence to the users of a class that the class implements a constant interface. In fact, it may even
 * confuse them. Worse, it represents a commitment: if in a future release the class is modified so that it no longer
 * needs to use the constants, it still must implement the interface to ensure binary compatibility.
 * If a nonfinal class implements a constant interface all of its subclasses will have their namespaces polluted by the
 * constants in the interface.
 * ```
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class AknElements {

    private AknElements() {
    }

    private static final ImmutableMap<String, Class<? extends AknObject>> ELEMENTS;

    static {
        ImmutableMap.Builder<String, Class<? extends AknObject>> builder = ImmutableMap.builder();
        for (Field field : AknElements.class.getDeclaredFields()) {
            if (!String.class.equals(field.getType())) {
                continue;
            }
            try {
                String value = field.get(null).toString();
                if ("akomaNtoso".equals(value)) {
                    builder.put(value, AkomaNtoso.class);
                } else {
                    //noinspection unchecked
                    //noinspection unchecked
                    builder.put(value, (Class<? extends AknObject>) Class.forName("io.legaldocml.akn.element." + firstLetterUpperCase(value)));
                }
            } catch (Exception cause) {
                throw new IllegalStateException("Failed to get value from field [" + field + "]", cause);
            }
        }

        ELEMENTS = builder.build();
    }


    public static final String AKOMANTOSO = "akomaNtoso";

    /**
     * Element for {@link io.legaldocml.akn.element.A}
     */
    public static final String A = "a";

    /**
     * Element for {@link io.legaldocml.akn.element.Abbr}
     */
    public static final String ABBR = "abbr";

    /**
     * Element for {@link io.legaldocml.akn.element.Act}
     */
    public static final String ACT = "act";

    /**
     * Element for {@link io.legaldocml.akn.element.ActiveModifications}
     */
    public static final String ACTIVE_MODIFICATIONS = "activeModifications";

    /**
     * Element for {@link io.legaldocml.akn.element.ActiveRef}
     */
    public static final String ACTIVE_REF = "activeRef";

    /**
     * Element for {@link io.legaldocml.akn.element.Address}
     */
    public static final String ADDRESS = "address";

    /**
     * Element for {@link io.legaldocml.akn.element.Adjournment}
     */
    public static final String ADJOURNMENT = "adjournment";

    /**
     * Element for {@link io.legaldocml.akn.element.AdministrationOfOath}
     */
    public static final String ADMINISTATION_OF_OATH = "administrationOfOath";

    /**
     * Element for {@link io.legaldocml.akn.element.AffectedDocument}
     */
    public static final String AFFECTED_DOCUMENT = "affectedDocument";

    /**
     * Element for {@link io.legaldocml.akn.element.AlternativeReference}
     */
    public static final String ALTERNATIVE_REFERENCE = "alternativeReference";

    /**
     * Element for {@link io.legaldocml.akn.element.Alinea}
     */
    public static final String ALINEA = "alinea";

    /**
     * Element for {@link io.legaldocml.akn.element.Amendment}
     */
    public static final String AMENDMENT = "amendment";

    /**
     * Element for {@link io.legaldocml.akn.element.AmendmentBody}
     */
    public static final String AMENDMENT_BODY = "amendmentBody";

    /**
     * Element for {@link io.legaldocml.akn.element.AmendmentContent}
     */
    public static final String AMENDMENT_CONTENT = "amendmentContent";

    /**
     * Element for {@link io.legaldocml.akn.element.AmendmentHeading}
     */
    public static final String AMENDMENT_HEADING = "amendmentHeading";

    /**
     * Element for {@link io.legaldocml.akn.element.AmendmentJustification}
     */
    public static final String AMENDMENT_JUSTIFICATION = "amendmentJustification";

    /**
     * Element for {@link io.legaldocml.akn.element.AmendmentList}
     */
    public static final String AMENDMENT_LIST = "amendmentList";

    /**
     * Element for {@link io.legaldocml.akn.element.AmendmentReference}
     */
    public static final String AMENDMENT_REFERENCE = "amendmentReference";

    /**
     * Element for {@link io.legaldocml.akn.element.Analysis}
     */
    public static final String ANALYSIS = "analysis";

    /**
     * Element for {@link io.legaldocml.akn.element.Answer}
     */
    public static final String ANSWER = "answer";

    /**
     * Element for {@link io.legaldocml.akn.element.Application}
     */
    public static final String APPLICATION = "application";

    /**
     * Element for {@link io.legaldocml.akn.element.Applies}
     */
    public static final String APPLIES = "applies";

    /**
     * Element for {@link io.legaldocml.akn.element.Argument}
     */
    public static final String ARGUMENT = "argument";

    /**
     * Element for {@link io.legaldocml.akn.element.Arguments}
     */
    public static final String ARGUMENTS = "arguments";

    /**
     * Element for {@link io.legaldocml.akn.element.Article}
     */
    public static final String ARTICLE = "article";

    /**
     * Element for {@link io.legaldocml.akn.element.Attachment}
     */
    public static final String ATTACHMENT = "attachment";

    /**
     * Element for {@link io.legaldocml.akn.element.Attachment}
     */
    public static final String ATTACHMENT_OF = "attachmentOf";

    /**
     * Element for :
     * * - {@link io.legaldocml.akn.element.AttachmentsV2}
     * * - {@link io.legaldocml.akn.element.AttachmentsV3}
     */
    public static final String ATTACHMENTS = "attachments";

    /**
     * Element for {@link io.legaldocml.akn.element.AuthorialNote}
     */
    public static final String AUTHORIAL_NOTE = "authorialNote";

    /**
     * Element for {@link io.legaldocml.akn.element.B}
     */
    public static final String B = "b";

    /**
     * Element for {@link io.legaldocml.akn.element.B}
     */
    public static final String BACKGROUND = "background";

    /**
     * Element for {@link io.legaldocml.akn.element.Block}
     */
    public static final String BLOCK = "block";

    /**
     * Element for {@link io.legaldocml.akn.element.BlockContainer}
     */
    public static final String BLOCK_CONTAINER = "blockContainer";

    /**
     * Element for {@link io.legaldocml.akn.element.BlockList}
     */
    public static final String BLOCK_LIST = "blockList";

    /**
     * Element for {@link io.legaldocml.akn.element.Body}
     */
    public static final String BODY = "body";

    /**
     * Element for {@link io.legaldocml.akn.element.Book}
     */
    public static final String BOOK = "book";

    /**
     * Element for {@link io.legaldocml.akn.element.Br}
     */
    public static final String BR = "br";

    /**
     * Element for {@link io.legaldocml.akn.element.Bill}
     */
    public static final String BILL = "bill";

    /**
     * Element for {@link io.legaldocml.akn.element.Caption}
     */
    public static final String CAPTION = "caption";

    /**
     * Element for {@link io.legaldocml.akn.element.Change}
     */
    public static final String CHANGE = "change";

    /**
     * Element for {@link io.legaldocml.akn.element.Chapter}
     */
    public static final String CHAPTER = "chapter";

    /**
     * Element for {@link io.legaldocml.akn.element.Citation}
     */
    public static final String CITATION = "citation";

    /**
     * Element for {@link io.legaldocml.akn.element.Citations}
     */
    public static final String CITATIONS = "citations";

    /**
     * Element for {@link io.legaldocml.akn.element.Classification}
     */
    public static final String CLASSIFICATION = "classification";

    /**
     * Element for {@link io.legaldocml.akn.element.Clause}
     */
    public static final String CLAUSE = "clause";

    /**
     * Element for {@link io.legaldocml.akn.element.Clause}
     */
    public static final String COLLECTION_BODY = "collectionBody";

    /**
     * Element for {@link io.legaldocml.akn.element.Communication}
     */
    public static final String COMMUNICATION = "communication";

    /**
     * Element for {@link io.legaldocml.akn.element.Component}
     */
    public static final String COMPONENT = "component";

    /**
     * Element for {@link io.legaldocml.akn.element.ComponentData}
     */
    public static final String COMPONENT_DATA = "componentData";

    /**
     * Element for {@link io.legaldocml.akn.element.ComponentInfo}
     */
    public static final String COMPONENT_INFO = "componentInfo";

    /**
     * Element for {@link io.legaldocml.akn.element.ComponentRef}
     */
    public static final String COMPONENT_REF = "componentRef";

    /**
     * Element for {@link io.legaldocml.akn.element.Components}
     */
    public static final String COMPONENTS = "components";

    /**
     * Element for {@link io.legaldocml.akn.element.Concept}
     */
    public static final String CONCEPT = "concept";

    /**
     * Element for {@link io.legaldocml.akn.element.Conclusions}
     */
    public static final String CONCLUSIONS = "conclusions";

    /**
     * Element for {@link io.legaldocml.akn.element.Condition}
     */
    public static final String CONDITION = "condition";

    /**
     * Element for {@link io.legaldocml.akn.element.Container}
     */
    public static final String CONTAINER = "container";

    /**
     * Element for {@link io.legaldocml.akn.element.Content}
     */
    public static final String CONTENT = "content";

    /**
     * Element for {@link io.legaldocml.akn.element.Contrasts}
     */
    public static final String CONTRASTS = "contrasts";

    /**
     * Element for {@link io.legaldocml.akn.element.Count}
     */
    public static final String COUNT = "count";

    /**
     * Element for {@link io.legaldocml.akn.element.CourtType}
     */
    public static final String COURT_TYPE = "courtType";

    /**
     * Element for {@link io.legaldocml.akn.element.CoverPage}
     */
    public static final String COVER_PAGE = "coverPage";

    /**
     * Element for {@link io.legaldocml.akn.element.CrossHeading}
     */
    public static final String CROSS_HEADING = "crossHeading";

    /**
     * Element for {@link io.legaldocml.akn.element.Date}
     */
    public static final String DATE = "date";

    /**
     * Element for {@link io.legaldocml.akn.element.Debate}
     */
    public static final String DEBATE = "debate";

    /**
     * Element for {@link io.legaldocml.akn.element.DebateBody}
     */
    public static final String DEBATE_BODY = "debateBody";

    /**
     * Element for {@link io.legaldocml.akn.element.DebateReport}
     */
    public static final String DEBATE_REPORT = "debateReport";

    /**
     * Element for {@link io.legaldocml.akn.element.DebateSection}
     */
    public static final String DEBATE_SECTION = "debateSection";

    /**
     * Element for {@link io.legaldocml.akn.element.Decision}
     */
    public static final String DECISION = "decision";

    /**
     * Element for {@link io.legaldocml.akn.element.DeclarationOfVote}
     */
    public static final String DECLARATION_OF_VOTE = "declarationOfVote";

    /**
     * Element for {@link io.legaldocml.akn.element.Decoration}
     */
    public static final String DECORATION = "decoration";

    /**
     * Element for {@link io.legaldocml.akn.element.Def}
     */
    public static final String DEF = "def";

    /**
     * Element for {@link io.legaldocml.akn.element.Del}
     */
    public static final String DEL = "del";

    /**
     * Element for {@link io.legaldocml.akn.element.Derogates}
     */
    public static final String DEROGATES = "derogates";

    /**
     * Element for {@link io.legaldocml.akn.element.Destination}
     */
    public static final String DESTINATION = "destination";

    /**
     * Element for {@link io.legaldocml.akn.element.DissentsFrom}
     */
    public static final String DISSENTS_FROM = "dissentsFrom";

    /**
     * Element for {@link io.legaldocml.akn.element.FRBRthis}
     */
    public static final String FRBRTHIS = "FRBRthis";

    /**
     * Element for {@link io.legaldocml.akn.element.Identification}
     */
    public static final String IDENTIFICATION = "identification";

    /**
     * Element for {@link io.legaldocml.akn.element.Meta}
     */
    public static final String META = "meta";

    /**
     * Element for {@link io.legaldocml.akn.element.Parliamentary}
     */
    public static final String PARLIAMENTARY = "parliamentary";

    /**
     * Element for {@link io.legaldocml.akn.element.Voting}
     */
    public static final String VOTING = "voting";


    public static boolean exists(String name) {
        return ELEMENTS.containsKey(name);
    }


    public static Class<? extends AknObject> getAknClass(String name) {
        return ELEMENTS.get(name);
    }


    private static String firstLetterUpperCase(String value) {
        StringBuilder builder = new StringBuilder();
        builder.append(Character.toUpperCase(value.charAt(0)));
        if (value.length() > 1) {
            builder.append(value.substring(1));
        }
        return builder.toString();
    }
}
