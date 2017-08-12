package io.legaldocml.module.xml.attribute;

import io.legaldocml.io.Attribute;
import io.legaldocml.module.xml.type.Space;

public interface XmlSpace extends Attribute{

    /**
     * Attribute name "xml:space".
     */
    String ATTRIBUTE = "space";

    /**
     * @return the attribute value.
     */
    Space getSpace();

    /**
     * Set a space value.
     *
     * @param space the space value.
     */
    void setSpace(Space space);


}