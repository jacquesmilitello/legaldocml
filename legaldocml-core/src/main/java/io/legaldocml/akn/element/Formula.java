package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.Name;
import io.legaldocml.akn.group.BasicContainers;
import io.legaldocml.akn.group.PreambleContainers;
import io.legaldocml.akn.group.PrefaceContainers;
import io.legaldocml.io.AttributeGetterSetter;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknAttributes.NAME;
import static io.legaldocml.akn.AknElements.FORMULA;
import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4String;
import static io.legaldocml.akn.util.XmlWriterHelper.writeName;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * the element formula is a section of the preface or preamble that contains a formulaic expression that is
 * systematically or frequently present in a preface or a preamble and has e precise legal meaning (e.g. an enacting
 * formula). Use the refersTo attribute for the specification of the actual type of formula.
 * <p>
 * <pre>
 *   <xsd:element name="formula">
 * 	   <xsd:complexType>
 *       <xsd:complexContent>
 *         <xsd:extension base="blocksreq">
 *           <xsd:attributeGroup ref="name"/>
 *         <xsd:extension>
 *       <xsd:complexContent>
 *     <xsd:complexType>
 *   <xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Formula extends Blocksreq implements Name, PreambleContainers, BasicContainers, PrefaceContainers, SubFlowStructureElement, PortionBodyTypeElement {

    /**
     * Memory address.
     */
    private static final long ADDRESS_FORMULA = Buffers.address(FORMULA);

    protected static final ImmutableMap<String, AttributeGetterSetter<AknObject>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, AttributeGetterSetter<AknObject>>builder()
                .putAll(Blocksreq.ATTRIBUTES)
                .put(NAME, attributeGetterSetter4String(NAME, getFieldOffset(Formula.class, "name")))
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
        writer.writeStart(ADDRESS_FORMULA, 7);
        if (writer.getVersion() >= 3) {
            writeName(writer, this);
        }
        super.write(writer);
        writer.writeEnd(ADDRESS_FORMULA, 7);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return FORMULA;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableMap<String, AttributeGetterSetter<AknObject>> attributes() {
        return ATTRIBUTES;
    }
}