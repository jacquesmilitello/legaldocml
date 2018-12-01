package io.legaldocml.business.builder.group;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.container.BlockElementsContainer;
import io.legaldocml.business.builder.support.BlockSupport;
import io.legaldocml.business.builder.support.ForeignSupport;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface BlockElementsBuilder<T extends BlockElementsContainer<E>, E extends AknObject> extends BlockSupport<T,E> , HTMLblockBuilder<T,E> , ForeignSupport<T,E> {

}
