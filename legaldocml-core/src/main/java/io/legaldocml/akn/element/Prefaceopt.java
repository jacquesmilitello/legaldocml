package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.attribute.CoreOpt;
import io.legaldocml.akn.container.BlockElementsContainer;
import io.legaldocml.akn.container.PrefaceContainersContainer;
import io.legaldocml.akn.group.ANblock;
import io.legaldocml.akn.group.BlockElements;
import io.legaldocml.akn.group.HTMLblock;
import io.legaldocml.akn.group.PrefaceContainers;
import io.legaldocml.akn.util.AknList;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.QName;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;

import javax.xml.stream.XMLStreamConstants;
import java.io.IOException;
import java.util.function.Supplier;

import static io.legaldocml.akn.element.Groups.blockElements;
import static io.legaldocml.akn.element.Groups.convertSuper;
import static io.legaldocml.akn.element.Groups.prefaceContainers;
import static java.util.Objects.requireNonNull;

/**
 * the complex type prefaceopt defines the content model and attributes used by preface. Here the eId attribute is
 * optional
 *
 * <pre>
 *   <xsd:complexType name="prefaceopt">
 * 	   <xsd:choice minOccurs="1" maxOccurs="unbounded">
 * 	     <xsd:group ref="blockElements"/>
 * 		 <xsd:group ref="prefaceContainers"/>
 * 	   <xsd:choice>
 * 	   <xsd:attributeGroup ref="coreopt"/>
 *   <xsd:complexType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class Prefaceopt extends AbstractCore implements CoreOpt, BlockElementsContainer<PrefaceoptElement>, PrefaceContainersContainer<PrefaceoptElement> {

    private static final ImmutableMap<String, Supplier<PrefaceoptElement>> ELEMS;

    static {
        ELEMS = ImmutableMap.<String, Supplier<PrefaceoptElement>>builder()
                .putAll(convertSuper(blockElements()))
                .putAll(convertSuper(prefaceContainers()))
                .build();
    }

    // Mandatory (min 1).
    private final AknList<PrefaceoptElement> pes = new AknList<>(new PrefaceoptElement[4]);

    private void addPrefaceoptElement(PrefaceoptElement element) {
        this.pes.add(requireNonNull(element));
    }

    private boolean removePrefaceoptElement(PrefaceoptElement element) {
        return this.pes.remove(requireNonNull(element));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void add(BlockElements elements) {
        addPrefaceoptElement(elements);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void add(ANblock block) {
        addPrefaceoptElement(block);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean remove(ANblock block) {
        return removePrefaceoptElement(block);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void add(HTMLblock block) {
        addPrefaceoptElement(block);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void add(PrefaceContainers prefaceContainers) {
        addPrefaceoptElement(prefaceContainers);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void add(Container container) {
        addPrefaceoptElement(container);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(LongTitle longTitle) {
        addPrefaceoptElement(longTitle);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {
        super.read(reader);
        PrefaceoptElement elem;
        QName qName = reader.getQName();

        int eventType;
        while (true) {
            eventType = reader.next();
            if (eventType == XMLStreamConstants.START_ELEMENT) {
                Supplier<PrefaceoptElement> supplier = ELEMS.get(reader.getQName().getLocalName());
                if (supplier == null) {
                    throw new RuntimeException(qName + " --> [" + reader.getQName() + "]");
                }
                elem = supplier.get();
                elem.read(reader);
                this.pes.add(elem);
                continue;
            }
            if (eventType == XMLStreamConstants.END_ELEMENT && qName.equals(reader.getQName())) {
                break;
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        CoreOpt.super.write(writer);
        this.pes.write(writer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(AknVisitor visitor) {
        this.pes.accept(visitor);
    }
}