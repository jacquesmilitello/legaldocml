package io.legaldocml.akn.container;


import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.element.Hcontainer;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface HcontainerContainer<T extends AknObject> extends Container<T> {

    void add(Hcontainer hcontainer);

    boolean remove(Hcontainer hcontainer);

}