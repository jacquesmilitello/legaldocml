package io.legaldocml.akn.visitor.group;

import io.legaldocml.akn.element.Marker;

/**
 * Visitor for {@link io.legaldocml.akn.group.MarkerElements}
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface MarkerElementsVisitor  {

    default void visit(Marker marker) {
    }

}