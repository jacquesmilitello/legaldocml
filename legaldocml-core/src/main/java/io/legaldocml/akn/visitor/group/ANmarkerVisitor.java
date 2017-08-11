package io.legaldocml.akn.visitor.group;

import io.legaldocml.akn.element.Eol;
import io.legaldocml.akn.element.Eop;
import io.legaldocml.akn.element.NoteRef;

/**
 * Visitor for {@link io.legaldocml.akn.group.ANmarker}
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface ANmarkerVisitor {

    default boolean visitEnter(NoteRef noteRef) {
        return true;
    }

    default void visitLeave(NoteRef noteRef) {
    }

    default boolean visitEnter(Eol eol) {
        return true;
    }

    default void visitLeave(Eol eol) {
    }

    default boolean visitEnter(Eop eop) {
        return true;
    }

    default void visitLeave(Eop eop) {
    }

}