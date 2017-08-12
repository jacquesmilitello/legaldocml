package io.legaldocml.akn.attribute;


import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.type.PosType;

/**
 * The attribute pos is used to identify the specific position of the reference (e.g., in source or destination)
 * with respect to the element being identified with the relative eId.
 *
 * <pre>
 * 	 &lt;xsd:attributeGroup name="pos"&gt;
 * 		&lt;xsd:attribute name="pos" type="posType" /&gt;
 * 	 &lt;xsd:attributeGroup&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface Pos extends AknObject {

    /**
     * Attribute name "pos".
     */
    String ATTRIBUTE = "pos";

    PosType getPos();

    void setPos(PosType posType);
}