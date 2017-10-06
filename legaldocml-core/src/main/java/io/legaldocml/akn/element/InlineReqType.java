package io.legaldocml.akn.element;

import io.legaldocml.akn.attribute.CoreReq;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

/**
 * The complex type inlinereq defines the content model and attributes shared by all blocks and inlines. Here the eId
 * attribute is required.
 *
 * <pre>
 *   <xsd:complexType name="inlinereq" mixed="true">
 * 	   <xsd:choice minOccurs="0" maxOccurs="unbounded">
 * 	     <xsd:group ref="inlineCM"/>
 * 	   <xsd:choice>
 * 	   <xsd:attributeGroup ref="corereq"/>
 *   <xsd:complexType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class InlineReqType extends InlineTypeAbstract implements CoreReq {

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(AknVisitor visitor) {
        getData().accept(visitor);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        CoreReq.super.write(writer);
        super.write(writer);
    }

}