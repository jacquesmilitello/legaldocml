package io.legaldocml.business.builder.group;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.container.ANsubFlowContainer;
import io.legaldocml.business.builder.support.AuthorialNoteSupport;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface ANsubFlowBuilder<T extends ANsubFlowContainer<E>, E extends AknObject> extends AuthorialNoteSupport<T,E> {

}
