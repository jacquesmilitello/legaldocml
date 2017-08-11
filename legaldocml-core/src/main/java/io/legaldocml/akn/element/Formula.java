package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.Name;
import io.legaldocml.akn.group.BasicContainers;
import io.legaldocml.akn.group.PreambleContainers;
import io.legaldocml.akn.group.PrefaceContainers;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.CharArray;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;
import java.util.function.BiConsumer;

import static io.legaldocml.akn.element.Attributes.biConsumerString;
import static io.legaldocml.akn.util.XmlWriterHelper.writeName;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * the element formula is a section of the preface or preamble that contains a formulaic expression that is
 * systematically or frequently present in a preface or a preamble and has e precise legal meaning (e.g. an enacting
 * formula). Use the refersTo attribute for the specification of the actual type of formula.
 * <p/>
 * <pre>
 *   <xsd:element name="formula">
 * 	   <xsd:complexType>
 *       <xsd:complexContent>
 *         <xsd:extension base="blocksreq">
 *           <xsd:attributeGroup ref="name"/>
 *         </xsd:extension>
 *       </xsd:complexContent>
 *     </xsd:complexType>
 *   </xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Formula extends Blocksreq implements Name, PreambleContainers, BasicContainers, PrefaceContainers, SubFlowStructureElement, PortionBodyTypeElement {

    /**
     * XML Tag element name.
     */
    public static final String ELEMENT = "formula";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    protected static final ImmutableMap<String, BiConsumer<AknObject, CharArray>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, BiConsumer<AknObject, CharArray>>builder()
                .putAll(Blocksreq.ATTRIBUTES)
                .put(Name.ATTRIBUTE, biConsumerString(getFieldOffset(Formula.class, "name")))
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
        writer.writeStart(ADDRESS, 7);
        if (writer.getVersion() >= 3) {
            writeName(writer, this);
        }
        super.write(writer);
        writer.writeEnd(ADDRESS, 7);
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
}