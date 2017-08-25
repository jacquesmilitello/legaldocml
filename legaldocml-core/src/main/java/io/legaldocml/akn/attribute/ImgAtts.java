package io.legaldocml.akn.attribute;

import io.legaldocml.akn.AknObject;

/**
 * These attributes specify the dimension of the image.
 *
 * ```xml
 * <xsd:attributeGroup name="imgAtts">
 *   <xsd:attribute name="width" type="xsd:integer"/>
 *   <xsd:attribute name="height" type="xsd:integer"/>
 * </xsd:attributeGroup>
 * ```
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface ImgAtts extends AknObject {

    Integer getWidth();

    void setWidth(Integer width);

    Integer getHeight();

    void setHeight(Integer height);

}
