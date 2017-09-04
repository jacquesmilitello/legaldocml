package io.legaldocml.akn.element;

import io.legaldocml.akn.DocumentType;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.ACT;

/**
 * Element act is used for describing the structure and content of an act.
 *
 * <pre>
 *   <xsd:element name="act" type="hierarchicalStructure">
 *     <xsd:unique name="eId-act">
 *       <xsd:selector xpath=".//*"/>
 *       <xsd:field xpath="@eId"/>
 *     <xsd:unique>
 *     <xsd:unique name="GUID-act">
 *       <xsd:selector xpath=".//*"/>
 *       <xsd:field xpath="@GUID"/>
 *     <xsd:unique>
 *   <xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Act extends HierarchicalStructure implements DocumentType {

    /**
     * Memory address.
     */
    private static final long ADDRESS_ACT = Buffers.address(ACT);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_ACT, 3);
        super.write(writer);
        writer.writeEnd(ADDRESS_ACT, 3);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return ACT;
    }

}