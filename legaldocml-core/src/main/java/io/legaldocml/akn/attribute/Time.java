package io.legaldocml.akn.attribute;

import io.legaldocml.akn.AknObject;

import java.time.OffsetDateTime;

/**
 * The attribute time is used to give a normalized text for a time according to the XSD syntax HH:MM:SS.
 *
 * ```xml
 * <xsd:attributeGroup name="time">
 *   <xsd:attribute name="date" use="required">
 *     <xsd:simpleType>
 *       <xsd:union memberTypes="xsd:date xsd:dateTime"/>
 *     </xsd:simpleType>
 *   </xsd:attribute>
 * </xsd:attributeGroup>
 * ```
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface Time extends AknObject {

    OffsetDateTime getTime();

    void setTime(OffsetDateTime time);

}