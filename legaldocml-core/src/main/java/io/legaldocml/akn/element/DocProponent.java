package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.group.ANtitleInline;
import io.legaldocml.akn.type.RoleRef;
import io.legaldocml.io.CharArray;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import java.io.IOException;
import java.util.function.BiConsumer;

import static io.legaldocml.akn.element.Attributes.biConsumerRoleRef;
import static io.legaldocml.akn.util.XmlWriterHelper.writeRole;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * the element docProponent is an inline element within preface to identify the string used by the document for its
 * proponent.
 * <p/>
 * <pre>
 *   <xsd:complexType mixed="true">
 *     <xsd:complexContent>
 *       <xsd:extension base="inline">
 *         <xsd:attributeGroup ref="role"/>
 *       </xsd:extension>
 *     </xsd:complexContent>
 *   </xsd:complex>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class DocProponent extends InlineType implements ANtitleInline, io.legaldocml.akn.attribute.Role {

    /**
     * XML Tag element name.
     */
    public static final String ELEMENT = "docProponent";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    private static final ImmutableMap<String, BiConsumer<AknObject, CharArray>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, BiConsumer<AknObject, CharArray>>builder()
                .putAll(InlineType.ATTRIBUTES)
                .put("as", biConsumerRoleRef(getFieldOffset(DocProponent.class, "as")))
                .build();
    }

    private RoleRef as;

    /**
     * {@inheritDoc}
     */
    @Override
    public RoleRef getAs() {
        return this.as;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAs(RoleRef as) {
        this.as = as;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 12);
        writeRole(writer, this);
        super.write(writer);
        writer.writeEnd(ADDRESS, 12);
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