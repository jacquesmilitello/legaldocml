package io.legaldocml.akn.attribute;

import io.legaldocml.akn.AknObject;

/**
 * These attributes specify the dimension of the image.
 *
 * <pre>
 *   &lt;xsd:attributeGroup name="imgAtts"&gt;
 * 	   &lt;xsd:attribute name="width" type="xsd:integer"/&gt;
 *     &lt;xsd:attribute name="height" type="xsd:integer"/&gt;
 *   &lt;xsd:attributeGroup&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface ImgAtts extends AknObject {

    /**
     * Attribute name "width".
     */
    String ATTRIBUTE_WIDTH = "width";
    /**
     * Attribute name "height".
     */
    String ATTRIBUTE_HEIGHT = "height";

    Integer getWidth();

    void setWidth(Integer width);

    Integer getHeight();

    void setHeight(Integer height);

}
