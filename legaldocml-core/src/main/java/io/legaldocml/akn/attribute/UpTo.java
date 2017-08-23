package io.legaldocml.akn.attribute;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.type.EidRef;

/**
 * The attribute upTo is used in range-based elements to specify the eId of the ending element of the range.
 *
 * ```xml
 * <xsd:attributeGroup name="upTo">
 *   <xsd:attribute name="upTo" type="eIdRef"/>
 * </xsd:attributeGroup>
 * ```
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface UpTo extends AknObject {

    /**
     * Attribute name "upTo".
     */
    String ATTRIBUTE = "upTo";

    EidRef getUpTo();

    void setUpTo(EidRef upTo);

}