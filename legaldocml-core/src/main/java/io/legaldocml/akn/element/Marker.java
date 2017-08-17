package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.Name;
import io.legaldocml.akn.group.MarkerElements;
import io.legaldocml.io.CharArray;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import java.io.IOException;
import java.util.function.BiConsumer;

import static io.legaldocml.akn.element.Attributes.biConsumerString;
import static io.legaldocml.akn.util.XmlWriterHelper.writeName;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * the element marker is a generic element for a marker. It can be placed in a block instead of any of the other
 * markers. The attribute name is required and gives a name to the element.
 * <p>
 * <pre>
 *   &lt;xsd:element name="marker"&gt;
 *     &lt;xsd:complexType&gt;
 *       &lt;xsd:complexContent&gt;
 *         &lt;xsd:extension base="markerreq"&gt;
 *           &lt;xsd:attributeGroup ref="name"/&gt;
 *         &lt;xsd:extension&gt;
 *       &lt;xsd:complexContent&gt;
 *     &lt;xsd:complexType&gt;
 *   &lt;xsd:element&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Marker extends MarkerReq implements Name, MarkerElements {

    /**
     * XML tag element name.
     */
    public static final String ELEMENT = "marker";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    private static final ImmutableMap<String, BiConsumer<AknObject, CharArray>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, BiConsumer<AknObject, CharArray>>builder()
                .putAll(MarkerReq.ATTRIBUTES)
                .put(Name.ATTRIBUTE, biConsumerString(getFieldOffset(Marker.class, "name")))
                .build();
    }

    private String name;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return name;
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
        writer.writeStart(ADDRESS, 6);
        writeName(writer, this);
        super.write(writer);
        writer.writeEnd(ADDRESS, 6);
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