package io.legaldocml.akn.attribute;

import io.legaldocml.akn.AknObject;

/**
 * These attributes are used in the analysis to allow manifestation editors to specify whether the analysis is complete
 * and/or ignored in the text.
 *
 * <pre>
 *   &lt;xsd:attributeGroup name="modifiers"&gt;
 * 	   &lt;xsd:attribute name="exclusion" type="xsd:boolean" /&gt;
 * 	   &lt;xsd:attribute name="incomplete" type="xsd:boolean" /&gt;
 *   &lt;xsd:attributeGroup&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface Modifiers extends AknObject {

    String ATTRIBUTE_EXCLUSION = "exclusion";
    String ATTRIBUTE_INCOMPLETE = "incomplete";

    Boolean getExclusion();

    void setExclusion(Boolean exclusion);

    Boolean getIncomplete();

    void setIncomplete(Boolean incomplete);
}