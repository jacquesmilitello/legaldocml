package io.legaldocml.akn.element;

import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.AMENDMENT_LIST;

/**
 * Element amendmentList is used for describing the structure and content of a collection of amendments.
 *
 * <pre>
 *   <xsd:element name="amendmentList" type="collectionStructure">
 * 	   <xsd:unique name="eId-amendmentList">
 *       <xsd:selector xpath=".//*"/>
 *       <xsd:field xpath="@eId"/>
 *     <xsd:unique>
 *     <xsd:unique name="GUID-amendmentList">
 *       <xsd:selector xpath=".//*"/>
 *       <xsd:field xpath="@GUID"/>
 *     <xsd:unique>
 *   <xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class AmendmentList extends CollectionStructure implements CollectionDocs {

    /**
     * Memory address.
     */
    private static final long ADDRESS_AMENDMENT_LIST = Buffers.address(AMENDMENT_LIST);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_AMENDMENT_LIST, 13);
        super.write(writer);
        writer.writeEnd(ADDRESS_AMENDMENT_LIST, 13);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return AMENDMENT_LIST;
    }

}