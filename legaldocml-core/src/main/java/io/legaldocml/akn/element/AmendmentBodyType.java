package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.group.AmendmentBlock;
import io.legaldocml.akn.util.AknList;
import io.legaldocml.akn.util.XmlReaderHelper;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;
import java.util.function.Supplier;

/**
 * <pre>
 *   &lt;xsd:complexType name="amendmentBodyType"&gt;
 * 	   &lt;xsd:complexType&gt;
 * 	     &lt;xsd:sequence minOccurs="1" maxOccurs="unbounded"&gt;
 * 		   &lt;xsd:group ref="amendmentBlock" /&gt;
 * 		&lt;xsd:sequence&gt;
 * 	   &lt;xsd:attributeGroup ref="coreopt" /&gt;
 * 	   &lt;xsd:complexType&gt;
 *   &lt;xsd:complexType&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class AmendmentBodyType extends CoreOptImpl {

    private static final ImmutableMap<String, Supplier<AmendmentBlock>> ELEMS;

    static {
        ELEMS = ImmutableMap.<String, Supplier<AmendmentBlock>>builder()
                .put(AmendmentHeading.ELEMENT, AmendmentHeading::new)
                .put(AmendmentContent.ELEMENT, AmendmentContent::new)
                .put(AmendmentReference.ELEMENT, AmendmentReference::new)
                .put(AmendmentJustification.ELEMENT, AmendmentJustification::new)
                .build();
    }

    // Mandatory (min 1)
    private final AknList<AmendmentBlock> blocks = new AknList<>(new AmendmentBlock[4]);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        super.write(writer);
        this.blocks.write(writer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {
        super.read(reader);
        XmlReaderHelper.read(reader, blocks, ELEMS);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(AknVisitor visitor) {
        this.blocks.accept(visitor);
    }

}