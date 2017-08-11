package io.legaldocml.akn.attribute;

import io.legaldocml.akn.AknObject;

/**
 * The attribute number is used to specify a number in the publication of the document.
 * <p/>
 * <pre>
 * 	<xsd:attributeGroup name="number">
 * 		<xsd:attribute name="number" type="xsd:string"/>
 * 	</xsd:attributeGroup>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface Number extends AknObject {

    /**
     * Attribute name "number".
     */
    String ATTRIBUTE = "number";

    String getNumber();

    void setNumber(String number);

}