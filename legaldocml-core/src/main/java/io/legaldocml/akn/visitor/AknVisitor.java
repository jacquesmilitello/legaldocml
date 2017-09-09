package io.legaldocml.akn.visitor;

import io.legaldocml.akn.AkomaNtoso;
import io.legaldocml.akn.element.StringInlineCM;
import io.legaldocml.akn.visitor.group.ANblockVisitor;
import io.legaldocml.akn.visitor.group.ANcontainersVisitor;
import io.legaldocml.akn.visitor.group.ANheaderInlineVisitor;
import io.legaldocml.akn.visitor.group.ANhierVisitor;
import io.legaldocml.akn.visitor.group.ANmarkerVisitor;
import io.legaldocml.akn.visitor.group.ANsemanticInlineVisitor;
import io.legaldocml.akn.visitor.group.ANtitleInlineVisitor;
import io.legaldocml.akn.visitor.group.ANtitleVisitor;
import io.legaldocml.akn.visitor.group.AmendmentInlineVisitor;
import io.legaldocml.akn.visitor.group.BasicContainersVisitor;
import io.legaldocml.akn.visitor.group.BlockElementsVisitor;
import io.legaldocml.akn.visitor.group.ContainerElementsVisitor;
import io.legaldocml.akn.visitor.group.DocumentTypeVisitor;
import io.legaldocml.akn.visitor.group.HTMLBlockVisitor;
import io.legaldocml.akn.visitor.group.HTMLinlineVisitor;
import io.legaldocml.akn.visitor.group.InlineCMVisitor;
import io.legaldocml.akn.visitor.group.InlineElementsVisitor;
import io.legaldocml.akn.visitor.group.JudgmentBlockVisitor;
import io.legaldocml.akn.visitor.group.PrefaceContainersVisitor;
import io.legaldocml.akn.visitor.group.SpeechSectionVisitor;
import io.legaldocml.akn.visitor.stuct.BodyVisitor;
import io.legaldocml.akn.visitor.stuct.FRBRVisitor;
import io.legaldocml.akn.visitor.stuct.OtherVisitior;
import io.legaldocml.akn.visitor.stuct.StructureVisitor;
import io.legaldocml.akn.visitor.stuct.TLCVisitor;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface AknVisitor extends DocumentTypeVisitor, ANhierVisitor, ANblockVisitor, ANtitleVisitor, BlockElementsVisitor,
        HTMLBlockVisitor, ANtitleInlineVisitor, AmendmentInlineVisitor, ANheaderInlineVisitor, ANsemanticInlineVisitor,
        BasicContainersVisitor, SpeechSectionVisitor, ANcontainersVisitor, ANmarkerVisitor, JudgmentBlockVisitor,
        ContainerElementsVisitor, HTMLinlineVisitor, PrefaceContainersVisitor,
        InlineCMVisitor, InlineElementsVisitor,
        StructureVisitor, BodyVisitor, OtherVisitior, FRBRVisitor, TLCVisitor {

    default boolean visitEnter(AkomaNtoso<?> akomaNtoso) {
        return true;
    }

    default void visitLeave(AkomaNtoso<?> akomaNtoso) {
        // default -> nothing to do.
    }

    default void visit(StringInlineCM stringInlineCM) {
    }


}
