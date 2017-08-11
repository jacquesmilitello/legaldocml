package io.legaldocml.akn.attribute;

import io.legaldocml.akn.AknObject;

/**
 * The attribute level specifies the level of the toc element.
 * <p/>
 * <pre>
 *   <xsd:attributeGroup name="level">
 *     <xsd:attribute name="level" type="xsd:string" use="required"/>
 * 	 </xsd:attributeGroup>
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