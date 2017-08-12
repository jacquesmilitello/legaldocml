package io.legaldocml.akn.attribute;

import io.legaldocml.akn.AknObject;

/**
 * The attribute level specifies the level of the toc element.
 *
 * <pre>
 *   &lt;xsd:attributeGroup name="level"&gt;
 *     &lt;xsd:attribute name="level" type="xsd:string" use="required"/&gt;
 * 	 &lt;xsd:attributeGroup&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface Level extends AknObject {

    /**
     * Attribute name "level".
     */
    String ATTRIBUTE = "level";

    String getLevel();

    void setLevel(String level);

}