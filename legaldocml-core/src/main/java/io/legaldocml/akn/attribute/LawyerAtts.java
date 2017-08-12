package io.legaldocml.akn.attribute;

import io.legaldocml.akn.type.AgentRef;

/**
 * The attributes in lawyerAtts are used in the inline element lawyer to identify representation aspects of lawyers. In
 * particular, attribute 'as' identifies his/her role, attribute 'for' identifies which party it represents, and
 * attribute 'empoweredBy' identifies another lawyer, if any, this lawyer received the power delegation of power..
 *
 * <pre>
 * 	&lt;xsd:attributeGroup name="lawyerAtts"&gt;
 *      &lt;xsd:attributeGroup ref="role"/&gt;
 *      &lt;xsd:attribute name="for" type="agentRef"/&gt;
 *      &lt;xsd:attribute name="empoweredBy" type="agentRef"/&gt;
 * 	&lt;xsd:attributeGroup&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface LawyerAtts extends Role {

    /**
     * Attribute name "for".
     */
    String ATTRIBUTE_FOR = "for";

    /**
     * Attribute name "empoweredBy".
     */
    String ATTRIBUTE_EMPOWERED_BY = "empoweredBy";

    AgentRef getFor();

    void setFor(AgentRef for_);

    AgentRef getEmpoweredBy();

    void setEmpoweredBy(AgentRef empoweredBy);

}
