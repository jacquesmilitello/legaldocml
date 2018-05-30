package io.legaldocml.akn.visitor.group;

import io.legaldocml.akn.element.Argument;
import io.legaldocml.akn.element.CourtType;
import io.legaldocml.akn.element.Judge;
import io.legaldocml.akn.element.Lawyer;
import io.legaldocml.akn.element.NeutralCitation;
import io.legaldocml.akn.element.Party;
import io.legaldocml.akn.element.Signature;

/**
 * Visitor for {@link io.legaldocml.akn.group.ANheaderInline}
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface ANheaderInlineVisitor {

    default boolean visitEnter(CourtType courtType) {
        return true;
    }

    default void visitLeave(CourtType courtType) {
        // default -> nothing to do.
    }

    default boolean visitEnter(NeutralCitation neutralCitation) {
        return true;
    }

    default void visitLeave(NeutralCitation neutralCitation) {
        // default -> nothing to do.
    }

    default boolean visitEnter(Party party) {
        return true;
    }

    default void visitLeave(Party party) {
        // default -> nothing to do.
    }

    default boolean visitEnter(Judge judge) {
        return true;
    }

    default void visitLeave(Judge judge) {
        // default -> nothing to do.
    }

    default boolean visitEnter(Lawyer lawyer) {
        return true;
    }

    default void visitLeave(Lawyer lawyer) {
        // default -> nothing to do.
    }

    default boolean visitEnter(Signature signature) {
        return true;
    }

    default void visitLeave(Signature signature) {
        // default -> nothing to do.
    }

    default boolean visitEnter(Argument argument) {
        return true;
    }

    default void visitLeave(Argument argument) {
        // default -> nothing to do.
    }

}