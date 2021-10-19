package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.attribute.CoreOpt;
import io.legaldocml.akn.group.SpeechSection;
import io.legaldocml.akn.util.AknList;
import io.legaldocml.akn.util.XmlReaderHelper;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;
import java.util.function.Supplier;

import static io.legaldocml.akn.element.Attributes.ATTRIBUTE_CONSUMER;

/**
 * The type debateBodyType specifies a content model of the main hierarchy of a debate.
 *
 * <pre>
 *   <xsd:complexType name="debateBodyType">
 *     <xsd:sequence minOccurs="1" maxOccurs="unbounded">
 * 	     <xsd:group ref="speechSection"/>
 * 	   <xsd:sequence>
 * 	   <xsd:attributeGroup ref="coreopt"/>
 *   <xsd:complexType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class DebateBodyType extends AbstractCore implements CoreOpt {

    private static final ImmutableMap<String, Supplier<SpeechSection>> ELEMS;

    static {
        ELEMS = ImmutableMap.<String, Supplier<SpeechSection>>builder()
                .putAll(Groups.convertSuper(Groups.speechSection()))
                .build();
    }


    // Mandatory (min 1).
    private final AknList<SpeechSection> sections = new AknList<>(new SpeechSection[4]);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        CoreOpt.super.write(writer);
        this.sections.write(writer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {
        reader.forEach(this, ATTRIBUTE_CONSUMER);
        XmlReaderHelper.read(reader, this, sections, ELEMS);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(AknVisitor visitor) {
        this.sections.accept(visitor);
    }

}