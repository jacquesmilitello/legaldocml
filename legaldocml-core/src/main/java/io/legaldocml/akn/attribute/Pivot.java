package io.legaldocml.akn.attribute;

import io.legaldocml.akn.AknObject;

/**
 * This attribute specifies the human language used as a pivot in the translation. Values are taken from the RFC 4646.
 *
 * <pre>
 *   &lt;xsd:attributeGroup name="pivot"&gt;
 * 		&lt;xsd:attribute name="pivot" type="language"/&gt;
 * 	 &lt;xsd:attributeGroup&gt;
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