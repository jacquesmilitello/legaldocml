package io.legaldocml.akn.visitor.stuct;

import io.legaldocml.akn.element.AmendmentBody;
import io.legaldocml.akn.element.Body;
import io.legaldocml.akn.element.CollectionBody;
import io.legaldocml.akn.element.DebateBody;
import io.legaldocml.akn.element.JudgmentBody;
import io.legaldocml.akn.element.MainBody;
import io.legaldocml.akn.element.PortionBody;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface BodyVisitor {

    default boolean visitEnter(DebateBody body) {
        return true;
    }

    default void visitLeave(DebateBody body) {
        // default -> nothing to do.
    }

    default boolean visitEnter(AmendmentBody body) {
        return true;
    }

    default void visitLeave(AmendmentBody body) {
        // default -> nothing to do.
    }

    default boolean visitEnter(Body body) {
        return true;
    }

    default void visitLeave(Body body) {
        // default -> nothing to do.
    }

    default boolean visitEnter(MainBody body) {
        return true;
    }

    default void visitLeave(MainBody body) {
        // default -> nothing to do.
    }

    default boolean visitEnter(CollectionBody body) {
        return true;
    }

    default void visitLeave(CollectionBody body) {
        // default -> nothing to do.
    }

    default boolean visitEnter(JudgmentBody body) {
        return true;
    }

    default void visitLeave(JudgmentBody body) {
        // default -> nothing to do.
    }


    default boolean visitEnter(PortionBody body) {
        return true;
    }

    default void visitLeave(PortionBody body) {
        // default -> nothing to do.
    }

}
