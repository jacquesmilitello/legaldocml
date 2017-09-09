package io.legaldocml.akn.visitor.stuct;

import io.legaldocml.akn.element.ComponentData;
import io.legaldocml.akn.element.ComponentInfo;
import io.legaldocml.akn.element.FRBRExpression;
import io.legaldocml.akn.element.FRBRManifestation;
import io.legaldocml.akn.element.FRBRWork;
import io.legaldocml.akn.element.FRBRalias;
import io.legaldocml.akn.element.FRBRauthor;
import io.legaldocml.akn.element.FRBRauthoritative;
import io.legaldocml.akn.element.FRBRcountry;
import io.legaldocml.akn.element.FRBRdate;
import io.legaldocml.akn.element.FRBRformat;
import io.legaldocml.akn.element.FRBRlanguage;
import io.legaldocml.akn.element.FRBRmasterExpression;
import io.legaldocml.akn.element.FRBRname;
import io.legaldocml.akn.element.FRBRnumber;
import io.legaldocml.akn.element.FRBRportion;
import io.legaldocml.akn.element.FRBRprescriptive;
import io.legaldocml.akn.element.FRBRsubtype;
import io.legaldocml.akn.element.FRBRthis;
import io.legaldocml.akn.element.FRBRtranslation;
import io.legaldocml.akn.element.FRBRuri;
import io.legaldocml.akn.element.FRBRversionNumber;
import io.legaldocml.akn.element.Preservation;

public interface FRBRVisitor {

    default boolean visitEnter(FRBRWork work) {
        return true;
    }

    default void visitLeave(FRBRWork work) {
        // default -> nothing to do.
    }

    default boolean visitEnter(FRBRExpression expression) {
        return true;
    }

    default void visitLeave(FRBRExpression expression) {
        // default -> nothing to do.
    }

    default boolean visitEnter(FRBRManifestation manifestation) {
        return true;
    }

    default void visitLeave(FRBRManifestation manifestation) {
        // default -> nothing to do.
    }

    default void visit(FRBRcountry country) {
        // default -> nothing to do.
    }

    default void visit(FRBRsubtype subType) {
        // default -> nothing to do.
    }

    default void visit(FRBRnumber number) {
        // default -> nothing to do.
    }

    default void visit(FRBRname name) {
        // default -> nothing to do.
    }

    default void visit(FRBRprescriptive prescriptive) {
        // default -> nothing to do.
    }

    default void visit(FRBRauthoritative authoritative) {
        // default -> nothing to do.
    }

    default void visit(FRBRthis frbRthis) {
        // default -> nothing to do.
    }

    default void visit(FRBRuri uri) {
        // default -> nothing to do.
    }

    default void visit(FRBRalias alias) {
        // default -> nothing to do.
    }

    default void visit(FRBRdate date) {
        // default -> nothing to do.
    }

    default void visit(FRBRauthor author) {
        // default -> nothing to do.
    }

    default void visit(FRBRversionNumber versionNumber) {
        // default -> nothing to do.
    }

    default void visit(FRBRmasterExpression masterExpression) {
        // default -> nothing to do.
    }

    default void visit(FRBRlanguage language) {
        // default -> nothing to do.
    }

    default void visit(FRBRtranslation translation) {
        // default -> nothing to do.
    }

    default void visit(FRBRformat format) {
        // default -> nothing to do.
    }

    default void visit(FRBRportion portion) {
        // default -> nothing to do.
    }

    default boolean visitEnter(ComponentInfo componentInfo) {
        return true;
    }

    default void visitLeave(ComponentInfo componentInfo) {
        // default -> nothing to do.
    }

    default boolean visitEnter(ComponentData componentData) {
        return true;
    }

    default void visitLeave(ComponentData componentData) {
        // default -> nothing to do.
    }

    default boolean visitEnter(Preservation preservation) {
        return true;
    }

    default void visitLeave(Preservation preservation) {
        // default -> nothing to do.
    }
}
