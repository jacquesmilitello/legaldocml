package io.legaldocml.business.builder.group;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.container.ANinlineContainer;
import io.legaldocml.business.builder.support.ModSupport;
import io.legaldocml.business.builder.support.MrefSupport;
import io.legaldocml.business.builder.support.RefSupport;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface ANinlineBuilder<T extends ANinlineContainer<E>, E extends AknObject> extends MrefSupport<T,E>, RefSupport<T,E>, ModSupport<T,E> {

}
