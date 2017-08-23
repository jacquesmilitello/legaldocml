package io.legaldocml.akn.attribute;


import io.legaldocml.akn.AknObject;

/**
 * The attribute target specifies the target of the a element.
 *
 * ```xml
 * <xsd:attributeGroup name="target">
 *   <xsd:attribute name="target" type="xsd:string"/>
 * </xsd:attributeGroup>
 * ```
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface Target extends AknObject {

    /**
     * Attribute name "value".
     */
    String ATTRIBUTE = "target";

    String getTarget();

    void setTarget(String value);

}