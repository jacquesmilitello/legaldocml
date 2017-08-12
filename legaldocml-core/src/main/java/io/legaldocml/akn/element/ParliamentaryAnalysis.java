package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.util.AknList;
import io.legaldocml.akn.util.XmlReaderHelper;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;
import java.util.function.Supplier;

/**
 * The complex type parliamentaryAnalysis is a list of all the parliamentary analysis elements that can be used on the
 * analysis of a debate.
 *
 * <pre>
 *   &lt;xsd:complexType name="parliamentaryAnalysis"&gt;
 *     &lt;xsd:choice minOccurs="1" maxOccurs="unbounded"&gt;
 *       &lt;xsd:element ref="quorumVerification"/&gt;
 *       &lt;xsd:element ref="voting"/&gt;
 * 	     &lt;xsd:element ref="recount"/&gt;
 * 	   &lt;xsd:choice&gt;
 *   &lt;xsd:complexType&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class ParliamentaryAnalysis implements AknObject {

    // Mandatory (min 1).
    private final AknList<ParliamentaryAnalysisElement> elements = new AknList<>(new ParliamentaryAnalysisElement[6]);

    private static final ImmutableMap<String, Supplier<ParliamentaryAnalysisElement>> ELEMS;

    static {
        ELEMS = ImmutableMap.<String, Supplier<ParliamentaryAnalysisElement>>builder()
                .put(QuorumVerification.ELEMENT, QuorumVerification::new)
                .put(Voting.ELEMENT, Voting::new)
                .put(Recount.ELEMENT, Recount::new)
                .build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        this.elements.write(writer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {
        XmlReaderHelper.read(reader, this.elements, ELEMS);
    }


}