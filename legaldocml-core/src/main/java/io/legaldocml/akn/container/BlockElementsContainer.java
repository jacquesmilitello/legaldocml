package io.legaldocml.akn.container;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.Id;
import io.legaldocml.akn.group.BlockElements;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface BlockElementsContainer extends AknObject, Id {

    void add(BlockElements elements);

}
