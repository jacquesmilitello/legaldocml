package io.legaldocml.akn.attribute;

import io.legaldocml.akn.AknObject;
import io.legaldocml.util.ReferenceRef;

/**
 * the attribute includedIn specifies the eId of the element of the references section identifying the document that
 * this portion is a portion of.
 *
 * <pre>
 *   &lt;xsd:attributeGroup name="portionAtt"&gt;
 * 	   &lt;xsd:attribute name="includedIn"  type="referenceRef" use="required"/&gt;
 *   &lt;xsd:attributeGroup&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface PortionAtt extends AknObject {

    /**
     * Attribute name.
     */
    String ATTRIBUTE = "includedIn";

    ReferenceRef getIncludedIn();

    void setIncludedIn(ReferenceRef referenceRef);
}