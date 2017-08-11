package io.legaldocml.mod.xsi.attribute;


import io.legaldocml.io.Attribute;

public interface SchemaLocation extends Attribute {

    /**
     * Attribute name for "xsi:schemaLocation".
     */
    String ATTRIBUTE = "schemaLocation";

    String getSchemaLocation();

    void setSchemaLocation(String schemaLocation);
}
