package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.RefersOpt;
import io.legaldocml.akn.type.ListReferenceRef;
import io.legaldocml.akn.type.RestrictionType;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.CharArray;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;
import java.util.function.BiConsumer;

import static io.legaldocml.akn.element.Attributes.biConsumerEnum;
import static io.legaldocml.akn.element.Attributes.biConsumerListReferenceRef;
import static io.legaldocml.akn.util.XmlWriterHelper.writeRefers;
import static io.legaldocml.akn.util.XmlWriterHelper.writeRestrictionType;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * The element restriction specifies information about a restriction (such as a jurisdiction specification) by pointing
 * to a specific legislative, geographic or temporal events through the refersTo attribute.
 *
 * <pre>
 *   &lt;xsd:element name="restriction" type="anyOtherType"&gt;
 * 	   &lt;xsd:complexType&gt;
 *       &lt;xsd:complexContent&gt;
 *         &lt;xsd:extension base="anyOtherType"&gt;
 *           &lt;xsd:attributeGroup ref="refers"/&gt;
 *           &lt;xsd:attributeGroup ref="restrictionType"/&gt;
 *         &lt;xsd:extension&gt;
 *       &lt;xsd:complexContent&gt;
 *     &lt;xsd:complexType&gt;
 *   &lt;xsd:element&gt;
 * <pre>
 *
 *     @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Restriction extends AnyOtherType implements RefersOpt, io.legaldocml.akn.attribute.RestrictionType {

    /**
     * XML tag element name.
     */
    public static final String ELEMENT = "restriction";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    private static final ImmutableMap<String, BiConsumer<AknObject, CharArray>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, BiConsumer<AknObject, CharArray>>builder()
                .putAll(AnyOtherType.ATTRIBUTES)
                .put(RefersOpt.ATTRIBUTE, biConsumerListReferenceRef(getFieldOffset(Restriction.class, "refersTo")))
                .put(io.legaldocml.akn.attribute.RestrictionType.ATTRIBUTE, biConsumerEnum(getFieldOffset(Restriction.class, "refersTo"), RestrictionType.class))
                .build();
    }

    private RestrictionType type;
    private ListReferenceRef refersTo;

    /**
     * {@inheritDoc}
     */
    @Override
    public RestrictionType getType() {
        return this.type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setType(RestrictionType type) {
        this.type = type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ListReferenceRef getRefersTo() {
        return this.refersTo;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRefersTo(ListReferenceRef refersTo) {
        this.refersTo = refersTo;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 11);
        writeRefers(writer, this);
        writeRestrictionType(writer, this);
        super.write(writer);
        writer.writeEnd(ADDRESS, 11);
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