package io.legaldocml.akn.visitor.group;

import io.legaldocml.akn.element.Ol;
import io.legaldocml.akn.element.P;
import io.legaldocml.akn.element.Table;
import io.legaldocml.akn.element.Ul;

/**
 * Visitor for {@link io.legaldocml.akn.group.HTMLblock}
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface HTMLBlockVisitor {

    default boolean visitEnter(Ul ul) {
        return true;
    }

    default void visitLeave(Ul ul) {
    }

    default boolean visitEnter(Ol ol) {
        return true;
    }

    default void visitLeave(Ol ol) {
    }

    default boolean visitEnter(Table table) {
        return true;
    }

    default void visitLeave(Table table) {
    }

    default boolean visitEnter(P p) {
        return true;
    }

    default void visitLeave(P p) {
    }

}
