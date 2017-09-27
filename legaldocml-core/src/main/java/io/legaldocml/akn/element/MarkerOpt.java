package io.legaldocml.akn.element;

import io.legaldocml.akn.attribute.CoreOpt;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

/**
 * The complex type markeropt defines the content model and attributes shared by all marker elements. Here the eId
 * attribute is optional.
 *
 * <pre>
 *   <xsd:complexType name="markeropt"/>
 * 	   <xsd:attributeGroup ref="coreopt" />
 *   <xsd:complexType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class MarkerOpt extends AbstractCore implements CoreOpt {

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        CoreOpt.super.write(writer);
    }
}