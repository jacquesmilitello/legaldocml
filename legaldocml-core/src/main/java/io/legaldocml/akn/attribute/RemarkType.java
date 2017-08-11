package io.legaldocml.akn.attribute;

import io.legaldocml.akn.AknObject;

/**
 * <pre>
 *   <xsd:attributeGroup name="remarkType">
 * 	   <xsd:attribute name="type" type="remarkType"/>
 *   </xsd:attributeGroup>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface RemarkType extends AknObject {

    /**
     * Attribute name.
     */
    String ATTRIBUTE = Type.ATTRIBUTE;

    io.legaldocml.akn.type.RemarkType getType();

    void setType(io.legaldocml.akn.type.RemarkType type);
}