package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknAttributes;
import io.legaldocml.akn.attribute.Notes;
import io.legaldocml.akn.group.ANsubFlow;
import io.legaldocml.akn.type.EidRef;
import io.legaldocml.akn.type.PlacementType;
import io.legaldocml.io.CharArray;
import io.legaldocml.io.Externalizable;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import java.io.IOException;
import java.util.function.BiConsumer;

import static io.legaldocml.akn.element.Attributes.biConsumerEidRef;
import static io.legaldocml.akn.element.Attributes.biConsumerEnum;
import static io.legaldocml.akn.element.Attributes.biConsumerString;
import static io.legaldocml.akn.util.XmlWriterHelper.writeNotes;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * the element authorialNote is a subFlow element containing an authorial (non-editorial) note in the main flow of the
 * text.
 *
 * <pre>
 *   <xsd:element name="authorialNote">
 * 	   <xsd:complexType>
 *       <xsd:complexContent>
 *         <xsd:extension base="subFlowStructure">
 *           <xsd:attributeGroup ref="notes"/>
 *         <xsd:extension>
 *       <xsd:complexContent>
 *     <xsd:complexType>
 * 	 <xsd:element>
 * </pre>
 */
public final class AuthorialNote extends SubFlowStructure implements Notes, ANsubFlow {

    /**
     * XML tag element name.
     */
    public static final String ELEMENT = "authorialNote";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);


    private static final ImmutableMap<String, BiConsumer<Externalizable, CharArray>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, BiConsumer<Externalizable, CharArray>>builder()
                .putAll(SubFlowStructure.ATTRIBUTES)
                .put(AknAttributes.MARKER, biConsumerString(getFieldOffset(AuthorialNote.class, "marker")))
                .put(AknAttributes.PLACEMENT_BASE, biConsumerEidRef(getFieldOffset(AuthorialNote.class, "placementBase")))
                .put(AknAttributes.PLACEMENT, biConsumerEnum(getFieldOffset(AuthorialNote.class, "placement"), PlacementType.class))
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
        writer.writeStart(ADDRESS, 13);
        writeNotes(writer, this);
        super.write(writer);
        writer.writeEnd(ADDRESS, 13);
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
    public ImmutableMap<String, BiConsumer<Externalizable, CharArray>> attributes() {
        return ATTRIBUTES;
    }
}