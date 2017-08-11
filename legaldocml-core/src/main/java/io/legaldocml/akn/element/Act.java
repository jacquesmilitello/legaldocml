package io.legaldocml.akn.element;

import io.legaldocml.akn.DocumentType;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

/**
 * Element act is used for describing the structure and content of an act.
 * <p/>
 * <pre>
 *   <xsd:element name="act" type="hierarchicalStructure">
 *     <xsd:unique name="eId-act">
 *       <xsd:selector xpath=".//*"/>
 *       <xsd:field xpath="@eId"/>
 *     </xsd:unique>
 *     <xsd:unique name="GUID-act">
 *       <xsd:selector xpath=".//*"/>
 *       <xsd:field xpath="@GUID"/>
 *     </xsd:unique>
 *   </xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Act extends HierarchicalStructure implements DocumentType {

    /**
     * XML element name.
     */
    public static final String ELEMENT = "act";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 3);
        super.write(writer);
        writer.writeEnd(ADDRESS, 3);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return ELEMENT;
    }

}