package io.legaldocml.akn.element;

import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.NUM;

/**
 * The element num is a heading element in a hierarchy that contains a number or any other ordered mechanism to identify
 * the structure.
 *
 * <pre>
 *   <xsd:element name="num" type="inline"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Num extends InlineType implements PopupStructureElement, SubFlowStructureElement {

    /**
     * Memory address.
     */
    private static final long ADDRESS_NUM = Buffers.address(NUM);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_NUM, 3);
        super.write(writer);
        writer.writeEnd(ADDRESS_NUM, 3);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return NUM;
    }

}