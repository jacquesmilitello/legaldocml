package io.legaldocml.akn.attribute;

import io.legaldocml.akn.AknObject;

/**
 * The attribute width of the element fillIn specifies the width of the fillIn element.
 *
 * <pre>
 *   &lt;xsd:attributeGroup name="fillInWidth"&gt;
 * 	   &lt;xsd:attribute name="width" type="xsd:string"/&gt;
 *   &lt;xsd:attributeGroup&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface FillInWidth extends AknObject {

    /**
     * Attribute name "width".
     */
    String ATTRIBUTE_WIDTH = "width";

    String getWidth();

    void setWidth(String width);

}
