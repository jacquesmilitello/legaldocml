package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.attribute.Source;
import io.legaldocml.akn.type.AgentRef;
import io.legaldocml.akn.util.AknList;
import io.legaldocml.akn.util.XmlWriterHelper;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.AttributeGetterSetter;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import java.io.IOException;
import java.util.stream.Stream;

import static io.legaldocml.akn.AknAttributes.SOURCE;
import static io.legaldocml.akn.AknElements.STEP;
import static io.legaldocml.akn.AknElements.WORKFLOW;
import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4AgentRef;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * <pre>
 *   <xsd:element name="workflow">
 * 	   <xsd:complexType>
 * 	     <xsd:sequence>
 * 		   <xsd:element ref="step" minOccurs="1" maxOccurs="unbounded"/>
 * 		 <xsd:sequence>
 * 	 	 <xsd:attributeGroup ref="source"/>
 * 	   <xsd:complexType>
 *   <xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Workflow implements Source {

    /**
     * Memory address.
     */
    private static final long ADDRESS_WORKFLOW = Buffers.address(WORKFLOW);

    private static final ImmutableMap<String, AttributeGetterSetter<AknObject>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, AttributeGetterSetter<AknObject>>builder()
                .put(SOURCE, attributeGetterSetter4AgentRef(SOURCE, getFieldOffset(Workflow.class, "source")))
                .build();
    }


    // Mandatory (min 1).
    private final AknList<Step> steps = new AknList<>(new Step[6]);

    // Mandatory
    private AgentRef source;

    public Stream<Step> getSteps() {
        return this.steps.stream();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AgentRef getSource() {
        return this.source;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSource(AgentRef source) {
        this.source = source;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_WORKFLOW, 8);
        XmlWriterHelper.writeSource(writer, this);
        this.steps.write(writer);
        writer.writeEnd(ADDRESS_WORKFLOW, 8);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {
        Attributes.read(reader, this);
        reader.nextStartOrEndElement();

        if (reader.getQName().equalsLocalName(STEP)) {
            Step step;
            do {
                step = new Step();
                step.read(reader);
                this.steps.add(step);
                reader.nextStartOrEndElement();
            } while (reader.getQName().equalsLocalName(STEP));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return WORKFLOW;
    }

    @Override
    public ImmutableMap<String, AttributeGetterSetter<AknObject>> attributes() {
        return ATTRIBUTES;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(AknVisitor visitor) {
        if (visitor.visitEnter(this)) {
            this.steps.accept(visitor);
            visitor.visitLeave(this);
        }
    }
}