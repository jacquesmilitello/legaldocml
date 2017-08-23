package io.legaldocml.akn.element;

import io.legaldocml.akn.group.ANinline;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import java.io.IOException;

/**
 * The element extractStructure is a popup element containing a full structure used as an extract from another document or position.
 *
 * <pre>
 *   <xsd:element name="extractStructure" type="popupStructure"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
@Deprecated
public final class ExtractStructure extends PopupStructure implements ANinline {

    /**
     * XML tag element name.
     */
    public static final String ELEMENT = "extractStructure";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 16);
        super.write(writer);
        writer.writeEnd(ADDRESS, 16);
    }

    /** {@inheritDoc} */
    @Override
    public String name() {
        return ELEMENT;
    }

}