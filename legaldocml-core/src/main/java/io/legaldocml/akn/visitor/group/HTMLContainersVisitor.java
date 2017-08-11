package io.legaldocml.akn.visitor.group;

import io.legaldocml.akn.element.Div;

/**
 * Visitor for {@link io.legaldocml.akn.group.HTMLcontainers}
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface HTMLContainersVisitor {

    default boolean visitEnter(Div div) {
        return true;
    }

    default void visitLeave(Div div) {
    }

}
