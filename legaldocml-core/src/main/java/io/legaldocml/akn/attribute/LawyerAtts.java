package io.legaldocml.akn.attribute;

import io.legaldocml.akn.type.AgentRef;

/**
 * The attributes in lawyerAtts are used in the inline element lawyer to identify representation aspects of lawyers. In
 * particular, attribute 'as' identifies his/her role, attribute 'for' identifies which party it represents, and
 * attribute 'empoweredBy' identifies another lawyer, if any, this lawyer received the power delegation of power..
 *
 * ```xml
 * <xsd:attributeGroup name="lawyerAtts">
 *   <xsd:attributeGroup ref="role"/>
 *   <xsd:attribute name="for" type="agentRef"/>
 *   <xsd:attribute name="empoweredBy" type="agentRef"/>
 * </xsd:attributeGroup>
 * ```
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface LawyerAtts extends Role {

    AgentRef getFor();

    void setFor(AgentRef for_);

    AgentRef getEmpoweredBy();

    void setEmpoweredBy(AgentRef empoweredBy);

}
