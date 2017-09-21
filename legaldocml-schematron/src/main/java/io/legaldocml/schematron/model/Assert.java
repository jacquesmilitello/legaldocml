package io.legaldocml.schematron.model;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.util.ExternalizableList;
import io.legaldocml.util.CharArray;
import io.legaldocml.io.Externalizable;
import io.legaldocml.io.QName;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.function.BiConsumer;

import static io.legaldocml.akn.element.Attributes.biConsumerUri;
import static io.legaldocml.schematron.model.SchReadException.Type.INVALID_STATE;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Assert extends AbstractLinkableRich implements RuleElement {

    /**
     * SLF4J Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(Assert.class);

    private static final ImmutableMap<String, BiConsumer<Externalizable, CharArray>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, BiConsumer<Externalizable, CharArray>>builder()
                .putAll(AbstractLinkableRich.ATTRIBUTES)
                .put(SchAttributes.TEST, biConsumerUri(getFieldOffset(Assert.class, "test")))
                .build();
    }

    private String test;

    private final ExternalizableList<SchMixedContent> content = new ExternalizableList<>(new SchMixedContent[8]);

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
        SchAttributes.read(reader, this);

        QName qName = reader.getQName();
        SchElements.read(reader,qName, content);

        if (!SchElements.ASSERT.equals(qName.getLocalName())) {
            throw new SchReadException(INVALID_STATE, reader, reader.getQName(), SchElements.ASSERT);
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
