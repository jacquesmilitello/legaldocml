package io.legaldocml.akn.attribute;

import io.legaldocml.akn.AknObject;
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
 * <pre>
 *   &lt;xsd:attributeGroup name="speechAtts"&gt;
 *     &lt;xsd:attributeGroup ref="agent"/&gt;
 * 	   &lt;xsd:attributeGroup ref="role"/&gt;
 * 	   &lt;xsd:attribute name="startTime" type="xsd:dateTime"/&gt;
 * 	   &lt;xsd:attribute name="endTime" type="xsd:dateTime"/&gt;
 * 	   &lt;xsd:attribute name="to" type="agentRef"/&gt;
 *   &lt;xsd:attributeGroup&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface SpeechAtts extends Agent, Role {

    /**
     * Attribute name "to".
     */
    String ATTRIBUTE_TO = "to";
    /**
     * Attribute name "startTime".
     */
    String ATTRIBUTE_START_TIME = "startTime";
    /**
     * Attribute name "endTime".
     */
    String ATTRIBUTE_END_TIME = "endTime";

    AgentRef getTo();

    void setTo(AgentRef to);

    LocalDateTime getStartTime();

    void setStartTime(LocalDateTime startTime);

    LocalDateTime getEndTime();

    void setEndTime(LocalDateTime endTime);



}
