package io.legaldocml.akn.attribute;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.type.MeaningMods;

/**
 * <pre>
 * 	&lt;xsd:attributeGroup name="meaningModType"&gt;
 * 		&lt;xsd:attribute name="type" type="MeaningMods" use="required"/&gt;
 * 	&lt;xsd:attributeGroup&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface MeaningModType extends AknObject {

    /**
     * Attribute name.
     */
    String ATTRIBUTE = Type.ATTRIBUTE;

    MeaningMods getType();

    void setType(MeaningMods type);
}