package io.legaldocml.akn.attribute;


import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.util.XmlWriterHelper.writeOptValue;

/**
 * The attribute text is used to specify a text for metadata elements. In elements using this attribute definition the
 * text attribute is optional.
 * <p>
 * ```xml
 *   <xsd:attributeGroup name="optvalue">
 *     <xsd:attribute name="text" type="xsd:string"/>
 *   </xsd:attributeGroup>
 * ```
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface ValueOpt extends Value {

    /**
     * {@inheritDoc}
     */
    @Override
    default void write(XmlWriter writer) throws IOException {
        writeOptValue(writer, this);
    }

}