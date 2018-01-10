package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.Notes;
import io.legaldocml.akn.type.EidRef;
import io.legaldocml.akn.type.PlacementType;
import io.legaldocml.io.AttributeGetterSetter;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.util.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknAttributes.MARKER;
import static io.legaldocml.akn.AknAttributes.PLACEMENT;
import static io.legaldocml.akn.AknAttributes.PLACEMENT_BASE;
import static io.legaldocml.akn.AknElements.NOTE;
import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4EidRef;
import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4Enum;
import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4String;
import static io.legaldocml.akn.util.XmlWriterHelper.writeNotes;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * The element note is a metadata element containing the text of the footnote and endnote specified.
 * <p>
 * <pre>
 *   <xsd:element name="note">
 * 	   <xsd:complexType>
 *       <xsd:complexContent>
 *         <xsd:extension base="subFlowStructure">
 *           <xsd:attributeGroup ref="notes"/>
 *         <xsd:extension>
 *       <xsd:complexContent>
 *     <xsd:complexType>
 * 	 <xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Note extends SubFlowStructure implements Notes {

    /**
     * Memory address.
     */
    private static final long ADDRESS_NOTE = Buffers.address(NOTE);


    private static final ImmutableMap<String, AttributeGetterSetter<AknObject>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, AttributeGetterSetter<AknObject>>builder()
                .putAll(SubFlowStructure.ATTRIBUTES)
                .put(MARKER, attributeGetterSetter4String(MARKER, getFieldOffset(Note.class, "marker")))
                .put(PLACEMENT_BASE, attributeGetterSetter4EidRef(PLACEMENT_BASE, getFieldOffset(Note.class, "placementBase")))
                .put(PLACEMENT, attributeGetterSetter4Enum(PLACEMENT, getFieldOffset(Note.class, "placement"), PlacementType.class))
                .build();
    }

    private String marker;
    private PlacementType placement;
    private EidRef placementBase;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getMarker() {
        return this.marker;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMarker(String marker) {
        this.marker = marker;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PlacementType getPlacement() {
        return this.placement;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPlacement(PlacementType placement) {
        this.placement = placement;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EidRef getPlacementBase() {
        return this.placementBase;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPlacementBase(EidRef placementBase) {
        this.placementBase = placementBase;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_NOTE, 4);
        writeNotes(writer, this);
        super.write(writer);
        writer.writeEnd(ADDRESS_NOTE, 4);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return NOTE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableMap<String, AttributeGetterSetter<AknObject>> attributes() {
        return ATTRIBUTES;
    }
}