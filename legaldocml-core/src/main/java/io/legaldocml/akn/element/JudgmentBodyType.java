package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.group.JudgmentBlock;
import io.legaldocml.akn.util.AknList;
import io.legaldocml.akn.util.XmlReaderHelper;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;
import java.util.function.Supplier;

/**
 * The type judgmentBodyType specifies a content model of the main hierarchy of a judgment document.
 * <p/>
 * <pre>
 *   <xsd:complexType name="judgmentBodyType">
 *     <xsd:choice minOccurs="1" maxOccurs="unbounded">
 *       <xsd:group ref="judgmentBlock"/>
 *     </xsd:choice>
 *     <xsd:attributeGroup ref="coreopt"/>
 *   </xsd:complexType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class JudgmentBodyType extends CoreOptImpl {

    private static final ImmutableMap<String, Supplier<JudgmentBlock>> ELEMS;

    static {
        ELEMS = ImmutableMap.<String, Supplier<JudgmentBlock>>builder()
                .put(Introduction.ELEMENT, Introduction::new)
                .put(Background.ELEMENT, Background::new)
                .put(Arguments.ELEMENT, Arguments::new)
                .put(Motivation.ELEMENT, Motivation::new)
                .put(Decision.ELEMENT, Decision::new)
                .build();
    }

    // Mandatory (min 1).
    private final AknList<JudgmentBlock> elements = new AknList<>(new JudgmentBlock[4]);

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {
        super.read(reader);
        XmlReaderHelper.read(reader, this.elements, ELEMS);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        super.write(writer);
        this.elements.write(writer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(AknVisitor visitor) {
        this.elements.accept(visitor);
    }

}