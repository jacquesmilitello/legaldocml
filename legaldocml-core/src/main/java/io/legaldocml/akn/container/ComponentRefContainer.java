package io.legaldocml.akn.container;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.element.ComponentRef;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface ComponentRefContainer<T extends AknObject> extends Container<T> {

    void add(ComponentRef componentRef);

    boolean remove(ComponentRef componentRef);

}