package io.legaldocml.akn.attribute;

import io.legaldocml.akn.AknObject;

/**
 * The attribute number is used to specify a number in the publication of the document.
 *
 * <pre>
 * 	&lt;xsd:attributeGroup name="number"&gt;
 * 		&lt;xsd:attribute name="number" type="xsd:string"/&gt;
 * 	&lt;xsd:attributeGroup&gt;
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