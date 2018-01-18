package io.legaldocml.akn.container;


import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.element.DocContainerType;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface DocContainerTypeContainer<T extends AknObject> extends Container<T> {

    void add(DocContainerType docContainerType);

}
