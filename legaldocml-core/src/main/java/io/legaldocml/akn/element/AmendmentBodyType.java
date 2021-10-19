package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.attribute.CoreOpt;
import io.legaldocml.akn.group.AmendmentBlock;
import io.legaldocml.akn.util.AknList;
import io.legaldocml.akn.util.XmlReaderHelper;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;
import java.util.function.Supplier;

import static io.legaldocml.akn.AknElements.AMENDMENT_CONTENT;
import static io.legaldocml.akn.AknElements.AMENDMENT_HEADING;
import static io.legaldocml.akn.AknElements.AMENDMENT_JUSTIFICATION;
import static io.legaldocml.akn.AknElements.AMENDMENT_REFERENCE;
import static java.util.Objects.requireNonNull;

/**
 * <pre>
 *   <xsd:complexType name="amendmentBodyType">
 * 	   <xsd:complexType>
 * 	     <xsd:sequence minOccurs="1" maxOccurs="unbounded">
 * 		   <xsd:group ref="amendmentBlock" />
 * 		<xsd:sequence>
 * 	   <xsd:attributeGroup ref="coreopt" />
 * 	   <xsd:complexType>
 *   <xsd:complexType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class AmendmentBodyType extends AbstractCore implements CoreOpt {

    private static final ImmutableMap<String, Supplier<AmendmentBlock>> ELEMS;

    static {
        ELEMS = ImmutableMap.<String, Supplier<AmendmentBlock>>builder()
                .put(AMENDMENT_HEADING, AmendmentHeading::new)
                .put(AMENDMENT_CONTENT, AmendmentContent::new)
                .put(AMENDMENT_REFERENCE, AmendmentReference::new)
                .put(AMENDMENT_JUSTIFICATION, AmendmentJustification::new)
                .build();
    }

    // Mandatory (min 1)
    private final AknList<AmendmentBlock> blocks = new AknList<>(new AmendmentBlock[4]);

    public final void add(AmendmentBlock block) {
        this.blocks.add(requireNonNull(block));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        CoreOpt.super.write(writer);
        this.blocks.write(writer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {
        super.read(reader);
        XmlReaderHelper.read(reader,this,  blocks, ELEMS);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(AknVisitor visitor) {
        this.blocks.accept(visitor);
    }

}