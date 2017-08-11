package io.legaldocml.akn.attribute;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.type.TimeType;

/**
 * <pre>
 *   <xsd:attributeGroup name="recordedTimeType">
 * 	   <xsd:attribute name="type" type="timeType"/>
 *   </xsd:attributeGroup>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface RecordedTimeType extends AknObject {

    /**
     * Attribute name.
     */
    String ATTRIBUTE = Type.ATTRIBUTE;

    TimeType getType();

    void setType(TimeType type);
}