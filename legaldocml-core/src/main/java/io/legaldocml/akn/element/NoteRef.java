package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.LinkReq;
import io.legaldocml.akn.attribute.Notes;
import io.legaldocml.akn.group.ANmarker;
import io.legaldocml.akn.type.EidRef;
import io.legaldocml.akn.type.PlacementType;
import io.legaldocml.akn.type.Uri;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.AttributeGetterSetter;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknAttributes.HREF;
import static io.legaldocml.akn.AknAttributes.MARKER;
import static io.legaldocml.akn.AknAttributes.PLACEMENT;
import static io.legaldocml.akn.AknAttributes.PLACEMENT_BASE;
import static io.legaldocml.akn.AknElements.NOTE_REF;
import static io.legaldocml.akn.element.Attributes.biConsumerEidRef;
import static io.legaldocml.akn.element.Attributes.biConsumerEnum;
import static io.legaldocml.akn.element.Attributes.biConsumerString;
import static io.legaldocml.akn.util.XmlWriterHelper.writeLinkReq;
import static io.legaldocml.akn.util.XmlWriterHelper.writeNotes;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * the element noteRef is a reference to a editorial note placed in the notes metadata section.
 * <p>
 * <pre>
 *   <xsd:element name="noteRef">
 * 	   <xsd:complexType>
 * 	     <xsd:complexContent>
 * 		   <xsd:extension base="markeropt">
 * 		     <xsd:attributeGroup ref="notes"/>
 * 			 <xsd:attributeGroup ref="link"/>
 * 		   <xsd:extension>
 * 		 <xsd:complexContent>
 * 	   <xsd:complexType>
 *   <xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class NoteRef extends MarkerOpt implements LinkReq, Notes, ANmarker {

    /**
     * Memory address.
     */
    private static final long ADDRESS_NOTE_REF = Buffers.address(NOTE_REF);

    private static final ImmutableMap<String, AttributeGetterSetter<AknObject>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, AttributeGetterSetter<AknObject>>builder()
                .putAll(MarkerOpt.ATTRIBUTES)
                .put(HREF, Attributes.biConsumerUri(HREF, getFieldOffset(NoteRef.class, "href")))
                .put(MARKER, biConsumerString(MARKER, getFieldOffset(NoteRef.class, "marker")))
                .put(PLACEMENT_BASE, biConsumerEidRef(PLACEMENT_BASE, getFieldOffset(NoteRef.class, "placementBase")))
                .put(PLACEMENT, biConsumerEnum(PLACEMENT, getFieldOffset(NoteRef.class, "placementType"), PlacementType.class))
                .build();
    }

    private Uri href;
    private String marker;
    private PlacementType placementType;
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
        return this.placementType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPlacement(PlacementType placement) {
        this.placementType = placement;
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
    public Uri getHref() {
        return this.href;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setHref(Uri href) {
        this.href = href;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_NOTE_REF, 7);
        writeLinkReq(writer, this);
        writeNotes(writer, this);
        super.write(writer);
        writer.writeEnd(ADDRESS_NOTE_REF, 7);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return NOTE_REF;
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
