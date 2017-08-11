package io.legaldocml.akn.element;

import io.legaldocml.akn.DocumentType;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

/**
 * Element portion is used for describing the structure and content of an independent portion of a document.
 * <p/>
 * <pre>
 *   <xsd:element name="portion" type="portionStructure"/>
 *     <xsd:unique name="eId-portion">
 *       <xsd:selector xpath=".//*"/>
 *       <xsd:field xpath="@eId"/>
 *     </xsd:unique>
 *     <xsd:unique name="GUID-portion">
 *       <xsd:selector xpath=".//*"/>
 *       <xsd:field xpath="@GUID"/>
 *     </xsd:unique>
 *   </element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Portion extends PortionStructure implements DocumentType {

    /**
     * XML Tag element name.
     */
    public static final String ELEMENT = "portion";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 7);
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

}