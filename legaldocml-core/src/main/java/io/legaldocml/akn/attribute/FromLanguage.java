package io.legaldocml.akn.attribute;

import io.legaldocml.akn.AknObject;

/**
 * This attribute specifies the human language of the expression this is a translation of. Values are taken from the RFC
 * 4646.
 * <p/>
 * <pre>
 *   <xsd:attributeGroup name="fromLanguage">
 * 		<xsd:attribute name="fromLanguage" type="language" use="required"/>
 * 	 </xsd:attributeGroup>
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