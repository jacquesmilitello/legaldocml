package io.legaldocml.akn.element;

import io.legaldocml.akn.group.InlineCM;
import io.legaldocml.akn.util.AknList;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

/**
 * The complex type inlinereq defines the content model and attributes shared by all blocks and inlines. Here the eId
 * attribute is required and also the refersTo is required.
 *
 * <pre>
 *   &lt;xsd:complexType name="inlinereqreq" mixed="true"&gt;
 * 	   &lt;xsd:choice minOccurs="0" maxOccurs="unbounded"&gt;
 * 	     &lt;xsd:group ref="inlineCM" /&gt;
 * 	   &lt;xsd:choice&gt;
 * 	   &lt;xsd:attributeGroup ref="corereqreq" /&gt;
 *   &lt;xsd:complexType&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class InlineReqReqType extends CoreReqReqImpl {

    /**
     * Container for all data fors this inline.
     */
    private final AknList<InlineCM> data = new AknList<>(new InlineCM[8]);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        super.write(writer);
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