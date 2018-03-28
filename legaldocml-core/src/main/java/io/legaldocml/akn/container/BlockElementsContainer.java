package io.legaldocml.akn.container;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.group.BlockElements;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface BlockElementsContainer<T extends AknObject> extends HTMLblockContainer<T>, ANblockContainer<T> {

    void add(BlockElements elements);

    void add(int index, BlockElements elements);

}
