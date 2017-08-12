package io.legaldocml.akn.element;

import io.legaldocml.akn.group.ANheaderInline;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

/**
 * the element argument is an inline element within judgments for classifying the arguments in the motivation part of
 * the judgment.
 *
 * <pre>
 * 	 &lt;xsd:element name="argument" type="inline"/&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Argument extends InlineType implements ANheaderInline {

    /**
     * XML Tag element name.
     */
    public static final String ELEMENT = "argument";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 8);
        super.write(writer);
        writer.writeEnd(ADDRESS, 8);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return ELEMENT;
    }

}