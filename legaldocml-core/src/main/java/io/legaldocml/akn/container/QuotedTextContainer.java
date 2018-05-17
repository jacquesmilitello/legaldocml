package io.legaldocml.akn.container;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.element.ComponentRef;
import io.legaldocml.akn.element.QuotedText;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface QuotedTextContainer<T extends AknObject> extends Container<T> {

    void add(QuotedText quotedText);

}