package io.legaldocml.business.builder.group;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.container.HierElementsContainer;
import io.legaldocml.business.builder.support.HcontainerSupport;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface HierElementsBuilder<T extends HierElementsContainer<E>, E extends AknObject> extends ANhierBuilder<T,E>, HcontainerSupport<T,E> {

}
