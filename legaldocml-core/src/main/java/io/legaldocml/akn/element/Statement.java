package io.legaldocml.akn.element;

import io.legaldocml.akn.DocumentType;
import io.legaldocml.util.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.STATEMENT;

/**
 * Element statement is used for describing the structure and content of an official document of a body that may or may
 * not be normative in structure (e.g., statements, resolutions, etc.).
 *
 * <pre>
 *   <xsd:element name="statement" type="openStructure"/>
 *     <xsd:unique name="eId-doc">
 *       <xsd:selector xpath=".//*"/>
 *       <xsd:field xpath="@eId"/>
 *     <xsd:unique>
 *     <xsd:unique name="GUID-doc">
 *       <xsd:selector xpath=".//*"/>
 *       <xsd:field xpath="@GUID"/>
 *     <xsd:unique>
 *   <xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Statement extends OpenStructure implements DocumentType {

    /**
     * Memory address.
     */
    private static final long ADDRESS_STATEMENT = Buffers.address(STATEMENT);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_STATEMENT, 9);
        super.write(writer);
        writer.writeEnd(ADDRESS_STATEMENT, 9);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return STATEMENT;
    }


}