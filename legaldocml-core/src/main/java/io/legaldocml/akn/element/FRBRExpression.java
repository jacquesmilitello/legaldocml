package io.legaldocml.akn.element;

import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.util.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.FRBR_EXPRESSION;

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
     * Memory address.
     */
    private static final long ADDRESS_FRBR_EXPRESSION = Buffers.address(FRBR_EXPRESSION);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_FRBR_EXPRESSION, 14);
        super.write(writer);
        writer.writeEnd(ADDRESS_FRBR_EXPRESSION, 14);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {
        super.read(reader);
        if (reader.getQName().equalsLocalName(FRBR_EXPRESSION)) {
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
        return FRBR_EXPRESSION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(AknVisitor visitor) {
        if (visitor.visitEnter(this)) {
            super.accept(visitor);
            visitor.visitLeave(this);
        }
    }
}