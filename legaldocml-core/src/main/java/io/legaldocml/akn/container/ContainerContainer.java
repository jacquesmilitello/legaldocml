package io.legaldocml.akn.container;


import io.legaldocml.akn.AknObject;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface ContainerContainer<T extends AknObject> extends Container<T> {

    void add(io.legaldocml.akn.element.Container container);

}