package io.legaldocml.akn.visitor.el;

import io.legaldocml.akn.element.Formula;

/**
 * Visitor for {@link io.legaldocml.akn.element.Formula}
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface FormulaVisitor {

    default boolean visitEnter(Formula formula) {
        return true;
    }

    default void visitLeave(Formula formula) {
    }

}