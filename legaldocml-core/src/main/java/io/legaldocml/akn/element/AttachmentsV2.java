package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.util.AknList;
import io.legaldocml.akn.util.XmlReaderHelper;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;
import java.util.function.Supplier;

import static io.legaldocml.akn.AknElements.ATTACHMENTS;

/**
 *
 * <pre>
 *  <xsd:element name="attachments">
 *  	<xsd:complexType>
 *      	<xsd:sequence>
 *          	<xsd:element ref="componentRef" minOccurs="1" maxOccurs="unbounded"/>
 *          <xsd:sequence>
 *      	<xsd:attributeGroup ref="coreopt"/>
 * 		<xsd:complexType>
 * 	<xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class AttachmentsV2 extends CoreOptImpl implements Attachments{

    /**
     * Memory address.
     */
    private static final long ADDRESS_ATTACHMENTS = Buffers.address(ATTACHMENTS);

    private static final ImmutableMap<String, Supplier<ComponentRef>> ELEMS;

    static {
        ELEMS = ImmutableMap.of(ComponentRef.ELEMENT, ComponentRef::new);
    }


    // Mandatory (min 1).
    private final AknList<ComponentRef> elements = new AknList<ComponentRef>(new ComponentRef[4]);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_ATTACHMENTS, 11);
        this.elements.write(writer);
        writer.writeEnd(ADDRESS_ATTACHMENTS, 11);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {
        super.read(reader);
        XmlReaderHelper.read(reader, this.elements, ELEMS);

    }

}