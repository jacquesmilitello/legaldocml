package io.legaldocml.akn.element;

import io.legaldocml.akn.group.SpeechSection;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

/**
 * This element is a structural container for parts of a debates that contain written statements by participants.
 * <p/>
 * <pre>
 * 	 <xsd:element name="ministerialStatements" type="althierarchy"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class MinisterialStatements extends AltHierarchy implements SpeechSection {

    /**
     * XML tag element name.
     */
    public static final String ELEMENT = "ministerialStatements";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 21);
        super.write(writer);
        writer.writeEnd(ADDRESS, 21);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return ELEMENT;
    }

}
