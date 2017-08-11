package io.legaldocml.akn.visitor.group;

import io.legaldocml.akn.element.Inline;

/**
 * Visitor for {@link io.legaldocml.akn.group.InlineElements}
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface InlineElementsVisitor extends ANtitleInlineVisitor, ANsemanticInlineVisitor, ANtitleVisitor, ANheaderInlineVisitor,
        AmendmentInlineVisitor, HTMLinlineVisitor {

    default boolean visitEnter(Inline inline) {
        return true;
    }

    default void visitLeave(Inline inline) {
    }

}