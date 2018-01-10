package io.legaldocml.akn.element;

import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.util.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.FRBR_MANIFESTATION;

/**
 * The element FRBRManifestation is the metadata container of identifying properties related to the Manifestation level
 * according to the FRBR hierarchy.
 *
 * <pre>
 *   <xsd:element name="FRBRManifestation">
 * 	   <xsd:complexType>
 * 	     <xsd:complexContent>
 * 		   <xsd:extension base="coreProperties">
 * 		     <xsd:sequence>
 * 			   <xsd:group ref="manifProperties"/>
 * 		 	 <xsd:sequence>
 * 		   <xsd:extension>
 * 		 <xsd:complexContent>
 * 	   <xsd:complexType>
 *   <xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class FRBRManifestation extends ManifProperties {

    /**
     * Memory address.
     */
    private static final long ADDRESS_FRBR_MANIFESTATION = Buffers.address(FRBR_MANIFESTATION);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_FRBR_MANIFESTATION, 17);
        super.write(writer);
        writer.writeEnd(ADDRESS_FRBR_MANIFESTATION, 17);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {
        super.read(reader);
        if (reader.getQName().equalsLocalName(FRBR_MANIFESTATION)) {
            reader.nextStartOrEndElement();
        } else {
            throw new IllegalStateException(reader.getQName().toString());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return FRBR_MANIFESTATION;
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