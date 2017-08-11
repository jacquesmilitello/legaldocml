package io.legaldocml.akn.attribute;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.type.LegalSystemMods;

/**
 * <pre>
 *   <xsd:attributeGroup name="legalSystemModType">
 * 	   <xsd:attribute name="type" type="LegalSystemMods" use="required"/>
 *   </xsd:attributeGroup>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface LegalSystemModType extends AknObject {

    /**
     * Attribute name.
     */
    String ATTRIBUTE = Type.ATTRIBUTE;

    LegalSystemMods getType();

    void setType(LegalSystemMods type);
}