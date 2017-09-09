package io.legaldocml.akn.visitor.group;

import io.legaldocml.akn.element.AuthorialNote;

/**
 * Visitor for {@link io.legaldocml.akn.group.ANsubFlow}
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface ANsubFlowVisitor {

    default boolean visitEnter(AuthorialNote authorialNote) {
        return true;
    }

    default void visitLeave(AuthorialNote authorialNote) {
    }

}
