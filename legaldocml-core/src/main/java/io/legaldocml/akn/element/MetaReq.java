package io.legaldocml.akn.element;

import io.legaldocml.akn.attribute.Core;
import io.legaldocml.akn.attribute.IdReq;
import io.legaldocml.io.Attribute;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;
import java.util.ArrayList;

/**
 * The complex type metareq defines the content model and attributes shared by all metadata elements. Here the eId
 * attribute is required.
 *
 * <pre>
 *   <xsd:complexType name="markerreq"/>
 *     <xsd:attributeGroup ref="core"/>
 *     <xsd:attributeGroup ref="idreq"/>
 *   <xsd:complexType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class MetaReq extends AbstractId implements Core, IdReq {

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
    public void write(XmlWriter writer) throws IOException {
        IdReq.super.write(writer);
        Core.super.write(writer);
    }

}