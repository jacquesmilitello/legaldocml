package io.legaldocml.business.builder.group;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.container.PrefaceContainersContainer;
import io.legaldocml.business.builder.support.ContainerSupport;
import io.legaldocml.business.builder.support.LongTitleSupport;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface PrefaceContainersBuilder<T extends PrefaceContainersContainer<E>, E extends AknObject> extends ContainerSupport<T,E>, LongTitleSupport<T,E> {

}
