package io.legaldocml.akn.attribute;

import io.legaldocml.akn.AknObject;
import io.legaldocml.util.ReferenceRef;

/**
 * the attribute includedIn specifies the eId of the element of the references section identifying the document that
 * this portion is a portion of.
 *
 * ```xml
 * <xsd:attributeGroup name="portionAtt">
 *   <xsd:attribute name="includedIn"  type="referenceRef" use="required"/>
 * </xsd:attributeGroup>
 * ```
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface PortionAtt extends AknObject {

    ReferenceRef getIncludedIn();

    void setIncludedIn(ReferenceRef referenceRef);
}