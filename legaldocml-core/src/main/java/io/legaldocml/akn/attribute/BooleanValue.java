package io.legaldocml.akn.attribute;

import io.legaldocml.akn.AknObject;

/**
 * The attribute value is used here to specify a boolean value for metadata elements. In elements using this attribute
 * definition the value attribute is required.
 *
 * <pre>
 * 	&lt;xsd:attributeGroup name="booleanvalue"&gt;
 * 		&lt;xsd:attribute name="value" type="xsd:boolean" use="required"/&gt;
 * 	&lt;xsd:attributeGroup&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface BooleanValue extends AknObject {

    /**
     * Attribute name.
     */
    String ATTRIBUTE = Value.ATTRIBUTE;

    Boolean getValue();

    void setValue(Boolean value);
}