package io.legaldocml.akn.attribute;


import io.legaldocml.akn.AknObject;

/**
 * The attribute normalized is used in the inline element quantity to provide a normalized value of the number, if
 * appropriate.
 *
 * ```xml
 * <xsd:attributeGroup name="normalizedAtt">
 *   <xsd:attribute name="normalized" type="xsd:string"/>
 * </xsd:attributeGroup>
 * ```
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