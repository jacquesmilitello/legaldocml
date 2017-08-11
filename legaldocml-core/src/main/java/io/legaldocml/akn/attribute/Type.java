package io.legaldocml.akn.attribute;


import io.legaldocml.akn.AknObject;

/**
 * The type attribute is used in various element to identify a type without restrictions as to its values.
 * <p/>
 * <pre>
 *   <xsd:attributeGroup name="type">
 *     <xsd:attribute name="type" type="xsd:string"/>
 *   </xsd:attributeGroup>
 * </pre>
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface Type extends AknObject {

    /**
     * Attribute name "type".
     */
    String ATTRIBUTE = "type";

    String getType();

    void setType(String value);
}