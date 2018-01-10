package io.legaldocml.akn.element;

import io.legaldocml.akn.group.ANcontainers;
import io.legaldocml.util.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.SUMMARY;

/**
 * The element summary is a block element in a debate to mark summaries of speeches that are individually not
 * interesting (e.g.: "Question put and agreed to").
 *
 * <pre>
 * 	 <xsd:element name="summary" type="inline"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Summary extends InlineType implements ANcontainers {

    /**
     * Memory address.
     */
    private static final long ADDRESS_SUMMARY = Buffers.address(SUMMARY);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_SUMMARY, 7);
        super.write(writer);
        writer.writeEnd(ADDRESS_SUMMARY, 7);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return SUMMARY;
    }


}