package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.ScopeModType;
import io.legaldocml.akn.type.ScopeMods;
import io.legaldocml.io.AttributeGetterSetter;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknAttributes.TYPE;
import static io.legaldocml.akn.AknElements.DOMAIN;
import static io.legaldocml.akn.AknElements.SCOPE_MOD;
import static io.legaldocml.akn.element.Attributes.biConsumerEnum;
import static io.legaldocml.akn.util.XmlWriterHelper.writeScopeModType;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * The element scopeMod is a metadata element specifying an (active or passive) modification in scope for the document.
 * <p>
 * <pre>
 *   <xsd:element name="scopeMod">
 * 	   <xsd:complexType>
 * 	     <xsd:complexContent>
 * 		   <xsd:extension base="modificationType">
 * 		     <xsd:sequence>
 * 			   <xsd:element ref="domain" minOccurs="0" maxOccurs="1"/>
 * 			 <xsd:sequence>
 * 			 <xsd:attributeGroup ref="scopeModType"/>
 * 		   <xsd:extension>
 * 		 <xsd:complexContent>
 * 	   <xsd:complexType>
 *   <xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class ScopeMod extends ModificationType implements ScopeModType, AmendmentsElement {

    /**
     * Memory address.
     */
    private static final long ADDRESS_SCOPE_MOD = Buffers.address(SCOPE_MOD);

    protected static final ImmutableMap<String, AttributeGetterSetter<AknObject>> ATTRIBUTES;

    static {

        ATTRIBUTES = ImmutableMap.<String, AttributeGetterSetter<AknObject>>builder()
                .putAll(ModificationType.ATTRIBUTES)
                .put(TYPE, biConsumerEnum(TYPE, getFieldOffset(ScopeMod.class, "type"), ScopeMods.class))
                .build();
    }


    private ScopeMods type;
    private Domain domain;

    /**
     * {@inheritDoc}
     */
    @Override
    public ScopeMods getType() {
        return this.type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setType(ScopeMods type) {
        this.type = type;
    }

    public Domain getDomain() {
        return this.domain;
    }

    public void setDomain(Domain domain) {
        this.domain = domain;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_SCOPE_MOD, 10);
        writeScopeModType(writer, this);
        super.write(writer);
        writer.writeEnd(ADDRESS_SCOPE_MOD, 10);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {
        super.read(reader);
        if (reader.getQName().equalsLocalName(DOMAIN)) {
            this.domain = new Domain();
            this.domain.read(reader);
            reader.nextStartOrEndElement();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return SCOPE_MOD;
    }

    @Override
    public ImmutableMap<String, AttributeGetterSetter<AknObject>> attributes() {
        return ATTRIBUTES;
    }
}