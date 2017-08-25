package io.legaldocml.akn.attribute;

import io.legaldocml.akn.AknObject;

/**
 * The attribute value is used here to specify a boolean value for metadata elements. In elements using this attribute
 * definition the value attribute is required.
 *
 * ```xml
 * <xsd:attributeGroup name="booleanvalue">
 *   <xsd:attribute name="value" type="xsd:boolean" use="required"/>
 * </xsd:attributeGroup>
 * ```
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface BooleanValue extends AknObject {

    Boolean getValue();

    void setValue(Boolean value);

}