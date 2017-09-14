package io.legaldocml.akn.element;


import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknAttributes;
import io.legaldocml.akn.attribute.Name;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.CharArray;
import io.legaldocml.io.Externalizable;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import java.io.IOException;
import java.util.function.BiConsumer;

import static io.legaldocml.akn.AknElements.FRBR_ALIAS;
import static io.legaldocml.akn.element.Attributes.biConsumerString;
import static io.legaldocml.akn.util.XmlWriterHelper.writeName;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * The element FRBRalias is the metadata property containing additional well-known names of the document in the
 * respective level of the FRBR hierarchy
 *
 * <pre>
 *   <xsd:element name="FRBRalias" type="valueType"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class FRBRalias extends ValueType implements Name {

    /**
     * Memory address.
     */
    private static final long ADDRESS_FRBR_ALIAS = Buffers.address(FRBR_ALIAS);

    private static final ImmutableMap<String, BiConsumer<Externalizable, CharArray>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, BiConsumer<Externalizable, CharArray>>builder()
                .putAll(ValueType.ATTRIBUTES)
                .put(AknAttributes.NAME, biConsumerString(getFieldOffset(FRBRalias.class, "name")))
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
        writer.writeStart(ADDRESS_FRBR_ALIAS, 9);
        if (writer.getVersion() >= 3) {
            writeName(writer, this);
        }
        super.write(writer);
        writer.writeEnd(ADDRESS_FRBR_ALIAS, 9);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return FRBR_ALIAS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableMap<String, BiConsumer<Externalizable, CharArray>> attributes() {
        return ATTRIBUTES;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(AknVisitor visitor) {
        visitor.visit(this);
    }

}