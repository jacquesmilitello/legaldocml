package io.legaldocml.akn.attribute;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.type.TemporalGroupRef;

/**
 * The period attribute is used in versioned content and metadata elements to indicate a time interval in which they
 * were in force, in efficacy, or in any other type of interval as specified in the corresponding temporalGroup.
 *
 * ```xml
 * <xsd:attributeGroup name="period">
 *   <xsd:attribute name="period" type="temporalGroupRef"/>
 * </xsd:attributeGroup>
 * ```
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface Period extends AknObject {

    /**
     * Attribute name "period".
     */
    String ATTRIBUTE = "period";

    TemporalGroupRef getPeriod();

    void setPeriod(TemporalGroupRef period);

}