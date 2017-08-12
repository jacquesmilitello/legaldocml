package io.legaldocml.akn.element;

import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

/**
 * Element amendmentList is used for describing the structure and content of a collection of amendments.
 *
 * <pre>
 *   &lt;xsd:element name="amendmentList" type="collectionStructure"&gt;
 * 	   &lt;xsd:unique name="eId-amendmentList"&gt;
 *       &lt;xsd:selector xpath=".//*"/&gt;
 *       &lt;xsd:field xpath="@eId"/&gt;
 *     &lt;xsd:unique&gt;
 *     &lt;xsd:unique name="GUID-amendmentList"&gt;
 *       &lt;xsd:selector xpath=".//*"/&gt;
 *       &lt;xsd:field xpath="@GUID"/&gt;
 *     &lt;xsd:unique&gt;
 *   &lt;xsd:element&gt;
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