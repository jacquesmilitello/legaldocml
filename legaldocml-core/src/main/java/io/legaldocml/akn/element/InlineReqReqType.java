package io.legaldocml.akn.element;

import io.legaldocml.akn.attribute.CoreReqReq;
import io.legaldocml.akn.container.InlineCMContainer;
import io.legaldocml.akn.group.ANtitleInline;
import io.legaldocml.akn.group.InlineCM;
import io.legaldocml.akn.group.SubFlowElements;
import io.legaldocml.akn.util.AknList;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static java.util.Objects.requireNonNull;

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
public abstract class InlineReqReqType extends AbstractCore implements CoreReqReq, InlineCMContainer {

    /**
     * Container for all data fors this inline.
     */
    private final AknList<InlineCM> data = new AknList<>(new InlineCM[8]);

    /**
     * {@inheritDoc}
     */
    @Override
    public final void add(InlineCM inlineCM) {
        this.data.add(inlineCM);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(SubFlowElements el) {
        this.data.add(el);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(ANtitleInline titleInline) {
        this.data.add(requireNonNull(titleInline));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        CoreReqReq.super.write(writer);
        this.data.write(writer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {
        super.read(reader);
        InlineType.read(reader, this.data);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(AknVisitor visitor) {
        this.data.accept(visitor);
    }

}