package io.legaldocml.akn.element;

import io.legaldocml.util.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.LIST_INTRODUCTION;

/**
 * The element listIntroduction is an optional element of blockList before any item of the list itself.
 *
 * <pre>
 *   <xsd:element name="listIntroduction" type="inline"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class ListIntroduction extends InlineType {

    /**
     * Memory address.
     */
    private static final long ADDRESS_LIST_INTRODUCTION = Buffers.address(LIST_INTRODUCTION);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_LIST_INTRODUCTION, 16);
        super.write(writer);
        writer.writeEnd(ADDRESS_LIST_INTRODUCTION, 16);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return LIST_INTRODUCTION;
    }

}