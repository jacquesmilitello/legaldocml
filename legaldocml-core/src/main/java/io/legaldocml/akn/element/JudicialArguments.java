package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknElements;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.util.AknList;
import io.legaldocml.akn.util.XmlReaderHelper;
import io.legaldocml.io.QName;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;
import java.util.function.Supplier;

import static io.legaldocml.akn.AknElements.CONTRASTS;

/**
 * The complex type judicialArguments is a list of all the judicial analysis elements that can be used on the analysis
 * of a judgment.
 *
 * <pre>
 *   <xsd:complexType name="judicialArguments">
 *     <xsd:sequence>
 *       <xsd:element ref="result"/>
 *       <xsd:choice minOccurs="1" maxOccurs="unbounded">
 *         <xsd:element ref="supports"/>
 *         <xsd:element ref="isAnalogTo"/>
 *         <xsd:element ref="applies"/>
 *         <xsd:element ref="extends"/>
 *         <xsd:element ref="restricts"/>
 *         <xsd:element ref="derogates"/>
 *         <xsd:element ref="contrasts"/>
 *         <xsd:element ref="overrules"/>
 *         <xsd:element ref="dissentsFrom"/>
 *         <xsd:element ref="putsInQuestion"/>
 *         <xsd:element ref="distinguishes"/>
 *       <xsd:choice>
 *     <xsd:sequence>
 *   <xsd:complexType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class JudicialArguments implements AknObject {

    private static final ImmutableMap<String, Supplier<JudicialArgumentsElement>> ELEMS;

    static {
        ELEMS = ImmutableMap.<String, Supplier<JudicialArgumentsElement>>builder()
                .put(Supports.ELEMENT, Supports::new)
                .put(IsAnalogTo.ELEMENT, IsAnalogTo::new)
                .put(AknElements.APPLIES, Applies::new)
                .put(Extends.ELEMENT, Extends::new)
                .put(Restricts.ELEMENT, Restricts::new)
                .put(Derogates.ELEMENT, Derogates::new)
                .put(CONTRASTS, Contrasts::new)
                .put(Overrules.ELEMENT, Overrules::new)
                .put(DissentsFrom.ELEMENT, DissentsFrom::new)
                .put(PutsInQuestion.ELEMENT, PutsInQuestion::new)
                .put(Distinguishes.ELEMENT, Distinguishes::new)
                .build();
    }

    private Result result;
    private final AknList<JudicialArgumentsElement> elems = new AknList<>(new JudicialArgumentsElement[2]);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        if (this.result != null) {
            this.result.write(writer);
        }
        this.elems.write(writer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {
        final QName parent = reader.getQName();
        reader.nextStartOrEndElement();

        if (reader.getQName().equalsLocalName(Result.ELEMENT)) {
            this.result = new Result();
            this.result.read(reader);
            reader.nextStartOrEndElement();
        }

        XmlReaderHelper.read(reader, this.elems, ELEMS, parent);
    }

}