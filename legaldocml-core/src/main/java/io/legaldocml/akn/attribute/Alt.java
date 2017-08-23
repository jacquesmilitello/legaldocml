package io.legaldocml.akn.attribute;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.type.EidRef;

/**
 * The attribute alternativeTo is used to specify, when the element contains an alternative version of some content, the
 * eId of the main element which this element is an alternative copy of.
 * ```xml
 * <xsd:attributeGroup name="alt">
 *   <xsd:attribute name="alternativeTo" type="xsd:anyURI"/>
 * </xsd:attributeGroup>
 * ```
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface Alt extends AknObject {

    /**
     * Attribute name.
     */
    String ATTRIBUTE = "alternativeTo";

    EidRef getAlternativeTo();

    void setAlternativeTo(EidRef alternativeTo);

}