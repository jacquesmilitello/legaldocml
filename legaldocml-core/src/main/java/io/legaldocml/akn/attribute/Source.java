package io.legaldocml.akn.attribute;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.type.AgentRef;

/**
 * The attribute source links to the agent (person, organization) providing the specific annotation or markup.
 *
 * <pre>
 *   &lt;xsd:attributeGroup name="source"&gt;
 * 	   &lt;xsd:attribute name="source" type="agentRef" use="required"/&gt;
 *   &lt;xsd:attributeGroup&gt;
 * </pre>
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