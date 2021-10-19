package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.attribute.CoreOpt;
import io.legaldocml.akn.container.Container;
import io.legaldocml.akn.util.AknList;
import io.legaldocml.akn.util.XmlReaderHelper;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.util.Buffers;
import io.legaldocml.util.ListIterable;

import java.io.IOException;
import java.util.function.Supplier;

import static io.legaldocml.akn.AknElements.TD;
import static io.legaldocml.akn.AknElements.TH;
import static io.legaldocml.akn.AknElements.TR;
import static java.util.Objects.requireNonNull;

/**
 * <pre>
 *   <xsd:element name="tr">
 * 	   <xsd:complexType>
 * 	     <xsd:choice minOccurs="1" maxOccurs="unbounded">
 * 		   <xsd:element ref="th"/>
 * 		   <xsd:element ref="td"/>
 * 		 <xsd:choice>
 * 		 <xsd:attributeGroup ref="coreopt"/>
 * 	   <xsd:complexType>
 *   <xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Tr extends AbstractCore implements CoreOpt, SubFlowStructureElement , Container<TrElement> {

    /**
     * Memory address.
     */
    private static final long ADDRESS_TR = Buffers.address(TR);

    private static final ImmutableMap<String, Supplier<TrElement>> ELEMS;

    static {
        ELEMS = ImmutableMap.<String, Supplier<TrElement>>builder()
                .put(TH, Th::new)
                .put(TD, Td::new)
                .build();
    }

    // Mandatory (min 1)
    private final AknList<TrElement> trs = new AknList<>(new TrElement[6]);

    public void add(TrElement element) {
        this.trs.add(requireNonNull(element));
    }

    public void add(int index, TrElement element) {
        this.trs.add(index, requireNonNull(element));
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public ListIterable<TrElement> iterable() {
        return this.trs.iterable();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_TR, 2);
        CoreOpt.super.write(writer);
        this.trs.write(writer);
        writer.writeEnd(ADDRESS_TR, 2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {
        super.read(reader);
        XmlReaderHelper.read(reader, this, this.trs, ELEMS);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return TR;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(AknVisitor visitor) {
        if (visitor.visitEnter(this)) {
            this.trs.accept(visitor);
            visitor.visitLeave(this);
        }
    }

}