package io.legaldocml.akn.visitor.group;

import io.legaldocml.akn.element.Item;

/**
 * @author <a href="mailto:mustapha.charboub@gmail.com">Mustapha CHARBOUB</a>
 */
public interface ItemTypeVisitor {

    default boolean visitEnter(Item item) {
        return true;
    }

    default void visitLeave(Item item) {
    }
}
