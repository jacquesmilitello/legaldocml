package io.legaldocml.akn.attribute;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.type.TextualMods;

/**
 * ```xml
 * <xsd:attributeGroup name="textualModType">
 *   <xsd:attribute name="type" type="TextualMods" use="required"/>
 * </xsd:attributeGroup>
 * ```
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface TextualModType extends AknObject {

    /**
     * Attribute name.
     */
    String ATTRIBUTE = Type.ATTRIBUTE;

    TextualMods getType();

    void setType(TextualMods type);
}