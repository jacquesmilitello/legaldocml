package io.legaldocml.akn.attribute;

import io.legaldocml.akn.AknObject;

/**
 * The attribute name is used to give a name to all generic elements.
 *
 * ```xml
 * <xsd:attributeGroup name="name">
 *   <xsd:attribute name="name" type="xsd:string" use="required"/>
 * </xsd:attributeGroup>
 * ```
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface Name extends AknObject {

    String getName();

    void setName(String name);

}