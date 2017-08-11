package io.legaldocml.akn.visitor.group;

import io.legaldocml.akn.element.Block;
import io.legaldocml.akn.element.Foreign;

/**
 * Visitor for {@link io.legaldocml.akn.group.BlockElements}
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface BlockElementsVisitor extends ANblockVisitor, HTMLBlockVisitor {

    default boolean visitEnter(Foreign foreign) {
        return true;
    }

    default void visitLeave(Foreign foreign) {
    }

    default boolean visitEnter(Block block) {
        return true;
    }

    default void visitLeave(Block block) {
    }

}