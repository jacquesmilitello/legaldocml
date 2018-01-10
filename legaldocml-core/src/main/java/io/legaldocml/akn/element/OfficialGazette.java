package io.legaldocml.akn.element;

import io.legaldocml.akn.DocumentType;
import io.legaldocml.util.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.OFFICIAL_GAZETTE;

/**
 * Element officialGazette is used for describing the structure and content of an issue of an official gazette.
 *
 * <pre>
 *   <xsd:element name="officialGazette" type="collectionStructure">
 * 	   <xsd:unique name="eId-officialGazette">
 *       <xsd:selector xpath=".//*"/>
 *       <xsd:field xpath="@eId"/>
 *     <xsd:unique>
 *     <xsd:unique name="GUID-officialGazette">
 *       <xsd:selector xpath=".//*"/>
 *       <xsd:field xpath="@GUID"/>
 *     <xsd:unique>
 * 	 <xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class OfficialGazette extends CollectionStructure implements DocumentType {

    /**
     * Memory address.
     */
    private static final long ADDRESS_OFFICIAL_GAZETTE = Buffers.address(OFFICIAL_GAZETTE);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_OFFICIAL_GAZETTE, 15);
        super.write(writer);
        writer.writeEnd(ADDRESS_OFFICIAL_GAZETTE, 15);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return OFFICIAL_GAZETTE;
    }

}