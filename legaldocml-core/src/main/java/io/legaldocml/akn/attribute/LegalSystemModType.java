package io.legaldocml.akn.attribute;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.type.LegalSystemMods;

/**
 * ```xml
 * <xsd:attributeGroup name="legalSystemModType">
 *   <xsd:attribute name="type" type="LegalSystemMods" use="required"/>
 * </xsd:attributeGroup>
 * ```
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface LegalSystemModType extends AknObject {

    LegalSystemMods getType();

    void setType(LegalSystemMods type);

}