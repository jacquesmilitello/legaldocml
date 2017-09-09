package io.legaldocml.akn.element;

import io.legaldocml.akn.group.ANtitleInline;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.DOCKET_NUMBER;

/**
 * The element docketNumber is an inline element within preface to identify the string used by the document for the
 * number of the docket, case, file, etc which the document belongs to.
 *
 * <pre>
 * 	 <xsd:element name="docketNumber" type="inline"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class DocketNumber extends InlineType implements ANtitleInline {

    /**
     * Memory address.
     */
    private static final long ADDRESS_DOCKET_NUMBER = Buffers.address(DOCKET_NUMBER);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_DOCKET_NUMBER, 12);
        super.write(writer);
        writer.writeEnd(ADDRESS_DOCKET_NUMBER, 12);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return DOCKET_NUMBER;
    }

}