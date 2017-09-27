package io.legaldocml.akn.element;

import io.legaldocml.akn.attribute.CoreReq;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

/**
 * The complex type markeropt defines the content model and attributes shared by all marker elements. Here the eId
 * attribute is required.
 *
 * <pre>
 *   <xsd:complexType name="markerreq"/>
 * 	   <xsd:attributeGroup ref="corereq" />
 *   <xsd:complexType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class MarkerReq extends AbstractCore implements CoreReq {

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        CoreReq.super.write(writer);
    }
}