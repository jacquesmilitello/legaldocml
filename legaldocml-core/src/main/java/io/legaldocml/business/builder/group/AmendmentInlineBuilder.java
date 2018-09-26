package io.legaldocml.business.builder.group;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.container.ANsemanticInlineContainer;
import io.legaldocml.akn.container.AmendmentInlineContainer;
import io.legaldocml.business.builder.support.ChangeSupport;
import io.legaldocml.business.builder.support.DateSupport;
import io.legaldocml.business.builder.support.TermSupport;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface AmendmentInlineBuilder<T extends AmendmentInlineContainer<E>, E extends AknObject> extends ChangeSupport<T, E> {

}
