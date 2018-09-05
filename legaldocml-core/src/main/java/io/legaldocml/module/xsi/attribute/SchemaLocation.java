package io.legaldocml.module.xsi.attribute;


import io.legaldocml.io.CoreAttribute;

public interface SchemaLocation extends CoreAttribute {

    /**
     * CoreAttribute name for "xsi:schemaLocation".
     */
    String ATTRIBUTE = "schemaLocation";

    String getSchemaLocation();

    void setSchemaLocation(String schemaLocation);
}
