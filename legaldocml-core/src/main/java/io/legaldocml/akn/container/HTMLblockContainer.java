package io.legaldocml.akn.container;


import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.group.HTMLblock;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface HTMLblockContainer<T extends AknObject> extends Container<T> {

    void add(HTMLblock block);

}
