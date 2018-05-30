package io.legaldocml.akn.container;


import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.group.ANsubFlow;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface ANsubFlowContainer<T extends AknObject> extends Container<T> {

    void add(ANsubFlow flow);

}
