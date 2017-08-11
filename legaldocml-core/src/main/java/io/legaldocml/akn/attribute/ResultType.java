package io.legaldocml.akn.attribute;

import io.legaldocml.akn.AknObject;

public interface ResultType extends AknObject {

    /**
     * Attribute name "type".
     */
    String ATTRIBUTE = Type.ATTRIBUTE;

    io.legaldocml.akn.type.ResultType getType();

    void setType(io.legaldocml.akn.type.ResultType type);
    
}