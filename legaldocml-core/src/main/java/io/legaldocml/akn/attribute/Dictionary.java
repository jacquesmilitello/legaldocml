package io.legaldocml.akn.attribute;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.type.ReferenceRef;

/**
 * This attribute specifies the eId of the element in the references section detailing the dictionary used for the
 * keyword.
 * <p/>
 * <pre>
 *   <xsd:attributeGroup name="dictionary">
 *     <xsd:attribute name="dictionary" type="referenceRef" use="required"/>
 *  </xsd:attributeGroup>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface Dictionary extends AknObject {

    /**
     * Attribute name "dictionary".
     */
    String ATTRIBUTE = "dictionary";

    ReferenceRef getDictionary();

    void setDictionary(ReferenceRef dictionary);

}