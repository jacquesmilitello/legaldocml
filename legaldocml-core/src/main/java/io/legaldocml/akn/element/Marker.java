package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.Name;
import io.legaldocml.akn.group.MarkerElements;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.AttributeGetterSetter;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.util.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknAttributes.NAME;
import static io.legaldocml.akn.AknElements.MARKER;
import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4String;
import static io.legaldocml.akn.util.XmlWriterHelper.writeName;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * the element marker is a generic element for a marker. It can be placed in a block instead of any of the other
 * markers. The attribute name is required and gives a name to the element.
 * <p>
 * <pre>
 *   <xsd:element name="marker">
 *     <xsd:complexType>
 *       <xsd:complexContent>
 *         <xsd:extension base="markerreq">
 *           <xsd:attributeGroup ref="name"/>
 *         <xsd:extension>
 *       <xsd:complexContent>
 *     <xsd:complexType>
 *   <xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Marker extends MarkerReq implements Name, MarkerElements {

    /**
     * Memory address.
     */
    private static final long ADDRESS_MARKER = Buffers.address(MARKER);

    private static final ImmutableMap<String, AttributeGetterSetter<AknObject>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, AttributeGetterSetter<AknObject>>builder()
                .putAll(MarkerReq.ATTRIBUTES)
                .put(NAME, attributeGetterSetter4String(NAME, getFieldOffset(Marker.class, "name")))
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
        writer.writeStart(ADDRESS_MARKER, 6);
        writeName(writer, this);
        super.write(writer);
        writer.writeEnd(ADDRESS_MARKER, 6);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return MARKER;
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