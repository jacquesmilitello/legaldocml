package io.legaldocml.akn.visitor.group;

import io.legaldocml.akn.element.BlockContainer;
import io.legaldocml.akn.element.BlockList;
import io.legaldocml.akn.element.Tblock;
import io.legaldocml.akn.element.Toc;

/**
 * Visitor for {@link io.legaldocml.akn.group.ANblock}
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface ANblockVisitor {

    default boolean visitEnter(BlockList blockList) {
        return true;
    }

    default void visitLeave(BlockList blockList) {
    }

    default boolean visitEnter(BlockContainer blockContainer) {
        return true;
    }

    default void visitLeave(BlockContainer blockContainer) {
    }

    default boolean visitEnter(Tblock tblock) {
        return true;
    }

    default void visitLeave(Tblock tblock) {
    }

    default boolean visitEnter(Toc toc) {
        return true;
    }

    default void visitLeave(Toc toc) {
    }

}