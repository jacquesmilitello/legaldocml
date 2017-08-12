package io.legaldocml.akn.element;

import io.legaldocml.akn.DocumentType;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

/**
 * Element act is used for describing the structure and content of an act.
 *
 * <pre>
 *   &lt;xsd:element name="act" type="hierarchicalStructure"&gt;
 *     &lt;xsd:unique name="eId-act"&gt;
 *       &lt;xsd:selector xpath=".//*"/&gt;
 *       &lt;xsd:field xpath="@eId"/&gt;
 *     &lt;xsd:unique&gt;
 *     &lt;xsd:unique name="GUID-act"&gt;
 *       &lt;xsd:selector xpath=".//*"/&gt;
 *       &lt;xsd:field xpath="@GUID"/&gt;
 *     &lt;xsd:unique&gt;
 *   &lt;xsd:element&gt;
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