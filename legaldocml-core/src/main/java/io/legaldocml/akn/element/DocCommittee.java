package io.legaldocml.akn.element;

import io.legaldocml.akn.attribute.ValueOpt;
import io.legaldocml.akn.group.ANtitleInline;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.util.XmlWriterHelper.writeOptValue;

/**
 * The element docCommittee is an inline element within preface to identify the string used by the document detailing
 * the committee within which the document originated.
 *
 * <pre>
 *   &lt;xsd:element name="docCommittee"&gt;
 * 	   &lt;xsd:complexType mixed="true"&gt;
 * 	     &lt;xsd:complexContent&gt;
 * 		   &lt;xsd:extension base="inline"&gt;
 * 		     &lt;xsd:attributeGroup ref="optvalue"/&gt;
 * 		   &lt;xsd:extension&gt;
 * 		 &lt;xsd:complexContent&gt;
 * 	   &lt;xsd:complexType&gt;
 *   &lt;xsd:element&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class DocCommittee extends InlineType implements ANtitleInline, ValueOpt {

    /**
     * XML element name.
     */
    public static final String ELEMENT = "docCommittee";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    private String value;

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 12);
        writeOptValue(writer, this);
        super.write(writer);
        writer.writeEnd(ADDRESS, 12);
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
        return ELEMENT;
    }

}