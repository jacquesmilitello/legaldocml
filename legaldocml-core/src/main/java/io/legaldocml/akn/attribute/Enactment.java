package io.legaldocml.akn.attribute;

import io.legaldocml.akn.type.StatusType;

/**
 * These attributes are those already defined in attribute list "period", plus the attribute status, that allows to
 * specify the status of the piece of text it wraps.
 *
 * <pre>
 *   &lt;xsd:attributeGroup name="enactment"&gt;
 * 	   &lt;xsd:attributeGroup ref="period"/&gt;
 * 	   &lt;xsd:attribute name="status" type="statusType"/&gt;
 *   &lt;xsd:attributeGroup&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface Enactment extends Period {

    /**
     * Attribute name "status".
     */
    String ATTRIBUTE = "status";

    StatusType getStatus();

    void setStatus(StatusType status);
}