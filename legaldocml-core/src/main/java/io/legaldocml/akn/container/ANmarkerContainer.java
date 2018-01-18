package io.legaldocml.akn.container;


import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.group.ANmarker;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface ANmarkerContainer<E extends AknObject> extends Container<E> {

    void add(ANmarker marker);

}
