package io.legaldocml.akn.attribute;

import io.legaldocml.akn.type.AgentRef;

import java.time.LocalDateTime;

/**
 * The attributes in speechAtts are used in speeches to identify actors and roles of speeches. In particular, attribute
 * 'by' identifies the speaker, optional attribute 'as' identifies the role under which the speaker is speaking,
 * optional attribute startTime specifies the absolute date and time where the individual speech item started, optional
 * attribute endTime specifies the absolute date and time where the individual speech item ended, and optional attribute
 * to identifies the addressee of the speech. All of them are references to person or organization elements in the
 * references section.
 *
 * ```xml
 * <xsd:attributeGroup name="speechAtts">
 *   <xsd:attributeGroup ref="agent"/>
 *   <xsd:attributeGroup ref="role"/>
 *   <xsd:attribute name="startTime" type="xsd:dateTime"/>
 *   <xsd:attribute name="endTime" type="xsd:dateTime"/>
 *   <xsd:attribute name="to" type="agentRef"/>
 * </xsd:attributeGroup>
 * ```
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface SpeechAtts extends Agent, Role {

    AgentRef getTo();

    void setTo(AgentRef to);

    LocalDateTime getStartTime();

    void setStartTime(LocalDateTime startTime);

    LocalDateTime getEndTime();

    void setEndTime(LocalDateTime endTime);



}
