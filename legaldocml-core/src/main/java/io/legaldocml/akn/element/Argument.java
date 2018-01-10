package io.legaldocml.akn.element;

import io.legaldocml.akn.group.ANheaderInline;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.util.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.ARGUMENT;

/**
 * the element argument is an inline element within judgments for classifying the arguments in the motivation part of
 * the judgment.
 *
 * <pre>
 * 	 <xsd:element name="argument" type="inline"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Argument extends InlineType implements ANheaderInline {

    /**
     * Memory address.
     */
    private static final long ADDRESS_ARGUMENT = Buffers.address(ARGUMENT);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_ARGUMENT, 8);
        super.write(writer);
        writer.writeEnd(ADDRESS_ARGUMENT, 8);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return ARGUMENT;
    }

}