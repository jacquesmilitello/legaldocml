package io.legaldocml.schematron.model;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.util.ExternalizableList;
import io.legaldocml.io.AttributeGetterSetter;
import io.legaldocml.io.QName;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.function.Supplier;

import static io.legaldocml.akn.element.Attributes.attributeGetterSetter4String;
import static io.legaldocml.schematron.model.SchAttributes.ID;
import static io.legaldocml.schematron.model.SchReadException.Type.INVALID_STATE;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Pattern implements SchemaElement{

    /**
     * SLF4J logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(Pattern.class);

    private static final ImmutableMap<String, AttributeGetterSetter<SchObject>> ATTRIBUTES;

    private static final ImmutableMap<String, Supplier<PatternElement>> ELEMS;

    static {
        ATTRIBUTES = ImmutableMap.<String, AttributeGetterSetter<SchObject>>builder()
                .put(ID, attributeGetterSetter4String(ID, getFieldOffset(Pattern.class, "id")))
                .build();

        ELEMS = ImmutableMap.<String, Supplier<PatternElement>>builder()
                .put(SchElements.RULE, Rule::new)
                .build();
    }

    private String id;

    private final ExternalizableList<PatternElement> elements = new ExternalizableList<>(new PatternElement[8]);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {
        QName qName = reader.getQName();
        SchAttributes.read(reader, this);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("[{}] with id [{}]",qName, this.id);
        }

        SchElements.read(reader, elements, ELEMS, qName);

        if (!SchElements.PATTERN.equals(qName.getLocalName())) {
            throw new SchReadException(INVALID_STATE, reader, reader.getQName(), SchElements.PATTERN);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableMap<String, AttributeGetterSetter<SchObject>> attributes() {
        return ATTRIBUTES;
    }
}
