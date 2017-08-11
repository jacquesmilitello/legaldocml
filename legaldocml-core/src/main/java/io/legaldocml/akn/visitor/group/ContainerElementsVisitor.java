package io.legaldocml.akn.visitor.group;


import io.legaldocml.akn.visitor.el.ContainerVisitor;

/**
 * Visitor for {@link io.legaldocml.akn.group.ContainerElements}
 */
public interface ContainerElementsVisitor extends HTMLContainersVisitor, SpeechSectionVisitor, ContainerVisitor {

}