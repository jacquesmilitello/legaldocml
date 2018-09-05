package io.legaldocml.module.xml.attribute;

import io.legaldocml.io.CoreAttribute;
import io.legaldocml.module.xml.type.Space;

public interface XmlSpace extends CoreAttribute {

    /**
     * CoreAttribute name "xml:space".
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