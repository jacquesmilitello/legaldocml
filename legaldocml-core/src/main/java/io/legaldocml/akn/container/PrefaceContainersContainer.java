package io.legaldocml.akn.container;


import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.group.PrefaceContainers;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface PrefaceContainersContainer<T extends AknObject> extends ContainerContainer<T> {

    void add(PrefaceContainers prefaceContainers);

}
