package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.Name;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.CharArray;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;
import java.util.function.BiConsumer;

import static io.legaldocml.akn.element.Attributes.biConsumerString;
import static io.legaldocml.akn.util.XmlWriterHelper.writeName;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * The element note is a metadata element containing the text of the footnote and endnote specified.
 *
 * <pre>
 *   &lt;xsd:element name="subFlow"&gt;
 * 	   &lt;xsd:complexType&gt;
 *       &lt;xsd:complexContent&gt;
 *         &lt;xsd:extension base="subFlowStructure"&gt;
 *           &lt;xsd:attributeGroup ref="name"/&gt;
 *         &lt;xsd:extension&gt;
 *       &lt;xsd:complexContent&gt;
 *     &lt;xsd:complexType&gt;
 * 	 &lt;xsd:element&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class SubFlow extends SubFlowStructure implements Name {

    /**
     * XML tag element name.
     */
    public static final String ELEMENT = "subFlow";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);


    private static final ImmutableMap<String, BiConsumer<AknObject, CharArray>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, BiConsumer<AknObject, CharArray>>builder()
                .putAll(SubFlowStructure.ATTRIBUTES)
                .put(Name.ATTRIBUTE, biConsumerString(getFieldOffset(SubFlow.class, "name")))
                .build();
    }

    private String name;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 7);
        writeName(writer, this);
        super.write(writer);
        writer.writeEnd(ADDRESS, 7);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return ELEMENT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableMap<String, BiConsumer<AknObject, CharArray>> attributes() {
        return ATTRIBUTES;
    }
}