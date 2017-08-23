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
 * <pre>
 *   <xsd:element name="tr">
 * 	   <xsd:complexType>
 * 	     <xsd:choice minOccurs="1" maxOccurs="unbounded">
 * 		   <xsd:element ref="th"/>
 * 		   <xsd:element ref="td"/>
 * 		 <xsd:choice>
 * 		 <xsd:attributeGroup ref="coreopt"/>
 * 	   <xsd:complexType>
 *   <xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Tr extends CoreOptImpl implements SubFlowStructureElement {

    /**
     * XML tag element name.
     */
    public static final String ELEMENT = "tr";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    private static final ImmutableMap<String, Supplier<TrElement>> ELEMS;

    static {
        ELEMS = ImmutableMap.<String, Supplier<TrElement>>builder()
                .put(Th.ELEMENT, Th::new)
                .put(Td.ELEMENT, Td::new)
                .build();
    }

    // Mandatory (min 1)
    private final AknList<TrElement> trs = new AknList<TrElement>(new TrElement[6]);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 2);
        super.write(writer);
        this.trs.write(writer);
        writer.writeEnd(ADDRESS, 2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {
        super.read(reader);
        XmlReaderHelper.read(reader, this.trs, ELEMS);
    }

//    /** {@inheritDoc} */
//    @Override
//    public void readExternal(Input in) throws IOException {
//        super.readExternal(in);
//        if (Tr.ELEMENT_TR == in.getLocalName()) {
//            final String localName = in.getLocalName();
//            TrElement element;
//            int eventType = in.next();
//            while (true) {
//                if (eventType == InputConstants.START_ELEMENT) {
//                    final Instantiator<? extends TrElement> instantiator = MAP.get(in.getLocalName());
//                    if (instantiator == null) {
//                    	throw new UnsupportedElementException(localName,MAP.keySet(), in);
//                    }
//                    element = instantiator.instantiate();
//                    element.readExternal(in);
//                    _trs.add(element);
//                } else if (in.getEventType() == InputConstants.END_ELEMENT && localName == in.getLocalName()) {
//                    return;
//                }
//                eventType = in.next();
//            }
//        }
//    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return ELEMENT;
    }

}