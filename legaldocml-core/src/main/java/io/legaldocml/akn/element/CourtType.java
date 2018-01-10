package io.legaldocml.akn.element;

import io.legaldocml.akn.group.ANheaderInline;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.util.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.COURT_TYPE;

/**
 * The element courtType is an inline element within judgments to identify the string used by the document for the type
 * of the court doing the judgment.
 *
 * <pre>
 * 	 <xsd:element name="courtType" type="inline"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class CourtType extends InlineType implements ANheaderInline {

    /**
     * Memory address.
     */
    private static final long ADDRESS_COURT_TYPE = Buffers.address(COURT_TYPE);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_COURT_TYPE, 9);
        super.write(writer);
        writer.writeEnd(ADDRESS_COURT_TYPE, 9);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return COURT_TYPE;
    }

}