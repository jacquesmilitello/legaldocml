package io.legaldocml.akn.element;

import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

/**
 * Element amendmentList is used for describing the structure and content of a collection of amendments.
 * <p/>
 * <pre>
 *   <xsd:element name="amendmentList" type="collectionStructure">
 * 	   <xsd:unique name="eId-amendmentList">
 *       <xsd:selector xpath=".//*"/>
 *       <xsd:field xpath="@eId"/>
 *     </xsd:unique>
 *     <xsd:unique name="GUID-amendmentList">
 *       <xsd:selector xpath=".//*"/>
 *       <xsd:field xpath="@GUID"/>
 *     </xsd:unique>
 *   </xsd:element>
 * <pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class AmendmentList extends CollectionStructure implements CollectionDocs {

    /**
     * XML element name.
     */
    public static final String ELEMENT = "amendmentList";

    private static final long ADDRESS = Buffers.address(ELEMENT);

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
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 13);
        super.write(writer);
        writer.writeEnd(ADDRESS, 13);
    }

}