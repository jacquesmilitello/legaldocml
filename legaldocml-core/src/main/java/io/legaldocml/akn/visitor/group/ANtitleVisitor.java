package io.legaldocml.akn.visitor.group;

import io.legaldocml.akn.element.DocAuthority;
import io.legaldocml.akn.element.DocCommittee;
import io.legaldocml.akn.element.DocDate;
import io.legaldocml.akn.element.DocIntroducer;
import io.legaldocml.akn.element.DocJurisdiction;
import io.legaldocml.akn.element.DocNumber;
import io.legaldocml.akn.element.DocProponent;
import io.legaldocml.akn.element.DocPurpose;
import io.legaldocml.akn.element.DocStage;
import io.legaldocml.akn.element.DocStatus;
import io.legaldocml.akn.element.DocTitle;
import io.legaldocml.akn.element.DocType;
import io.legaldocml.akn.element.DocketNumber;
import io.legaldocml.akn.element.Legislature;
import io.legaldocml.akn.element.Session;
import io.legaldocml.akn.element.ShortTitle;

/**
 * Visitor for {@link io.legaldocml.akn.group.ANtitleInline}
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface ANtitleVisitor {

    default boolean visitEnter(DocType docType) {
        return true;
    }

    default void visitLeave(DocType docType) {
    }

    default boolean visitEnter(DocTitle docTitle) {
        return true;
    }

    default void visitLeave(DocTitle docTitle) {
    }

    default boolean visitEnter(DocNumber docNumber) {
        return true;
    }

    default void visitLeave(DocNumber docNumber) {
    }

    default boolean visitEnter(DocProponent docProponent) {
        return true;
    }

    default void visitLeave(DocProponent docProponent) {
    }

    default boolean visitEnter(DocDate docDate) {
        return true;
    }

    default void visitLeave(DocDate docDate) {
    }

    default boolean visitEnter(Legislature legislature) {
        return true;
    }

    default void visitLeave(Legislature legislature) {
    }

    default boolean visitEnter(Session session) {
        return true;
    }

    default void visitLeave(Session session) {
    }

    default boolean visitEnter(ShortTitle shortTitle) {
        return true;
    }

    default void visitLeave(ShortTitle shortTitle) {
    }

    default boolean visitEnter(DocAuthority docAuthority) {
        return true;
    }

    default void visitLeave(DocAuthority docAuthority) {
    }

    default boolean visitEnter(DocPurpose docPurpose) {
        return true;
    }

    default void visitLeave(DocPurpose docPurpose) {
    }

    default boolean visitEnter(DocCommittee docCommittee) {
        return true;
    }

    default void visitLeave(DocCommittee docCommittee) {
    }

    default boolean visitEnter(DocIntroducer docIntroducer) {
        return true;
    }

    default void visitLeave(DocIntroducer docIntroducer) {
    }

    default boolean visitEnter(DocStage docStage) {
        return true;
    }

    default void visitLeave(DocStage docStage) {
    }

    default boolean visitEnter(DocStatus docStatus) {
        return true;
    }

    default void visitLeave(DocStatus docStatus) {
    }

    default boolean visitEnter(DocJurisdiction docJurisdiction) {
        return true;
    }

    default void visitLeave(DocJurisdiction docJurisdiction) {
    }

    default boolean visitEnter(DocketNumber docketNumber) {
        return true;
    }

    default void visitLeave(DocketNumber docketNumber) {
    }

}