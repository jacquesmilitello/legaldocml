package io.legaldocml.akn.visitor.group;

import io.legaldocml.akn.element.Answer;
import io.legaldocml.akn.element.Narrative;
import io.legaldocml.akn.element.Other;
import io.legaldocml.akn.element.Question;
import io.legaldocml.akn.element.Scene;
import io.legaldocml.akn.element.Speech;
import io.legaldocml.akn.element.SpeechGroup;
import io.legaldocml.akn.element.Summary;

/**
 * Visitor for {@link io.legaldocml.akn.group.ANcontainers}
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface ANcontainersVisitor {

    default boolean visitEnter(SpeechGroup speechGroup) {
        return true;
    }

    default void visitLeave(SpeechGroup speechGroup) {
    }

    default boolean visitEnter(Speech speech) {
        return true;
    }

    default void visitLeave(Speech speech) {
    }

    default boolean visitEnter(Question question) {
        return true;
    }

    default void visitLeave(Question question) {
    }

    default boolean visitEnter(Answer answer) {
        return true;
    }

    default void visitLeave(Answer answer) {
    }

    default boolean visitEnter(Other other) {
        return true;
    }

    default void visitLeave(Other other) {
    }

    default boolean visitEnter(Scene scene) {
        return true;
    }

    default void visitLeave(Scene scene) {
    }

    default boolean visitEnter(Narrative narrative) {
        return true;
    }

    default void visitLeave(Narrative narrative) {
    }

    default boolean visitEnter(Summary summary) {
        return true;
    }

    default void visitLeave(Summary summary) {
    }

}