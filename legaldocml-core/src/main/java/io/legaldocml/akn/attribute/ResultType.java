package io.legaldocml.akn.attribute;

import io.legaldocml.akn.AknObject;


/**
 * <pre>
 *   &lt;xsd:attributeGroup name="resultType"&gt;
 *     &lt;xsd:attribute name="type" type="resultType" use="required"/&gt;
 *   &lt;/xsd:attributeGroup&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface ResultType extends AknObject {

    /**
     * Attribute name "type".
     */
    String ATTRIBUTE = Type.ATTRIBUTE;

    io.legaldocml.akn.type.ResultType getType();

    void setType(io.legaldocml.akn.type.ResultType type);

}