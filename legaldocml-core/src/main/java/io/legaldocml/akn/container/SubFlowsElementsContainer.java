package io.legaldocml.akn.container;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.group.SubFlowElements;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface SubFlowsElementsContainer<T extends AknObject> extends Container<T> {

    void add(SubFlowElements el);

}
