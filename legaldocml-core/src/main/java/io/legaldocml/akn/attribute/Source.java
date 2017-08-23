package io.legaldocml.akn.attribute;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.type.AgentRef;

/**
 * The attribute source links to the agent (person, organization) providing the specific annotation or markup.
 *
 * ```xml
 * <xsd:attributeGroup name="source">
 *   <xsd:attribute name="source" type="agentRef" use="required"/>
 * </xsd:attributeGroup>
 * ```
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface Source extends AknObject {

    /**
     * Attribute name "source".
     */
    String ATTRIBUTE = "source";

    AgentRef getSource();

    void setSource(AgentRef source);
}