package io.legaldocml.akn.attribute;

/**
 * <pre>
 *   &lt;xsd:attributeGroup name="remarkType"&gt;
 *     &lt;xsd:attributeGroup ref="agent"/&gt;
 * 	   &lt;xsd:attribute name="type" type="opinionType"/&gt;
 *   &lt;xsd:attributeGroup&gt;
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