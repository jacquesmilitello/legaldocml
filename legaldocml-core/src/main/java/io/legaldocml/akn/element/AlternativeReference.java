package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknAttributes;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.For;
import io.legaldocml.akn.attribute.RefersOpt;
import io.legaldocml.akn.attribute.ShowOpt;
import io.legaldocml.akn.type.EidRef;
import io.legaldocml.akn.type.ListReferenceRef;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.CharArray;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import java.io.IOException;
import java.util.function.BiConsumer;

import static io.legaldocml.akn.element.Attributes.biConsumerListReferenceRef;
import static io.legaldocml.akn.element.Attributes.biConsumerString;
import static io.legaldocml.akn.util.XmlWriterHelper.writeFor;
import static io.legaldocml.akn.util.XmlWriterHelper.writeRefers;
import static io.legaldocml.akn.util.XmlWriterHelper.writeShow;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * The element alternativeReference contains an alternative reference (e.g., using a different namespace) for a legal
 * reference.
 *
 * <pre>
 *   <xsd:element name="alternativeReference" type="anyOtherType">
 * 	   <xsd:complexType>
 *       <xsd:complexContent>
 *         <xsd:extension base="anyOtherType">
 *           <xsd:attribute name="for" type="eIdRef"/>
 *           <xsd:attributeGroup ref="refers"/>
 *           <xsd:attributeGroup ref="showopt"/>
 *         <xsd:extension>
 *       <xsd:complexContent>
 *     <xsd:complexType>
 *   <xsd:element>
 * <pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class AlternativeReference extends AnyOtherType implements RefersOpt, ShowOpt, For, OtherReferencesElement {

    /**
     * XML tag element name.
     */
    public static final String ELEMENT = "alternativeReference";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    private static final ImmutableMap<String, BiConsumer<AknObject, CharArray>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, BiConsumer<AknObject, CharArray>>builder()
                .putAll(AnyOtherType.ATTRIBUTES)
                .put(RefersOpt.ATTRIBUTE, biConsumerListReferenceRef(getFieldOffset(AlternativeReference.class, "refersTo")))
                .put(ShowOpt.ATTRIBUTE_SHOW_AS, biConsumerString(getFieldOffset(AlternativeReference.class, "showAs")))
                .put(ShowOpt.ATTRIBUTE_SHORT_FORM, biConsumerString(getFieldOffset(AlternativeReference.class, "shortForm")))
                .put(AknAttributes.FOR, biConsumerString(getFieldOffset(AlternativeReference.class, "for_")))
                .build();
    }

    private ListReferenceRef refersTo;
    private String showAs;
    private String shortForm;
    private EidRef for_;

    public EidRef getFor() {
        return this.for_;
    }

    public void setFor(EidRef for_) {
        this.for_ = for_;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRefersTo(ListReferenceRef refersTo) {
        this.refersTo = refersTo;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setShowAs(String showAs) {
        this.showAs = showAs;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getShortForm() {
        return this.shortForm;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setShortForm(String shortForm) {
        this.shortForm = shortForm;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ListReferenceRef getRefersTo() {
        return this.refersTo;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getShowAs() {
        return this.showAs;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 20);
        writeRefers(writer, this);
        writeShow(writer, this);
        writeFor(writer, this);
        super.write(writer);
        writer.writeEnd(ADDRESS, 20);
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(AknVisitor visitor) {
        if (visitor.visitEnter(this)) {
            super.accept(visitor);
            visitor.visitLeave(this);
        }
    }

}