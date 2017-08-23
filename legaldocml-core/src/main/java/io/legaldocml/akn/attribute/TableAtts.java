package io.legaldocml.akn.attribute;

import io.legaldocml.akn.AknObject;

/**
 * These attributes specify the characteristics of the table.
 *
 * ```xml
 * <xsd:attributeGroup name="tableAtts">
 *   <xsd:attribute name="width" type="xsd:integer"/>
 *   <xsd:attribute name="border" type="xsd:integer"/>
 *   <xsd:attribute name="cellspacing" type="xsd:integer"/>
 *   <xsd:attribute name="cellpadding" type="xsd:integer"/>
 * </xsd:attributeGroup>
 * ```
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface TableAtts extends AknObject {

    /**
     * Attribute name "width".
     */
    String ATTRIBUTE_WIDTH = "width";
    /**
     * Attribute name "border".
     */
    String ATTRIBUTE_BORDER = "border";
    /**
     * Attribute name "cellspacing".
     */
    String ATTRIBUTE_CELLSPACING = "cellspacing";
    /**
     * Attribute name "cellpadding".
     */
    String ATTRIBUTE_CELLPADDING = "cellpadding";

    Integer getWidth();

    void setWidth(Integer width);

    Integer getBorder();

    void setBorder(Integer border);

    Integer getCellspacing();

    void setCellspacing(Integer cellspacing);

    Integer getCellpadding();

    void setCellpadding(Integer cellpadding);

}
