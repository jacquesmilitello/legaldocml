package io.legaldocml.akn.attribute;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.type.EfficacyMods;

/**
 * ```xml
 * <xsd:attributeGroup name="efficacyModType">
 *   <xsd:attribute name="type" type="EfficacyMods" use="required"/>
 * </xsd:attributeGroup>
 * ```
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface EfficacyModType extends AknObject {

    /**
     * Attribute name.
     */
    String ATTRIBUTE = Type.ATTRIBUTE;

    EfficacyMods getType();

    void setType(EfficacyMods type);

}