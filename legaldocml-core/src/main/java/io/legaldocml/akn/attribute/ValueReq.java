package io.legaldocml.akn.attribute;


import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.util.XmlWriterHelper.writeValue;

/**
 * The attribute value is used to specify a value for metadata elements. In elements using this attribute definition the
 * value attribute is required.
 *
 * ```xml
 * <xsd:attributeGroup name="value">
 *   <xsd:attribute name="value" type="xsd:string" use="required"/>
 * </xsd:attributeGroup>
 * ```
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface ValueReq extends Value {

    /**
     * {@inheritDoc}
     */
    @Override
    default void write(XmlWriter writer) throws IOException {
        writeValue(writer, this);
    }

}