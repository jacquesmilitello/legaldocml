package io.legaldocml.akn.attribute;


import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.type.EidRef;

/**
 * The attribute for is used in modification elements to specify the element this is a modification of.
 *
 * ```xml
 * <xsd:attributeGroup name="for">
 *   <xsd:attribute name="for" type="eIdRef"/>
 * </xsd:attributeGroup>
 * ```
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface For extends AknObject {

    EidRef getFor();

    void setFor(EidRef for_);

}