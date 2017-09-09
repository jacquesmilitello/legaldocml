package io.legaldocml.akn.visitor.group;

import io.legaldocml.akn.element.Img;

/**
 * Visitor for {@link io.legaldocml.akn.group.HTMLMarker}
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface HTMLmarkerVisitor {

    default void visit(Img img) {
    }


}
