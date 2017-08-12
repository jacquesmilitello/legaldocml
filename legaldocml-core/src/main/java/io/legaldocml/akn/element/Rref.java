package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.Range;
import io.legaldocml.akn.attribute.RangeReq;
import io.legaldocml.akn.attribute.UpTo;
import io.legaldocml.akn.group.ANinline;
import io.legaldocml.akn.type.EidRef;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.CharArray;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;
import java.util.function.BiConsumer;

import static io.legaldocml.akn.element.Attributes.biConsumerEidRef;
import static io.legaldocml.akn.util.XmlWriterHelper.writeRange;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * the element rref is an inline element containing a range of references between the IRI specified in the from
 * attribute and the one specified in the upTo attribute.
 *
 * <pre>
 *   &lt;xsd:element name="rref"&gt;
 *     &lt;xsd:complexType mixed="true"&gt;
 * 	     &lt;xsd:complexContent&gt;
 * 	  	   &lt;xsd:extension base="inlinereq"&gt;
 * 		     &lt;xsd:attributeGroup ref="range"/&gt;
 * 		   &lt;xsd:extension&gt;
 * 	     &lt;xsd:complexContent&gt;
 * 	   &lt;xsd:complexType&gt;
 *   &lt;xsd:element&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Rref extends InlineReqType implements RangeReq, ANinline {

    /**
     * XML tag element name.
     */
    public static final String ELEMENT = "rref";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    private static final ImmutableMap<String, BiConsumer<AknObject, CharArray>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, BiConsumer<AknObject, CharArray>>builder()
                .putAll(InlineReqType.ATTRIBUTES)
                .put(Range.ATTRIBUTE, biConsumerEidRef(getFieldOffset(Rref.class, "from")))
                .put(UpTo.ATTRIBUTE, biConsumerEidRef(getFieldOffset(Rref.class, "upTo")))
                .build();
    }

    private EidRef from;
    private EidRef upTo;

    /**
     * {@inheritDoc}
     */
    @Override
    public EidRef getFrom() {
        return from;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFrom(EidRef from) {
        this.from = from;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EidRef getUpTo() {
        return upTo;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUpTo(EidRef upTo) {
        this.upTo = upTo;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 4);
        writeRange(writer, this);
        super.write(writer);
        writer.writeEnd(ADDRESS, 4);
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
