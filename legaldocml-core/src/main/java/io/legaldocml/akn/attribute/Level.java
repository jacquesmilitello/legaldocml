package io.legaldocml.akn.attribute;

import io.legaldocml.akn.AknObject;

/**
 * The attribute level specifies the level of the toc element.
 *
 * ```xml
 * <xsd:attributeGroup name="level">
 *   <xsd:attribute name="level" type="xsd:string" use="required"/>
 * </xsd:attributeGroup>
 * ```
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface Level extends AknObject {

    String getLevel();

    void setLevel(String level);

}