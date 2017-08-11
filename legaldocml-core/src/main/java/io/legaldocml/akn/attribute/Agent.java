package io.legaldocml.akn.attribute;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.type.AgentRef;

/**
 * The attribute by is used to identify the agent of an action. It is a reference to a TLCPerson or TLCOrganization
 * element in the references section.
 * <p/>
 * <pre>
 *   <xsd:attributeGroup name="agent">
 * 	   <xsd:attribute name="by" type="agentRef" use="required"/>
 *   </xsd:attributeGroup>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface Agent extends AknObject {

    /**
     * Attribute name.
     */
    String ATTRIBUTE = "by";

    AgentRef getBy();

    void setBy(AgentRef by);
}