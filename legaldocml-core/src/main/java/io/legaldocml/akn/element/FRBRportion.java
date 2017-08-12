package io.legaldocml.akn.element;


import io.legaldocml.akn.attribute.RangeOpt;
import io.legaldocml.akn.attribute.RefersOpt;
import io.legaldocml.akn.attribute.ShowOpt;
import io.legaldocml.akn.type.EidRef;
import io.legaldocml.akn.type.ListReferenceRef;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

/**
 * The element FRBRportion is the metadata property containing the eId of the portion contained in a manifestation-level
 * portion. If the portion contains an interval of elements, the range attributes specifies the first and last one.
 *
 * <pre>
 *   &lt;xsd:element name="FRBRportion"&gt;
 *     &lt;xsd:complexType&gt;
 *       &lt;xsd:complexContent&gt;
 *         &lt;xsd:extension base="metaopt"&gt;
 *           &lt;xsd:attributeGroup ref="refers"/&gt;
 *           &lt;xsd:attributeGroup ref="showopt"/&gt;
 *           &lt;xsd:attributeGroup ref="rangeOpt"/&gt;
 *         &lt;xsd:extension&gt;
 *       &lt;xsd:complexContent&gt;
 *     &lt;xsd:complexType&gt;
 *   &lt;xsd:element&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class FRBRportion extends MetaOpt implements RefersOpt, ShowOpt, RangeOpt {

    /**
     * XML Tag element name.
     */
    public static final String ELEMENT = "FRBRportion";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    private ListReferenceRef refersTo;
    private String showAs;
    private String shortForm;
    private EidRef from;
    private EidRef upTo;

    @Override
    public ListReferenceRef getRefersTo() {
        return this.refersTo;
    }

    @Override
    public String getShowAs() {
        return this.showAs;
    }

    @Override
    public void setRefersTo(ListReferenceRef refersTo) {
        this.refersTo = refersTo;
    }

    @Override
    public void setShowAs(String showAs) {
        this.showAs = showAs;
    }

    @Override
    public String getShortForm() {
        return this.shortForm;
    }

    @Override
    public EidRef getFrom() {
        return this.from;
    }

    @Override
    public void setShortForm(String shortForm) {
        this.shortForm = shortForm;
    }

    @Override
    public void setFrom(EidRef eidRef) {
        this.from = from;
    }

    @Override
    public EidRef getUpTo() {
        return this.upTo;
    }

    @Override
    public void setUpTo(EidRef upTo) {
        this.upTo = upTo;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 11);
        super.write(writer);
        writer.writeEnd(ADDRESS, 11);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return ELEMENT;
    }
}