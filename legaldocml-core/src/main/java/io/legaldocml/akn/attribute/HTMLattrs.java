package io.legaldocml.akn.attribute;

import io.legaldocml.akn.AknObject;

/**
 * These attributes are used to specify class, style and title of the element, exactly as in HTML<
 * <p/>
 * <pre>
 *   <xsd:attributeGroup name="HTMLattrs">
 * 	   <xsd:attribute name="class" type="xsd:string"/>
 * 	   <xsd:attribute name="style" type="xsd:string"/>
 * 	   <xsd:attribute name="title" type="xsd:string"/>
 *   </xsd:attributeGroup>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface HTMLattrs extends AknObject {

    /**
     * Attribute name "class".
     */
    String ATTRIBUTE_CLASS = "class";
    /**
     * Attribute name "style".
     */
    String ATTRIBUTE_STYLE = "style";
    /**
     * Attribute name "title".
     */
    String ATTRIBUTE_TITLE = "title";

    String getClazz();

    void setClazz(String clazz);

    String getStyle();

    void setStyle(String style);

    String getTitle();

    void setTitle(String title);

}