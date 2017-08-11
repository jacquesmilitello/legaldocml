package io.legaldocml.akn.visitor.stuct;

import io.legaldocml.akn.element.Attachments;
import io.legaldocml.akn.element.Components;
import io.legaldocml.akn.element.Conclusions;
import io.legaldocml.akn.element.CoverPage;
import io.legaldocml.akn.element.Meta;
import io.legaldocml.akn.element.Preamble;
import io.legaldocml.akn.element.Preface;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface StructureVisitor {

    default boolean visitEnter(Meta meta) {
        return true;
    }

    default void visitLeave(Meta meta) {
        // default -> nothing to do.
    }

    default boolean visitEnter(CoverPage coverPage) {
        return true;
    }

    default void visitLeave(CoverPage coverPage) {
        // default -> nothing to do.
    }

    default boolean visitEnter(Preface preface) {
        return true;
    }

    default void visitLeave(Preface preface) {
        // default -> nothing to do.
    }

    default boolean visitEnter(Preamble preamble) {
        return true;
    }

    default void visitLeave(Preamble preamble) {
        // default -> nothing to do.
    }

    default boolean visitEnter(Conclusions conclusions) {
        return true;
    }

    default void visitLeave(Conclusions conclusions) {
        // default -> nothing to do.
    }

    default boolean visitEnter(Attachments attachments) {
        return true;
    }

    default void visitLeave(Attachments Attachments) {
        // default -> nothing to do.
    }

    default boolean visitEnter(Components components) {
        return true;
    }

    default void visitLeave(Components components) {
        // default -> nothing to do.
    }
}
