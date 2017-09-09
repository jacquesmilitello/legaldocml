package io.legaldocml.akn.element;

import io.legaldocml.akn.group.SpeechSection;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.DECLARATION_OF_VOTE;

/**
 * This element is a structural container for parts of a debates that are relevant to the declaration of votes.
 *
 * <pre>
 * 	 <xsd:element name="declarationOfVote" type="althierarchy"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class DeclarationOfVote extends AltHierarchy implements SpeechSection {

    /**
     * Memory address.
     */
    private static final long ADDRESS_DECLARATION_OF_VOTE = Buffers.address(DECLARATION_OF_VOTE);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_DECLARATION_OF_VOTE, 17);
        super.write(writer);
        writer.writeEnd(ADDRESS_DECLARATION_OF_VOTE, 17);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return DECLARATION_OF_VOTE;
    }

}
