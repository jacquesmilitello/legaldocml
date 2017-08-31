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
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Schema implements SchObject {

    /**
     * SLF4J logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(Schema.class);

    private static final ImmutableMap<String, Supplier<SchemaElement>> ELEMS;

    static {
        ELEMS = ImmutableMap.<String, Supplier<SchemaElement>>builder()
                .put(SchElements.PATTERN, Pattern::new)
                .put(SchElements.LET, Let::new)
                .build();
    }
    private static final ImmutableMap<String, BiConsumer<Externalizable, CharArray>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, BiConsumer<Externalizable, CharArray>>builder()
                .put(SchAttributes.ID, biConsumerString(getFieldOffset(Schema.class, "id")))
                .build();
    }

    private String id;

    private final ExternalizableList<SchemaElement> elems = new ExternalizableList<>(new SchemaElement[8]);

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

        SchElements.read(reader, elems, ELEMS, qName);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableMap<String, BiConsumer<Externalizable, CharArray>> attributes() {
        return ATTRIBUTES;
    }
}
