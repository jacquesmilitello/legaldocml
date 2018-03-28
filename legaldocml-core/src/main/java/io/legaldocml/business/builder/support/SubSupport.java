package io.legaldocml.business.builder.support;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.container.HTMLinlineContainer;

/**
 * @author <a href="mailto:mustapha.charboub@gmail.com">Mustapha CHARBOUB</a>
 */
public interface SubSupport<T extends HTMLinlineContainer<E>, E extends AknObject> extends SupportBuilder<T> {
}
