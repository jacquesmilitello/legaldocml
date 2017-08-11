package io.legaldocml.akn.element;

import io.legaldocml.akn.group.ANheaderInline;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

/**
 * The element courtType is an inline element within judgments to identify the string used by the document for the type
 * of the court doing the judgment.
 * <p/>
 * <pre>
 * 	 <xsd:element name="courtType" type="inline"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class CourtType extends InlineType implements ANheaderInline {

    /**
     * XML Tag element name.
     */
    public static final String ELEMENT = "courtType";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 9);
        super.write(writer);
        writer.writeEnd(ADDRESS, 9);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return ELEMENT;
    }

}