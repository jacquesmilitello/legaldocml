package io.legaldocml.akn.container;


import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.group.ANhier;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface ANhierContainer<T extends AknObject> extends Container<T> {

    void add(ANhier hier);

}
