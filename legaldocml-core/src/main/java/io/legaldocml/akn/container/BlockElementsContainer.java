package io.legaldocml.akn.container;

import io.legaldocml.akn.group.BlockElements;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface BlockElementsContainer extends HTMLblockContainer, ANblockContainer {

    void add(BlockElements elements);

}
