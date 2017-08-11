package io.legaldocml.akn.visitor.group;

import io.legaldocml.akn.visitor.el.ContainerVisitor;
import io.legaldocml.akn.visitor.el.FormulaVisitor;
import io.legaldocml.akn.visitor.el.LongTitleVisitor;

/**
 * Visitor for {@link io.legaldocml.akn.group.BasicContainers}
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface BasicContainersVisitor extends ContainerVisitor, LongTitleVisitor, FormulaVisitor {

}