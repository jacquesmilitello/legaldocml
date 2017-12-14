package io.legaldocml.schematron.model;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.io.AttributeGetterSetter;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.element.Attributes.biConsumerString;
import static io.legaldocml.schematron.model.SchAttributes.NAME;
import static io.legaldocml.schematron.model.SchAttributes.VALUE;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Let implements SchemaElement {

    private static final ImmutableMap<String, AttributeGetterSetter<SchObject>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, AttributeGetterSetter<SchObject>>builder()
                .put(NAME, biConsumerString(NAME, getFieldOffset(Let.class, "name")))
                .put(VALUE, biConsumerString(VALUE, getFieldOffset(Let.class, "value")))
                .build();
    }

    private String name;
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

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
        reader.nextStartOrEndElement();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableMap<String, AttributeGetterSetter<SchObject>> attributes() {
        return ATTRIBUTES;
    }
}
