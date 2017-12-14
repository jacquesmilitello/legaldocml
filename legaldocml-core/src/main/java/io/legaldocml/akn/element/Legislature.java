package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.ValueOpt;
import io.legaldocml.akn.group.ANtitleInline;
import io.legaldocml.io.AttributeGetterSetter;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknAttributes.VALUE;
import static io.legaldocml.akn.AknElements.LEGISLATURE;
import static io.legaldocml.akn.element.Attributes.biConsumerString;
import static io.legaldocml.akn.util.XmlWriterHelper.writeOptValue;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * The element legislature is an inline element within preface to identify the string used by the document for the
 * legislature relative to the document. Use #refersTo to a TLCEvent to refer to the event of the specific legislature.
 *
 * <pre>
 *   <xsd:element name="legislature">
 * 	   <xsd:complexType mixed="true">
 * 	     <xsd:complexContent>
 * 		   <xsd:extension base="inline">
 * 		     <xsd:attributeGroup ref="optvalue"/>
 * 		   </xsd:extension>
 * 		 </xsd:complexContent>
 * 	   </xsd:complexType>
 *   </xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Legislature extends InlineType implements ValueOpt, ANtitleInline {

    /**
     * Memory address.
     */
    private static final long ADDRESS_LEGISLATURE = Buffers.address(LEGISLATURE);

    private static final ImmutableMap<String, AttributeGetterSetter<AknObject>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, AttributeGetterSetter<AknObject>>builder()
                .putAll(InlineType.ATTRIBUTES)
                .put(VALUE, biConsumerString(VALUE, getFieldOffset(Legislature.class, "value")))
                .build();
    }

    private String value;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getValue() {
        return this.value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_LEGISLATURE, 11);
        writeOptValue(writer, this);
        super.write(writer);
        writer.writeEnd(ADDRESS_LEGISLATURE, 11);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return LEGISLATURE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableMap<String, AttributeGetterSetter<AknObject>> attributes() {
        return ATTRIBUTES;
    }
}