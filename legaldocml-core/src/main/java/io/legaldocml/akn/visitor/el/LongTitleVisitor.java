package io.legaldocml.akn.visitor.el;

import io.legaldocml.akn.element.LongTitle;

/**
 * Visitor for {@link LongTitle}
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface LongTitleVisitor {

    default boolean visitEnter(LongTitle longTitle) {
        return true;
    }

    default void visitLeave(LongTitle longTitle) {
    }

}