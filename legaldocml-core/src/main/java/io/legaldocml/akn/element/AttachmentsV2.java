package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.util.AknList;
import io.legaldocml.akn.util.XmlReaderHelper;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;
import java.util.function.Supplier;

/**
 *
 * <pre>
 *  &lt;xsd:element name="attachments"&gt;
 *  	&lt;xsd:complexType&gt;
 *      	&lt;xsd:sequence&gt;
 *          	&lt;xsd:element ref="componentRef" minOccurs="1" maxOccurs="unbounded"/&gt;
 *          &lt;xsd:sequence&gt;
 *      	&lt;xsd:attributeGroup ref="coreopt"/&gt;
 * 		&lt;xsd:complexType&gt;
 * 	&lt;xsd:element&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class AttachmentsV2 extends CoreOptImpl implements Attachments{

    /**
     * XML tag element name.
     */
    public static final String ELEMENT = "attachments";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

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
        writer.writeStart(ADDRESS, 11);
        this.elements.write(writer);
        writer.writeEnd(ADDRESS, 11);
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