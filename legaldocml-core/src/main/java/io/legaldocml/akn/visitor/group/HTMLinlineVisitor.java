package io.legaldocml.akn.visitor.group;

import io.legaldocml.akn.element.A;
import io.legaldocml.akn.element.B;
import io.legaldocml.akn.element.Span;
import io.legaldocml.akn.element.Sub;
import io.legaldocml.akn.element.Sup;
import io.legaldocml.akn.element.U;

/**
 * Visitor for {@link io.legaldocml.akn.group.HTMLinline}
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface HTMLinlineVisitor {

    default boolean visitEnter(B b) {
        return true;
    }

    default void visitLeave(B b) {
    }

    default boolean visitEnter(A a) {
        return true;
    }

    default void visitLeave(A a) {
    }

    default boolean visitEnter(U u) {
        return true;
    }

    default void visitLeave(U u) {
    }

    default boolean visitEnter(Sub sub) {
        return true;
    }

    default void visitLeave(Sub sub) {
    }

    default boolean visitEnter(Sup sup) {
        return true;
    }

    default void visitLeave(Sup sup) {
    }

    default boolean visitEnter(Span span) {
        return true;
    }

    default void visitLeave(Span span) {
    }


}
