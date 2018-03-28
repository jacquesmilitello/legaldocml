package io.legaldocml.business.builder.group;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.container.HTMLinlineContainer;
import io.legaldocml.business.builder.support.BSupport;
import io.legaldocml.business.builder.support.ISupport;
import io.legaldocml.business.builder.support.SupSupport;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface HTMLInlineBuilder<T extends HTMLinlineContainer<E>, E extends AknObject> extends BSupport<T, E>, ISupport<T, E>, SupSupport<T, E> {

}
