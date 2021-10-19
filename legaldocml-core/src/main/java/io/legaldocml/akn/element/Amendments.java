package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.container.Container;
import io.legaldocml.akn.util.AknList;
import io.legaldocml.akn.util.XmlReaderHelper;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.util.ListIterable;

import java.io.IOException;
import java.util.function.Supplier;

import static io.legaldocml.akn.AknElements.EFFICACY_MOD;
import static io.legaldocml.akn.AknElements.FORCE_MOD;
import static io.legaldocml.akn.AknElements.LEGAL_SYSTEM_MOD;
import static io.legaldocml.akn.AknElements.MEANING_MOD;
import static io.legaldocml.akn.AknElements.SCOPE_MOD;
import static io.legaldocml.akn.AknElements.TEXTUAL_MOD;

/**
 * The complex type Amendments is a list of all the amendment elements that can be used on a document analysis.
 *
 * <pre>
 *   <xsd:complexType name="Amendments">
 * 	   <xsd:choice minOccurs="1" maxOccurs="unbounded">
 * 	     <xsd:element ref="textualMod" />
 * 		 <xsd:element ref="meaningMod" />
 * 		 <xsd:element ref="scopeMod" />
 * 		 <xsd:element ref="forceMod" />
 * 		 <xsd:element ref="efficacyMod" />
 * 		 <xsd:element ref="legalSystemMod" />
 * 	   <xsd:choice>
 *   <xsd:complexType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class Amendments implements AknObject {

    private static final ImmutableMap<String, Supplier<AmendmentsElement>> ELEMS;

    static {
        ELEMS = ImmutableMap.<String, Supplier<AmendmentsElement>>builder()
                .put(TEXTUAL_MOD, TextualMod::new)
                .put(MEANING_MOD, MeaningMod::new)
                .put(SCOPE_MOD, ScopeMod::new)
                .put(FORCE_MOD, ForceMod::new)
                .put(EFFICACY_MOD, EfficacyMod::new)
                .put(LEGAL_SYSTEM_MOD, LegalSystemMod::new)
                .build();
    }

    // Mandatory (min 1).
    private final AknList<AmendmentsElement> elements = new AknList<>(new AmendmentsElement[4]);

    public final void add(AmendmentsElement element) {
        this.elements.add(element);
    }

    public final void add(int index, AmendmentsElement element) {
        this.elements.add(index, element);
    }

    public final ListIterable<AmendmentsElement> iterable() {
        return this.elements.iterable();
    }

    private AknObject parent;
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
        XmlReaderHelper.read(reader, this, elements, ELEMS);
    }

    @SuppressWarnings("unchecked")
    public <T extends AknObject> T getParent() {
        return (T)parent;
    }

    public <T extends AknObject> void setParent(T parent) {
        this.parent = parent;
    }

}