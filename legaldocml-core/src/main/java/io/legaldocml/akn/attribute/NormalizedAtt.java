package io.legaldocml.akn.attribute;


import io.legaldocml.akn.AknObject;

/**
 * The attribute normalized is used in the inline element quantity to provide a normalized value of the number, if
 * appropriate.
 *
 * <pre>
 *   &lt;xsd:attributeGroup name="normalizedAtt"&gt;
 *     &lt;xsd:attribute name="normalized" type="xsd:string"/&gt;
 *  &lt;xsd:attributeGroup&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface NormalizedAtt extends AknObject {

    /**
     * Attribute name "normalized".
     */
    String ATTRIBUTE = "normalized";

    String getNormalized();

    void setNormalized(String normalized);

}