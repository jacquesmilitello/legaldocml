package io.legaldocml.akn.visitor.stuct;

import io.legaldocml.akn.element.AlternativeReference;
import io.legaldocml.akn.element.Attachment;
import io.legaldocml.akn.element.ComponentRef;
import io.legaldocml.akn.element.DocumentRef;
import io.legaldocml.akn.element.Header;
import io.legaldocml.akn.element.Heading;
import io.legaldocml.akn.element.ImplicitReference;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface OtherVisitior {

    default boolean visitEnter(ImplicitReference implicitReference) {
        return true;
    }

    default void visitLeave(ImplicitReference implicitReference) {
        // default -> nothing to do.
    }

    default boolean visitEnter(AlternativeReference alternativeReference) {
        return true;
    }

    default void visitLeave(AlternativeReference alternativeReference) {
        // default -> nothing to do.
    }

    default boolean visitEnter(Attachment attachment) {
        return true;
    }

    default void visitLeave(Attachment attachment) {
        // default -> nothing to do.
    }

    default void visit(DocumentRef documentRef) {
    }

    default void visit(ComponentRef componentRef) {
    }

    default boolean visitEnter(Heading heading) {
        return true;
    }

    default void visitLeave(Heading heading) {
        // default -> nothing to do.
    }

    default boolean visitEnter(Header header) {
        return true;
    }

    default void visitLeave(Header header) {
        // default -> nothing to do.
    }
}
