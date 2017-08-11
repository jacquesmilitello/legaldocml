package io.legaldocml.akn.visitor.group;

import io.legaldocml.akn.element.AffectedDocument;
import io.legaldocml.akn.element.Change;
import io.legaldocml.akn.element.RelatedDocument;

/**
 * Visitor for {@link io.legaldocml.akn.group.AmendmentInline}
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface AmendmentInlineVisitor {

    default boolean visitEnter(RelatedDocument relatedDocument) {
        return true;
    }

    default void visitLeave(RelatedDocument RelatedDocument) {
        // default -> nothing to do.
    }

    default boolean visitEnter(AffectedDocument affectedDocument) {
        return true;
    }

    default void visitLeave(AffectedDocument affectedDocument) {
        // default -> nothing to do.
    }

    default boolean visitEnter(Change change) {
        return true;
    }

    default void visitLeave(Change change) {
        // default -> nothing to do.
    }

}