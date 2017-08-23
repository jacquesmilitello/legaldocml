package io.legaldocml.akn.attribute;


import io.legaldocml.akn.AknObject;

/**
 * ```xml
 * <xsd:attributeGroup name="eventType">
 *   <xsd:attribute name="type" type="eventType"/>
 * </xsd:attributeGroup>
 * ```
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface EventType extends AknObject {

    /**
     * Attribute name "type".
     */
    String ATTRIBUTE = Type.ATTRIBUTE;

    io.legaldocml.akn.type.EventType getType();

    void setType(io.legaldocml.akn.type.EventType value);
}