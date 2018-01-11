package io.legaldocml.akn.container;


import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.group.HTMLinline;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface HTMLinlineContainer<T extends AknObject> extends Container<T> {

    void add(HTMLinline htmLinline);

}
