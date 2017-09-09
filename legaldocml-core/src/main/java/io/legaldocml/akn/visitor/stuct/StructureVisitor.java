package io.legaldocml.akn.visitor.stuct;

import io.legaldocml.akn.element.ActiveRef;
import io.legaldocml.akn.element.Analysis;
import io.legaldocml.akn.element.Attachments;
import io.legaldocml.akn.element.Classification;
import io.legaldocml.akn.element.Components;
import io.legaldocml.akn.element.Conclusions;
import io.legaldocml.akn.element.CoverPage;
import io.legaldocml.akn.element.EventRef;
import io.legaldocml.akn.element.Identification;
import io.legaldocml.akn.element.Keyword;
import io.legaldocml.akn.element.Lifecycle;
import io.legaldocml.akn.element.Meta;
import io.legaldocml.akn.element.Notes;
import io.legaldocml.akn.element.Original;
import io.legaldocml.akn.element.Preamble;
import io.legaldocml.akn.element.Preface;
import io.legaldocml.akn.element.Publication;
import io.legaldocml.akn.element.References;
import io.legaldocml.akn.element.Workflow;

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

    default boolean visitEnter(Identification identification) {
        return true;
    }

    default void visitLeave(Identification identification) {
        // default -> nothing to do.
    }

    default boolean visitEnter(Analysis analysis) {
        return true;
    }

    default void visitLeave(Analysis analysis) {
        // default -> nothing to do.
    }

    default boolean visitEnter(References references) {
        return true;
    }

    default void visitLeave(References references) {
        // default -> nothing to do.
    }

    default void visit(Publication publication) {
        // default -> nothing to do.
    }

    default boolean visitEnter(Workflow workflow) {
        return true;
    }

    default void visitLeave(Workflow workflow) {
        // default -> nothing to do.
    }

    default void visit(Original original) {
        // default -> nothing to do.
    }

    default boolean visitEnter(Classification classification) {
        return true;
    }

    default void visitLeave(Classification classification) {
        // default -> nothing to do.
    }

    default void visit(Keyword keyword) {
        // default -> nothing to do.
    }

    default void visit(ActiveRef ref) {
        // default -> nothing to do.
    }

    default boolean visitEnter(Lifecycle lifecycle) {
        return true;
    }

    default void visitLeave(Lifecycle lifecycle) {
        // default -> nothing to do.
    }

    default boolean visitEnter(EventRef eventRef) {
        return true;
    }

    default void visitLeave(EventRef eventRef) {
        // default -> nothing to do.
    }

    default boolean visitEnter(Notes notes) {
        return true;
    }

    default void visitLeave(Notes notes) {
        // default -> nothing to do.
    }
}
