package io.legaldocml.akn.attribute;

/**
 * ```xml
 * <xsd:attributeGroup name="remarkType">
 *   <xsd:attributeGroup ref="agent"/>
 *   <xsd:attribute name="type" type="opinionType"/>
 * </xsd:attributeGroup>
 * ```
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface OpinionType extends Agent {

    io.legaldocml.akn.type.OpinionType getType();

    void setType(io.legaldocml.akn.type.OpinionType type);

}