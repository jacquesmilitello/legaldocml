package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.CoreReqReq;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.AttributeGetterSetter;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

/**
 * The complex type inlinereq defines the content model and attributes shared by all blocks and inlines. Here the eId
 * attribute is required and also the refersTo is required.
 *
 * <pre>
 *   <xsd:complexType name="inlinereqreq" mixed="true">
 * 	   <xsd:choice minOccurs="0" maxOccurs="unbounded">
 * 	     <xsd:group ref="inlineCM" />
 * 	   <xsd:choice>
 * 	   <xsd:attributeGroup ref="corereqreq" />
 *   <xsd:complexType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class InlineReqReqType extends InlineTypeAbstract implements CoreReqReq {

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
        CoreReqReq.super.write(writer);
        super.write(writer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableMap<String, AttributeGetterSetter<AknObject>> attributes() {
        return ATTRIBUTES_REQ_REQ;
    }
}