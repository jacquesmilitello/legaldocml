package io.legaldocml.akn.visitor.group;

import io.legaldocml.akn.element.Del;
import io.legaldocml.akn.element.EmbeddedStructure;
import io.legaldocml.akn.element.EmbeddedText;
import io.legaldocml.akn.element.FillIn;
import io.legaldocml.akn.element.Ins;
import io.legaldocml.akn.element.Mmod;
import io.legaldocml.akn.element.Mod;
import io.legaldocml.akn.element.Mref;
import io.legaldocml.akn.element.Omissis;
import io.legaldocml.akn.element.Outcome;
import io.legaldocml.akn.element.Placeholder;
import io.legaldocml.akn.element.RecordedTime;
import io.legaldocml.akn.element.Ref;
import io.legaldocml.akn.element.Remark;
import io.legaldocml.akn.element.Rmod;
import io.legaldocml.akn.element.Rref;
import io.legaldocml.akn.element.Vote;

/**
 * Visitor for {@link io.legaldocml.akn.group.ANinline}
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface ANtitleInlineVisitor {

    default boolean visitEnter(Ref ref) {
        return true;
    }

    default void visitLeave(Ref ref) {
    }

    default boolean visitEnter(Mref mref) {
        return true;
    }

    default void visitLeave(Mref mref) {
    }

    default boolean visitEnter(Rref rref) {
        return true;
    }

    default void visitLeave(Rref rref) {
    }

    default boolean visitEnter(Mod mod) {
        return true;
    }

    default void visitLeave(Mod mod) {
    }

    default boolean visitEnter(Mmod mmod) {
        return true;
    }

    default void visitLeave(Mmod mmod) {
    }

    default boolean visitEnter(Rmod rmod) {
        return true;
    }

    default void visitLeave(Rmod rmod) {
    }

    default boolean visitEnter(Remark remark) {
        return true;
    }

    default void visitLeave(Remark remark) {
    }

    default boolean visitEnter(RecordedTime recordedTime) {
        return true;
    }

    default void visitLeave(RecordedTime recordedTime) {
    }

    default boolean visitEnter(Vote vote) {
        return true;
    }

    default void visitLeave(Vote vote) {
    }

    default boolean visitEnter(Outcome outcome) {
        return true;
    }

    default void visitLeave(Outcome outcome) {
    }

    default boolean visitEnter(Ins ins) {
        return true;
    }

    default void visitLeave(Ins ins) {
    }

    default boolean visitEnter(Del del) {
        return true;
    }

    default void visitLeave(Del del) {
    }

    default boolean visitEnter(Omissis omissis) {
        return true;
    }

    default void visitLeave(Omissis omissis) {
    }

    default boolean visitEnter(EmbeddedText embeddedText) {
        return true;
    }

    default void visitLeave(EmbeddedText embeddedText) {
    }

    default boolean visitEnter(EmbeddedStructure embeddedStructure) {
        return true;
    }

    default void visitLeave(EmbeddedStructure embeddedStructure) {
    }

    default boolean visitEnter(Placeholder placeholder) {
        return true;
    }

    default void visitLeave(Placeholder placeholder) {
    }

    default boolean visitEnter(FillIn fillIn) {
        return true;
    }

    default void visitLeave(FillIn fillIn) {
    }

}