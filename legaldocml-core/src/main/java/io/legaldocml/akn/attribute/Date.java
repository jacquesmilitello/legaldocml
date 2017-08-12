package io.legaldocml.akn.attribute;

import io.legaldocml.akn.AknObject;

import java.time.OffsetDateTime;

/**
 * The attribute date is used to give a normalized value for a date according to the XSD syntax YYYY-MM-DD or a
 * normalized value for a dateTime according to the XSD syntax YYYY-MM-DDThh:mm:ss(zzzz).
 *
 * <pre>
 *   &lt;xsd:attributeGroup name="date"&gt;
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
public interface Date extends AknObject {

    /**
     * Attribute name "date".
     */
    String ATTRIBUTE = "date";

    OffsetDateTime getDate();

    void setDate(OffsetDateTime date);
}