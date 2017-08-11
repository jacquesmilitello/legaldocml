package io.legaldocml.akn.attribute;

import io.legaldocml.akn.AknObject;

/**
 * The attribute name is used to give a name to all generic elements.
 * <p/>
 * <pre>
 *   <xsd:attributeGroup name="name">
 * 	   <xsd:attribute name="name" type="xsd:string" use="required"/>
 *   </xsd:attributeGroup>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface Name extends AknObject {

    /**
     * Attribute name "name".
     */
    String ATTRIBUTE = "name";

    String getName();

    void setName(String name);

}