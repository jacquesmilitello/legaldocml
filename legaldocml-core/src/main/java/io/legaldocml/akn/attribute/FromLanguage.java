package io.legaldocml.akn.attribute;

import io.legaldocml.akn.AknObject;

/**
 * This attribute specifies the human language of the expression this is a translation of. Values are taken from the RFC
 * 4646.
 *
 * <pre>
 *   &lt;xsd:attributeGroup name="fromLanguage"&gt;
 * 		&lt;xsd:attribute name="fromLanguage" type="language" use="required"/&gt;
 * 	 &lt;xsd:attributeGroup&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface FromLanguage extends AknObject {

    /**
     * Attribute name "language".
     */
    String ATTRIBUTE = "fromLanguage";

    String getFromLanguage();

    void setFromLanguage(String language);

}