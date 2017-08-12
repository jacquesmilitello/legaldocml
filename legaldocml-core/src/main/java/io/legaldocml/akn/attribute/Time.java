package io.legaldocml.akn.attribute;

import io.legaldocml.akn.AknObject;

import java.time.OffsetDateTime;

/**
 * The attribute time is used to give a normalized text for a time according to the XSD syntax HH:MM:SS.
 *
 * <pre>
 *   &lt;xsd:attributeGroup name="time"&gt;
 *     &lt;xsd:attribute name="date" use="required"&gt;
 * 	     &lt;xsd:simpleType>
 *         &lt;xsd:union memberTypes="xsd:date xsd:dateTime"/&gt;
 *       &lt;xsd:simpleType>
 *     &lt;xsd:attribute>
 * 	 &lt;xsd:attribute>
 *   &lt;xsd:attributeGroup&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface Time extends AknObject {

    /**
     * Attribute name "time".
     */
    String ATTRIBUTE = "time";

    OffsetDateTime getTime();

    void setTime(OffsetDateTime time);
}