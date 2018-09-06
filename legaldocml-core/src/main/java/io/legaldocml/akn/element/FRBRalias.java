package io.legaldocml.akn.element;


import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.Name;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.AttributeGetterSetter;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.util.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknAttributes.NAME;
import static io.legaldocml.akn.AknElements.FRBR_ALIAS;
import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4String;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * The element FRBRalias is the metadata property containing additional well-known names of the document in the
 * respective level of the FRBR hierarchy
 * <p>
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

    private static final ImmutableMap<String, AttributeGetterSetter<AknObject>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, AttributeGetterSetter<AknObject>>builder()
                .putAll(ValueType.ATTRIBUTES)
                .put(NAME, attributeGetterSetter4String(NAME, getFieldOffset(FRBRalias.class, "name")))
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
            Name.super.write(writer);
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
    public ImmutableMap<String, AttributeGetterSetter<AknObject>> attributes() {
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