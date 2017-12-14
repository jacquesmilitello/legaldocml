package io.legaldocml.akn.element;


import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.RangeOpt;
import io.legaldocml.akn.attribute.RefersOpt;
import io.legaldocml.akn.attribute.ShowOpt;
import io.legaldocml.akn.type.EidRef;
import io.legaldocml.akn.type.ListReferenceRef;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.AttributeGetterSetter;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknAttributes.FROM;
import static io.legaldocml.akn.AknAttributes.REFERS_TO;
import static io.legaldocml.akn.AknAttributes.SHORT_FORM;
import static io.legaldocml.akn.AknAttributes.SHOW_AS;
import static io.legaldocml.akn.AknAttributes.UP_TO;
import static io.legaldocml.akn.AknElements.FRBR_PORTION;
import static io.legaldocml.akn.element.Attributes.biConsumerEidRef;
import static io.legaldocml.akn.element.Attributes.biConsumerListReferenceRef;
import static io.legaldocml.akn.element.Attributes.biConsumerString;
import static io.legaldocml.akn.util.XmlWriterHelper.writeRange;
import static io.legaldocml.akn.util.XmlWriterHelper.writeRefers;
import static io.legaldocml.akn.util.XmlWriterHelper.writeShow;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * The element FRBRportion is the metadata property containing the eId of the portion contained in a manifestation-level
 * portion. If the portion contains an interval of elements, the range attributes specifies the first and last one.
 * <p>
 * <pre>
 *   <xsd:element name="FRBRportion">
 *     <xsd:complexType>
 *       <xsd:complexContent>
 *         <xsd:extension base="metaopt">
 *           <xsd:attributeGroup ref="refers"/>
 *           <xsd:attributeGroup ref="showopt"/>
 *           <xsd:attributeGroup ref="rangeOpt"/>
 *         </xsd:extension>
 *       </xsd:complexContent>
 *     </xsd:complexType>
 *   </xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class FRBRportion extends MetaOpt implements RefersOpt, ShowOpt, RangeOpt {

    /**
     * Memory address.
     */
    private static final long ADDRESS_FRBR_PORTION = Buffers.address(FRBR_PORTION);

    private static final ImmutableMap<String, AttributeGetterSetter<AknObject>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, AttributeGetterSetter<AknObject>>builder()
                .putAll(MetaOpt.ATTRIBUTES)
                .put(REFERS_TO, biConsumerListReferenceRef(REFERS_TO, getFieldOffset(FRBRportion.class, "refersTo")))
                .put(SHOW_AS, biConsumerString(SHOW_AS, getFieldOffset(FRBRportion.class, "showAs")))
                .put(SHORT_FORM, biConsumerString(SHORT_FORM, getFieldOffset(FRBRportion.class, "shortForm")))
                .put(UP_TO, biConsumerEidRef(UP_TO, getFieldOffset(FRBRportion.class, "upTo")))
                .put(FROM, biConsumerEidRef(FROM, getFieldOffset(FRBRportion.class, "from")))
                .build();
    }

    private ListReferenceRef refersTo;
    private String showAs;
    private String shortForm;
    private EidRef from;
    private EidRef upTo;

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
    public EidRef getFrom() {
        return this.from;
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
    public void setFrom(EidRef eidRef) {
        this.from = eidRef;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EidRef getUpTo() {
        return this.upTo;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUpTo(EidRef upTo) {
        this.upTo = upTo;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_FRBR_PORTION, 11);
        writeRefers(writer, this);
        writeShow(writer, this);
        writeRange(writer, this);
        super.write(writer);
        writer.writeEnd(ADDRESS_FRBR_PORTION, 11);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return FRBR_PORTION;
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