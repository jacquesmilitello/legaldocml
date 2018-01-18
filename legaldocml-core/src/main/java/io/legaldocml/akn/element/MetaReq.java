package io.legaldocml.akn.element;

import java.io.IOException;

import io.legaldocml.akn.attribute.Core;
import io.legaldocml.akn.attribute.IdReq;
import io.legaldocml.io.XmlWriter;

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
public abstract class MetaReq extends AbstractIdCore implements Core, IdReq {

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        IdReq.super.write(writer);
        Core.super.write(writer);
    }

}