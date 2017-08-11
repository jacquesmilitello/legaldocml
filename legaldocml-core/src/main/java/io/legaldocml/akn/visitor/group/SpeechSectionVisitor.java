package io.legaldocml.akn.visitor.group;

import io.legaldocml.akn.element.Address;
import io.legaldocml.akn.element.Adjournment;
import io.legaldocml.akn.element.AdministrationOfOath;
import io.legaldocml.akn.element.Communication;
import io.legaldocml.akn.element.DebateSection;
import io.legaldocml.akn.element.DeclarationOfVote;
import io.legaldocml.akn.element.MinisterialStatements;
import io.legaldocml.akn.element.NationalInterest;
import io.legaldocml.akn.element.NoticesOfMotion;
import io.legaldocml.akn.element.OralStatements;
import io.legaldocml.akn.element.Papers;
import io.legaldocml.akn.element.PersonalStatements;
import io.legaldocml.akn.element.Petitions;
import io.legaldocml.akn.element.PointOfOrder;
import io.legaldocml.akn.element.Prayers;
import io.legaldocml.akn.element.ProceduralMotions;
import io.legaldocml.akn.element.Questions;
import io.legaldocml.akn.element.Resolutions;
import io.legaldocml.akn.element.RollCall;
import io.legaldocml.akn.element.WrittenStatements;

/**
 * Visitor for {@link io.legaldocml.akn.group.SpeechSection}
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface SpeechSectionVisitor {

    default boolean visitEnter(AdministrationOfOath administrationOfOath) {
        return true;
    }

    default void visitLeave(AdministrationOfOath administrationOfOath) {
    }

    default boolean visitEnter(RollCall rollCall) {
        return true;
    }

    default void visitLeave(RollCall rollCall) {
    }

    default boolean visitEnter(Prayers prayers) {
        return true;
    }

    default void visitLeave(Prayers prayers) {
    }

    default boolean visitEnter(OralStatements oralStatements) {
        return true;
    }

    default void visitLeave(OralStatements oralStatements) {
    }

    default boolean visitEnter(WrittenStatements writtenStatements) {
        return true;
    }

    default void visitLeave(WrittenStatements writtenStatements) {
    }

    default boolean visitEnter(PersonalStatements personalStatements) {
        return true;
    }

    default void visitLeave(PersonalStatements personalStatements) {
    }

    default boolean visitEnter(MinisterialStatements ministerialStatements) {
        return true;
    }

    default void visitLeave(MinisterialStatements ministerialStatements) {
    }

    default boolean visitEnter(Resolutions resolutions) {
        return true;
    }

    default void visitLeave(Resolutions resolutions) {
    }

    default boolean visitEnter(NationalInterest nationalInterest) {
        return true;
    }

    default void visitLeave(NationalInterest nationalInterest) {
    }

    default boolean visitEnter(DeclarationOfVote declarationOfVote) {
        return true;
    }

    default void visitLeave(DeclarationOfVote declarationOfVote) {
    }

    default boolean visitEnter(Communication communication) {
        return true;
    }

    default void visitLeave(Communication communication) {
    }

    default boolean visitEnter(Petitions petitions) {
        return true;
    }

    default void visitLeave(Petitions petitions) {
    }

    default boolean visitEnter(Papers papers) {
        return true;
    }

    default void visitLeave(Papers papers) {
    }

    default boolean visitEnter(NoticesOfMotion noticesOfMotion) {
        return true;
    }

    default void visitLeave(NoticesOfMotion noticesOfMotion) {
    }

    default boolean visitEnter(Questions questions) {
        return true;
    }

    default void visitLeave(Questions questions) {
    }

    default boolean visitEnter(Address address) {
        return true;
    }

    default void visitLeave(Address address) {
    }

    default boolean visitEnter(ProceduralMotions ProceduralMotions) {
        return true;
    }

    default void visitLeave(ProceduralMotions ProceduralMotions) {
    }

    default boolean visitEnter(PointOfOrder pointOfOrder) {
        return true;
    }

    default void visitLeave(PointOfOrder pointOfOrder) {
    }

    default boolean visitEnter(Adjournment adjournment) {
        return true;
    }

    default void visitLeave(Adjournment adjournment) {
    }

    default boolean visitEnter(DebateSection debateSection) {
        return true;
    }

    default void visitLeave(DebateSection debateSection) {
    }

}
