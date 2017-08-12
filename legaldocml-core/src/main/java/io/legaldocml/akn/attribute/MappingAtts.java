package io.legaldocml.akn.attribute;

import io.legaldocml.akn.type.EidRef;
import io.legaldocml.akn.type.EventRefRef;
import io.legaldocml.akn.type.WidRef;

/**
 * The mappingAtts attributes represent the reference to the permanent wId (attribute original) of a structure, and to
 * the eId (attribute current) such structure had during the time interval included between an initial temporalGroup
 * (attribute start) and a final temporalGroup (attribute end).
 *
 * <pre>
 *   &lt;xsd:attributeGroup name="mappingAtts"&gt;
 * 	   &lt;xsd:attribute name="original" type="wIdRef"/&gt;
 *     &lt;xsd:attribute name="current" type="eIdRef"/&gt;
 *     &lt;xsd:attribute name="start" type="eventRefRef"/&gt;
 *     &lt;xsd:attribute name="end" type="eventRefRef"/&gt;
 *   &lt;xsd:attributeGroup&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface MappingAtts extends Role, Agent {

    /**
     * Attribute name "original".
     */
    String ATTRIBUTE_ORIGINAL = "original";

    /**
     * Attribute name "current".
     */
    String ATTRIBUTE_CURRENT = "current";

    /**
     * Attribute name "start".
     */
    String ATTRIBUTE_START = "start";
    /**
     * Attribute name "end".
     */
    String ATTRIBUTE_END = "end";


    WidRef getOriginal();

    void setOriginal(WidRef original);

    EidRef getCurrent();

    void setCurrent(EidRef current);

    EventRefRef getStart();

    void setStart(EventRefRef start);

    EventRefRef getEnd();

    void setEnd(EventRefRef end);
}
