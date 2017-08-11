package io.legaldocml.akn.element;


import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

/**
 * The element FRBRprescriptive is the metadata property containing a boolean value to determine whether the document
 * contains prescriptive text (i.e., text that is or might become prescriptive, such as an act or a bill) or not (such
 * as, for instance, a non-normative resolution from an assembly.
 * <p/>
 * <pre>
 *   <xsd:element name="FRBRprescriptive" type="booleanValueType"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class FRBRprescriptive extends BooleanValueType {

    /**
     * XML Tag element name.
     */
    public static final String ELEMENT = "FRBRprescriptive";

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

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return ELEMENT;
    }
}