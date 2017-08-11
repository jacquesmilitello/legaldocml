package io.legaldocml.akn.attribute;


import io.legaldocml.akn.AknObject;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface Value extends AknObject {

    /**
     * Attribute name "value".
     */
    String ATTRIBUTE = "value";

    String getValue();

    void setValue(String value);
}