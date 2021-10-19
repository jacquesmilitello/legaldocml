package io.legaldocml.akn.element;

import java.io.IOException;

import io.legaldocml.akn.attribute.Core;
import io.legaldocml.akn.attribute.IdOpt;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;

/**
 * The complex type metaopt defines the content model and attributes shared by all metadata elements. Here the eId
 * attribute is optional.
 *
 * <pre>
 *   <xsd:complexType name="metaopt">
 *     <xsd:attributeGroup ref="core"/>
 *     <xsd:attributeGroup ref="idopt"/>
 *   <xsd:complexType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class MetaOpt extends AbstractIdCore implements IdOpt {

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {
        super.read(reader);
        reader.nextStartOrEndElement();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        super.write(writer);
        IdOpt.super.write(writer);

    }

}