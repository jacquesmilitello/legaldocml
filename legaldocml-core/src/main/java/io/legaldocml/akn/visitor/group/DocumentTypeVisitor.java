package io.legaldocml.akn.visitor.group;

import io.legaldocml.akn.element.Act;
import io.legaldocml.akn.element.Amendment;
import io.legaldocml.akn.element.AmendmentList;
import io.legaldocml.akn.element.Bill;
import io.legaldocml.akn.element.Debate;
import io.legaldocml.akn.element.DebateReport;
import io.legaldocml.akn.element.Doc;
import io.legaldocml.akn.element.DocumentCollection;
import io.legaldocml.akn.element.Judgment;
import io.legaldocml.akn.element.OfficialGazette;
import io.legaldocml.akn.element.Portion;
import io.legaldocml.akn.element.Statement;

/**
 * Visitor for {@link io.legaldocml.akn.DocumentType}
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface DocumentTypeVisitor {

    default boolean visitEnter(AmendmentList amendmentList) {
        return true;
    }

    default void visitLeave(AmendmentList amendmentList) {
    }
    
    default boolean visitEnter(OfficialGazette officialGazette) {
        return true;
    }

    default void visitLeave(OfficialGazette officialGazette) {
    }

    default boolean visitEnter(DocumentCollection documentCollection) {
        return true;
    }

    default void visitLeave(DocumentCollection documentCollection) {
    }

    default boolean visitEnter(Act act) {
        return true;
    }

    default void visitLeave(Act act) {
    }

    default boolean visitEnter(Bill bill) {
        return true;
    }

    default void visitLeave(Bill bill) {
    }

    default boolean visitEnter(DebateReport debateReport) {
        return true;
    }

    default void visitLeave(DebateReport debateReport) {
    }

    default boolean visitEnter(Debate debate) {
        return true;
    }

    default void visitLeave(Debate debate) {
    }

    default boolean visitEnter(Statement statement) {
        return true;
    }

    default void visitLeave(Statement statement) {
    }

    default boolean visitEnter(Amendment amendment) {
        return true;
    }

    default void visitLeave(Amendment amendment) {
    }

    default boolean visitEnter(Judgment judgment) {
        return true;
    }

    default void visitLeave(Judgment judgment) {
    }

    default boolean visitEnter(Portion portion) {
        return true;
    }

    default void visitLeave(Portion portion) {
    }

    default boolean visitEnter(Doc doc) {
        return true;
    }

    default void visitLeave(Doc doc) {
    }

}
