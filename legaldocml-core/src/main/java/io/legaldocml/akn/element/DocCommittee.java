package io.legaldocml.akn.element;

import io.legaldocml.akn.attribute.ValueOpt;
import io.legaldocml.akn.group.ANtitleInline;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.util.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.DOC_COMMITTEE;

/**
 * The element docCommittee is an inline element within preface to identify the string used by the document detailing
 * the committee within which the document originated.
 *
 * <pre>
 *   <xsd:element name="docCommittee">
 * 	   <xsd:complexType mixed="true">
 * 	     <xsd:complexContent>
 * 		   <xsd:extension base="inline">
 * 		     <xsd:attributeGroup ref="optvalue"/>
 * 		   <xsd:extension>
 * 		 <xsd:complexContent>
 * 	   <xsd:complexType>
 *   <xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class DocCommittee extends InlineType implements ANtitleInline, ValueOpt {

    /**
     * Memory address.
     */
    private static final long ADDRESS_DOC_COMMITTEE = Buffers.address(DOC_COMMITTEE);

    private String value;

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_DOC_COMMITTEE, 12);
        ValueOpt.super.write(writer);
        super.write(writer);
        writer.writeEnd(ADDRESS_DOC_COMMITTEE, 12);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getValue() {
        return this.value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return DOC_COMMITTEE;
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