package io.legaldocml.akn.visitor.stuct;

import io.legaldocml.akn.element.AlternativeReference;
import io.legaldocml.akn.element.Attachment;
import io.legaldocml.akn.element.ComponentRef;
import io.legaldocml.akn.element.DocumentRef;
import io.legaldocml.akn.element.Header;
import io.legaldocml.akn.element.Heading;
import io.legaldocml.akn.element.ImplicitReference;
import io.legaldocml.akn.element.ListIntroduction;
import io.legaldocml.akn.element.ListWrapUp;
import io.legaldocml.akn.element.Td;
import io.legaldocml.akn.element.TemporalData;
import io.legaldocml.akn.element.TemporalGroup;
import io.legaldocml.akn.element.Th;
import io.legaldocml.akn.element.TimeInterval;
import io.legaldocml.akn.element.Tr;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface OtherVisitior {

    default boolean visitEnter(ImplicitReference implicitReference) {
        return true;
    }

    default void visitLeave(ImplicitReference implicitReference) {
        // default -> nothing to do.
    }

    default boolean visitEnter(AlternativeReference alternativeReference) {
        return true;
    }

    default void visitLeave(AlternativeReference alternativeReference) {
        // default -> nothing to do.
    }

    default boolean visitEnter(Attachment attachment) {
        return true;
    }

    default void visitLeave(Attachment attachment) {
        // default -> nothing to do.
    }

    default void visit(DocumentRef documentRef) {
    }

    default void visit(ComponentRef componentRef) {
    }

    default boolean visitEnter(Heading heading) {
        return true;
    }

    default void visitLeave(Heading heading) {
        // default -> nothing to do.
    }

    default boolean visitEnter(Header header) {
        return true;
    }

    default void visitLeave(Header header) {
        // default -> nothing to do.
    }

    default boolean visitEnter(Tr tr) {
        return true;
    }

    default void visitLeave(Tr tr) {
        // default -> nothing to do.
    }

    default boolean visitEnter(Td td) {
        return true;
    }

    default void visitLeave(Td Td) {
        // default -> nothing to do.
    }

    default boolean visitEnter(Th th) {
        return true;
    }

    default void visitLeave(Th th) {
        // default -> nothing to do.
    }

    default boolean visitEnter(TemporalData temporalData) {
        return true;
    }

    default void visitLeave(TemporalData temporalData) {
        // default -> nothing to do.
    }

    default boolean visitEnter(TemporalGroup temporalGroup) {
        return true;
    }

    default void visitLeave(TemporalGroup temporalGroup) {
        // default -> nothing to do.
    }

    default void visit(TimeInterval timeInterval) {
        // default -> nothing to do.
    }

    default boolean visitEnter(ListIntroduction listIntroduction) {
        return true;
    }

    default void visitLeave(ListIntroduction listIntroduction) {
        // default -> nothing to do.
    }

    default boolean visitEnter(ListWrapUp listWrapUp) {
        return true;
    }

    default void visitLeave(ListWrapUp listWrapUp) {
        // default -> nothing to do.
    }

}