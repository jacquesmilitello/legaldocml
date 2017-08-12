package io.legaldocml.akn.element;

import io.legaldocml.akn.group.ANsemanticInline;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

/**
 * The element process is an inline element to identify a text fragment introducing or referring to a process in the
 * ontology.
 *
 * <pre>
 *   &lt;xsd:element name="process" type="inlinereqreq"/&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Process extends InlineReqReqType implements ANsemanticInline {

    /**
     * XML tag element name.
     */
    public static final String ELEMENT = "process";

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