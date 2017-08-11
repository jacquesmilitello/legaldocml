package io.legaldocml.akn.visitor.el;

import io.legaldocml.akn.element.Container;

/**
 * Visitor for {@link io.legaldocml.akn.element.Container}
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface ContainerVisitor {

    default boolean visitEnter(Container container) {
        return true;
    }

    default void visitLeave(Container container) {
    }

}