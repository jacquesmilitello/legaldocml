package io.legaldocml.akn.visitor.group;

import io.legaldocml.akn.element.Arguments;
import io.legaldocml.akn.element.Background;
import io.legaldocml.akn.element.Decision;
import io.legaldocml.akn.element.Introduction;
import io.legaldocml.akn.element.Motivation;
import io.legaldocml.akn.element.Remedies;

/**
 * Visitor for {@link io.legaldocml.akn.group.JudgmentBlock}
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface JudgmentBlockVisitor {

    default boolean visitEnter(Introduction introduction) {
        return true;
    }

    default void visitLeave(Introduction introduction) {
    }

    default boolean visitEnter(Background background) {
        return true;
    }

    default void visitLeave(Background background) {
    }

    default boolean visitEnter(Arguments arguments) {
        return true;
    }

    default void visitLeave(Arguments arguments) {
    }

    default boolean visitEnter(Remedies remedies) {
        return true;
    }

    default void visitLeave(Remedies remedies) {
    }

    default boolean visitEnter(Motivation motivation) {
        return true;
    }

    default void visitLeave(Motivation motivation) {
    }

    default boolean visitEnter(Decision decision) {
        return true;
    }

    default void visitLeave(Decision decision) {
    }

}
