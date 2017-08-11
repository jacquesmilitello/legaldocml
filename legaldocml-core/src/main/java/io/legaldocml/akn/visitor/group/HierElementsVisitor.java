package io.legaldocml.akn.visitor.group;

import io.legaldocml.akn.element.Hcontainer;

/**
 * Visitor for {@link io.legaldocml.akn.group.HierElements}
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface HierElementsVisitor extends ANhierVisitor {

    default boolean visitEnter(Hcontainer hcontainer) {
        return true;
    }

    default void visitLeave(Hcontainer hcontainer) {
    }

}