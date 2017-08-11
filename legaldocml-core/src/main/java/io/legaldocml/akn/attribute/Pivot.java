package io.legaldocml.akn.attribute;

import io.legaldocml.akn.AknObject;

/**
 * This attribute specifies the human language used as a pivot in the translation. Values are taken from the RFC 4646.
 * <p/>
 * <pre>
 *   <xsd:attributeGroup name="pivot">
 * 		<xsd:attribute name="pivot" type="language"/>
 * 	 </xsd:attributeGroup>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface Pivot extends AknObject {

    /**
     * Attribute name "language".
     */
    String ATTRIBUTE = "pivot";

    String getPivot();

    void setPivot(String language);

}