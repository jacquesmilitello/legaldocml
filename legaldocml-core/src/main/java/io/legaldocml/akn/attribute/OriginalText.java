package io.legaldocml.akn.attribute;


import io.legaldocml.akn.AknObject;

/**
 * The attribute originalText contains the original text of a placeholder.
 *
 * <pre>
 *   &lt;xsd:attributeGroup name="originalText"&gt;
 *     &lt;xsd:attribute name="originalText" type="xsd:string"/&gt;
 *   &lt;xsd:attributeGroup&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface OriginalText extends AknObject {

    /**
     * Attribute name "originalText".
     */
    String ATTRIBUTE = "originalText";

    String getOriginalText();

    void setOriginalText(String originalText);

}