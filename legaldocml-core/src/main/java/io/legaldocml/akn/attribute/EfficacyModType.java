package io.legaldocml.akn.attribute;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.type.EfficacyMods;

/**
 * <pre>
 *   &lt;xsd:attributeGroup name="efficacyModType"&gt;
 * 	   &lt;xsd:attribute name="type" type="EfficacyMods" use="required"/&gt;
 *   &lt;xsd:attributeGroup&gt;
 * </pre>
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