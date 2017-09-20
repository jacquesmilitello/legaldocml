package io.legaldocml.akn.visitor.group;

import io.legaldocml.akn.element.AttachmentOf;
import io.legaldocml.akn.element.HasAttachment;
import io.legaldocml.akn.element.Jurisprudence;
import io.legaldocml.akn.element.PassiveRef;

/**
 * Visitor for {@link io.legaldocml.akn.group.DocRef}
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface DocRefVisitor {


    default void visit(PassiveRef passiveRef) {
    }

    default void visit(HasAttachment attachment) {
    }

    default void visit(AttachmentOf attachmentOf) {
    }

    default void visit(Jurisprudence jurisprudence) {
    }
}
