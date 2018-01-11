package io.legaldocml.akn.container;


import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.group.ANsemanticInline;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface ANsemanticInlineContainer<T extends AknObject> extends Container<T> {

    void add(ANsemanticInline inline);

}
