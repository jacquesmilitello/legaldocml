package io.legaldocml.akn.attribute;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.type.XmlDuration;

/**
 * The duration attribute is used to specify a duration when either the start or the end event of a time interval is not
 * known.
 * <p/>
 * <pre>
 *   <xsd:attributeGroup name="duration">
 * 	   <xsd:attribute name="duration" type="xsd:duration"/>
 *   </xsd:attributeGroup>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface Duration extends AknObject {

    /**
     * Attribute name.
     */
    String ATTRIBUTE = "duration";

    XmlDuration getDuration();

    void setDuration(XmlDuration duration);
}