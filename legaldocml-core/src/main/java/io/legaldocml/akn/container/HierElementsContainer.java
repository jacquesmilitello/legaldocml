package io.legaldocml.akn.container;


import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.group.HierElements;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface HierElementsContainer<T extends AknObject> extends ANhierContainer<T>, HcontainerContainer<T> {

    void add(HierElements hier);

    boolean remove(HierElements hier);

}
