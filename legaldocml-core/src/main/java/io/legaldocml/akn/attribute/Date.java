package io.legaldocml.akn.attribute;

import io.legaldocml.akn.AknObject;

import java.time.OffsetDateTime;

/**
 * The attribute date is used to give a normalized value for a date according to the XSD syntax YYYY-MM-DD or a
 * normalized value for a dateTime according to the XSD syntax YYYY-MM-DDThh:mm:ss(zzzz).
 *
 * ```xml
 * <xsd:attributeGroup name="date">
 *   <xsd:attribute name="date" use="required">
 * 	   <xsd:simpleType>
 *       <xsd:union memberTypes="xsd:date xsd:dateTime"/>
 *     </xsd:simpleType>
 *   </xsd:attribute>
 * </xsd:attributeGroup>
 * ```
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