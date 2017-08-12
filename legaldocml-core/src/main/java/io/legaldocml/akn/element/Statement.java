package io.legaldocml.akn.element;

import io.legaldocml.akn.DocumentType;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

/**
 * Element statement is used for describing the structure and content of an official document of a body that may or may
 * not be normative in structure (e.g., statements, resolutions, etc.).
 *
 * <pre>
 *   &lt;xsd:element name="statement" type="openStructure"/&gt;
 *     &lt;xsd:unique name="eId-doc"&gt;
 *       &lt;xsd:selector xpath=".//*"/&gt;
 *       &lt;xsd:field xpath="@eId"/&gt;
 *     &lt;xsd:unique&gt;
 *     &lt;xsd:unique name="GUID-doc"&gt;
 *       &lt;xsd:selector xpath=".//*"/&gt;
 *       &lt;xsd:field xpath="@GUID"/&gt;
 *     &lt;xsd:unique&gt;
 *   &lt;xsd:element&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Statement extends OpenStructure implements DocumentType {

    /**
     * XML Tag element name.
     */
    public static final String ELEMENT = "statement";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 9);
        super.write(writer);
        writer.writeEnd(ADDRESS, 9);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return ELEMENT;
    }


}