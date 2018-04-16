package io.legaldocml.akn.visitor.stuct;

import io.legaldocml.akn.element.Citation;
import io.legaldocml.akn.element.Item;
import io.legaldocml.akn.element.Recital;

/**
 * @author <a href="mailto:mustapha.charboub@gmail.com">Mustapha CHARBOUB</a>
 */
public interface ItemTypeVisitor {

    default boolean visitEnter(Item item) {
        return true;
    }

    default void visitLeave(Item item) {
    }

    default boolean visitEnter(Citation citation) {
        return true;
    }

    default void visitLeave(Citation citation) {
    }

    default boolean visitEnter(Recital recital) {
        return true;
    }

    default void visitLeave(Recital recital) {
    }

}
