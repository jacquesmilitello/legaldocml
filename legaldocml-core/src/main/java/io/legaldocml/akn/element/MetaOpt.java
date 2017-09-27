package io.legaldocml.akn.element;

import io.legaldocml.akn.attribute.Core;
import io.legaldocml.akn.attribute.IdOpt;
import io.legaldocml.io.Attribute;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;
import java.util.ArrayList;

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
public abstract class MetaOpt extends AbstractId implements Core, IdOpt {

    private java.util.List<Attribute> attributes;

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(Attribute attribute) {
        if (this.attributes == null) {
            this.attributes = new ArrayList<>();
        }
        this.attributes.add(attribute);
    }

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
       IdOpt.super.write(writer);
       Core.super.write(writer);
    }

}