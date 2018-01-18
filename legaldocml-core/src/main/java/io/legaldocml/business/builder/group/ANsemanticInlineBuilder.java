package io.legaldocml.business.builder.group;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.container.ANsemanticInlineContainer;
import io.legaldocml.business.builder.support.TermSupport;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface ANsemanticInlineBuilder<T extends ANsemanticInlineContainer<E>, E extends AknObject> extends TermSupport<T,E> {

}
