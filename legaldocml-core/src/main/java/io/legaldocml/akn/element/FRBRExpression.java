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
 *   <xsd:element name="FRBRExpression">
 * 	  <xsd:complexType>
 * 	    <xsd:complexContent>
 * 	       <xsd:extension base="coreProperties">
 * 		     <xsd:sequence>
 * 		       <xsd:group ref="exprProperties"/>
 * 		     <xsd:sequence>
 * 		   <xsd:extension>
 * 	     <xsd:complexContent>
 * 	   <xsd:complexType>
 *   <xsd:element>
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