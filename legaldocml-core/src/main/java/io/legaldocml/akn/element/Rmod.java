package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.RangeReq;
import io.legaldocml.akn.group.ANinline;
import io.legaldocml.akn.type.EidRef;
import io.legaldocml.io.AttributeGetterSetter;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.util.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknAttributes.FROM;
import static io.legaldocml.akn.AknAttributes.UP_TO;
import static io.legaldocml.akn.AknElements.RMOD;
import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4EidRef;
import static io.legaldocml.akn.util.XmlWriterHelper.writeRange;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * the element rmod is an inline element containing the specification of a range of modifications on another document.
 *
 * <pre>
 *   <xsd:element name="rmod">
 *     <xsd:complexType mixed="true">
 *       <xsd:complexContent>
 *         <xsd:extension base="modType">
 *           <xsd:attributeGroup ref="range"/>
 *         </xsd:extension>
 *       </xsd:complexContent>
 *     </xsd:complexType>
 *   </xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Rmod extends ModType implements RangeReq, ANinline {

    /**
     * Memory address.
     */
    private static final long ADDRESS_RMOD = Buffers.address(RMOD);

    protected static final ImmutableMap<String, AttributeGetterSetter<AknObject>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, AttributeGetterSetter<AknObject>>builder()
                .putAll(ModType.ATTRIBUTES)
                .put(UP_TO, attributeGetterSetter4EidRef(UP_TO, getFieldOffset(Rmod.class, "upTo")))
                .put(FROM, attributeGetterSetter4EidRef(FROM, getFieldOffset(Rmod.class, "from")))
                .build();

    }

    private EidRef from;
    private EidRef upTo;

    /**
     * {@inheritDoc}
     */
    @Override
    public EidRef getFrom() {
        return this.from;
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
        return this.upTo;
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
        writer.writeStart(ADDRESS_RMOD, 4);
        writeRange(writer, this);
        super.write(writer);
        writer.writeEnd(ADDRESS_RMOD, 4);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return RMOD;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableMap<String, AttributeGetterSetter<AknObject>> attributes() {
        return ATTRIBUTES;
    }
}