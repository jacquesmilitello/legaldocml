package io.legaldocml.akn.visitor.el;

import io.legaldocml.akn.element.Content;

/**
 * Visitor for {@link io.legaldocml.akn.element.Content}
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface ContentVisitor {

    default boolean visitEnter(Content content) {
        return true;
    }

    default void visitLeave(Content content) {
    }

}