package io.legaldocml.akn.attribute;

/**
 * <pre>
 *   <xsd:attributeGroup name="remarkType">
 *     <xsd:attributeGroup ref="agent"/>
 * 	   <xsd:attribute name="type" type="opinionType"/>
 *   </xsd:attributeGroup>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface OpinionType extends Agent {

    /**
     * Attribute name.
     */
    String ATTRIBUTE = Type.ATTRIBUTE;

    io.legaldocml.akn.type.OpinionType getType();

    void setType(io.legaldocml.akn.type.OpinionType type);
}