package io.legaldocml.akn.attribute;


import io.legaldocml.akn.AknObject;

/**
 * The attribute originalText contains the original text of a placeholder.
 * <p/>
 * <pre>
 *   <xsd:attributeGroup name="originalText">
 *     <xsd:attribute name="originalText" type="xsd:string"/>
 *   </xsd:attributeGroup>
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