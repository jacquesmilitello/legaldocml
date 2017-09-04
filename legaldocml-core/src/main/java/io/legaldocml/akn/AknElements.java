package io.legaldocml.akn;

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
     * Element for {@link io.legaldocml.akn.element.FRBRthis}
     */
    public static final String FRBRTHIS = "FRBRthis";

    /**
     * Element for {@link io.legaldocml.akn.element.Br}
     */
    public static final String BR = "br";

    /**
     * Element for {@link io.legaldocml.akn.element.Alinea}
     */
    public static final String ALINEA = "alinea";
}
