package io.legaldocml.akn.element;

import io.legaldocml.akn.group.ANsemanticInline;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.PROCESS;

/**
 * The element process is an inline element to identify a text fragment introducing or referring to a process in the
 * ontology.
 *
 * <pre>
 *   <xsd:element name="process" type="inlinereqreq"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Process extends InlineReqReqType implements ANsemanticInline {

    /**
     * Memory address.
     */
    private static final long ADDRESS_PROCESS = Buffers.address(PROCESS);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_PROCESS, 7);
        super.write(writer);
        writer.writeEnd(ADDRESS_PROCESS, 7);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return PROCESS;
    }

}