package io.legaldocml.akn.attribute;

import io.legaldocml.akn.AknObject;

/**
 * The attribute name is used to give a name to all generic elements.
 *
 * <pre>
 *   &lt;xsd:attributeGroup name="name"&gt;
 * 	   &lt;xsd:attribute name="name" type="xsd:string" use="required"/&gt;
 *   &lt;xsd:attributeGroup&gt;
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