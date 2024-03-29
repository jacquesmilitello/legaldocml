package io.legaldocml.business.builder.group;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.container.ANmarkerContainer;
import io.legaldocml.business.builder.support.EolSupport;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface ANmarkerBuilder<T extends ANmarkerContainer<E>, E extends AknObject> extends EolSupport<T,E> {

}