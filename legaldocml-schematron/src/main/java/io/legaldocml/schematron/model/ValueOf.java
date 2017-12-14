package io.legaldocml.schematron.model;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.io.AttributeGetterSetter;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.element.Attributes.biConsumerUri;
import static io.legaldocml.schematron.model.SchAttributes.SELECT;
import static io.legaldocml.unsafe.UnsafeHelper.getFieldOffset;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class ValueOf implements SchMixedContent {

    private static final ImmutableMap<String, AttributeGetterSetter<SchObject>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, AttributeGetterSetter<SchObject>>builder()
                .put(SELECT, biConsumerUri(SELECT, getFieldOffset(ValueOf.class, "select")))
                .build();
    }

    private String select;

    public String getSelect() {
        return select;
    }

    public void setSelect(String select) {
        this.select = select;
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
