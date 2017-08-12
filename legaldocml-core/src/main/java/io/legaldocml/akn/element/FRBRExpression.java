package io.legaldocml.akn.element;

import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

/**
 * The element FRBRExpression is the metadata container of identifying properties related to the Expression level
 * according to the FRBR hierarchy.
 *
 * <pre>
 *   &lt;xsd:element name="FRBRExpression"&gt;
 * 	  &lt;xsd:complexType&gt;
 * 	    &lt;xsd:complexContent&gt;
 * 	       &lt;xsd:extension base="coreProperties"&gt;
 * 		     &lt;xsd:sequence&gt;
 * 		       &lt;xsd:group ref="exprProperties"/&gt;
 * 		     &lt;xsd:sequence&gt;
 * 		   &lt;xsd:extension&gt;
 * 	     &lt;xsd:complexContent&gt;
 * 	   &lt;xsd:complexType&gt;
 *   &lt;xsd:element&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class FRBRExpression extends ExprProperties {

    /**
     * XML Tag element name.
     */
    public static final String ELEMENT = "FRBRExpression";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 14);
        super.write(writer);
        writer.writeEnd(ADDRESS, 14);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {
        super.read(reader);
        if (reader.getQName().equalsLocalName(ELEMENT)) {
            reader.nextStartOrEndElement();
        } else {
            throw new IllegalStateException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return ELEMENT;
    }

}