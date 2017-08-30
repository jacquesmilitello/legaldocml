package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknAttributes;
import io.legaldocml.akn.attribute.Source;
import io.legaldocml.akn.type.AgentRef;
import io.legaldocml.akn.util.AknList;
import io.legaldocml.akn.util.XmlReaderHelper;
import io.legaldocml.io.CharArray;
import io.legaldocml.io.Externalizable;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

import static io.legaldocml.akn.element.Attributes.biConsumerAgentRef;
import static io.legaldocml.akn.element.Groups.TLCs;
import static io.legaldocml.akn.element.Groups.convertSuper;
import static io.legaldocml.akn.element.Groups.docRefs;
import static io.legaldocml.akn.util.XmlWriterHelper.writeSource;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * The complex type refItems is a list of types of references used in the references section.
 *
 * <pre>
 *   <xsd:complexType name="refItems">
 * 	   <xsd:choice minOccurs="1" maxOccurs="unbounded">
 *       <xsd:group ref="docRefs"/>
 *       <xsd:group ref="TLCs"/>
 *     <xsd:choice>
 *     <xsd:attributeGroup ref="source"/>
 *   <xsd:complexType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class RefItems implements Source {

    private static final ImmutableMap<String, BiConsumer<Externalizable, CharArray>> ATTRIBUTES;

    private static final ImmutableMap<String, Supplier<RefItem>> ELEMS;

    static {
        ATTRIBUTES = ImmutableMap.<String, BiConsumer<Externalizable, CharArray>>builder()
                .put(AknAttributes.SOURCE, biConsumerAgentRef(getFieldOffset(RefItems.class, "source")))
                .build();

        ELEMS = ImmutableMap.<String, Supplier<RefItem>>builder()
                .putAll(convertSuper(docRefs()))
                .putAll(convertSuper(TLCs()))
                .build();
    }

    // Mandatory (min 1)
    private final AknList<RefItem> refItems = new AknList<RefItem>(new RefItem[6]);

    // Mandatory
    private AgentRef source;

    /**
     * {@inheritDoc}
     */
    public AgentRef getSource() {
        return this.source;
    }

    /**
     * {@inheritDoc}
     */
    public void setSource(AgentRef source) {
        this.source = source;
    }

    public final AknList<RefItem> getRefItems() {
        return this.refItems;
    }

    public final void add(RefItem item) {
        this.refItems.add(item);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writeSource(writer, this);
        this.refItems.write(writer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {
        Attributes.read(reader, this);
        XmlReaderHelper.read(reader, this.refItems, ELEMS);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableMap<String, BiConsumer<Externalizable, CharArray>> attributes() {
        return ATTRIBUTES;
    }
}