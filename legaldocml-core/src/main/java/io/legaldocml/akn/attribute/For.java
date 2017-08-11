package io.legaldocml.akn.attribute;


import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.type.EidRef;

/**
 * The attribute for is used in modification elements to specify the element this is a modification of.
 * <p/>
 * <pre>
 *   <xsd:attributeGroup name="for">
 *     <xsd:attribute name="for" type="eIdRef"/>
 *  </xsd:attributeGroup>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface For extends AknObject {

    /**
     * Attribute name "for".
     */
    String ATTRIBUTE = "for";

    EidRef getFor();

    void setFor(EidRef for_);

}