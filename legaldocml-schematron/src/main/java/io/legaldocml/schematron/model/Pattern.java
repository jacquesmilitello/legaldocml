package io.legaldocml.schematron.model;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.util.ExternalizableList;
import io.legaldocml.io.CharArray;
import io.legaldocml.io.Externalizable;
import io.legaldocml.io.QName;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

import static io.legaldocml.akn.element.Attributes.biConsumerString;
import static io.legaldocml.schematron.model.SchReadException.Type.*;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Pattern implements SchemaElement{

    /**
     * SLF4J logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(Pattern.class);

    private static final ImmutableMap<String, BiConsumer<Externalizable, CharArray>> ATTRIBUTES;

    private static final ImmutableMap<String, Supplier<PatternElement>> ELEMS;

    static {
        ATTRIBUTES = ImmutableMap.<String, BiConsumer<Externalizable, CharArray>>builder()
                .put(SchAttributes.ID, biConsumerString(getFieldOffset(Pattern.class, "id")))
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
    public ImmutableMap<String, BiConsumer<Externalizable, CharArray>> attributes() {
        return ATTRIBUTES;
    }
}