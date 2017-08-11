package io.legaldocml.akn.attribute;

import io.legaldocml.akn.AknObject;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface RestrictionType extends AknObject {

    /**
     * Attribute name "type".
     */
    String ATTRIBUTE = Type.ATTRIBUTE;

    io.legaldocml.akn.type.RestrictionType getType();

    void setType(io.legaldocml.akn.type.RestrictionType type);
}