package io.legaldocml.akn.element;

import io.legaldocml.akn.attribute.CoreOpt;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

/**
 * The complex type inline defines the content model and attributes shared by all blocks and inlines. Here the eId
 * attribute is optional.
 *
 * <pre>
 *   <xsd:complexType name="inline" mixed="true">
 *     <xsd:choice minOccurs="0" maxOccurs="unbounded">
 *       <xsd:group ref="InlineCM"/>
 *     <xsd:choice>
 *     <xsd:attributeGroup ref="coreopt"/>
 *   <xsd:complexType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class InlineType extends InlineTypeAbstract implements CoreOpt {

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
        CoreOpt.super.write(writer);
        super.write(writer);
    }

}