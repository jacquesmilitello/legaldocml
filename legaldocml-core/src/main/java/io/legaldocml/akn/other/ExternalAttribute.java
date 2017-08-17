package io.legaldocml.akn.other;

import io.legaldocml.io.Attribute;
import io.legaldocml.io.CharArray;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.unsafe.UnsafeString;
import io.legaldocml.util.StringWriterTemplate;

import java.io.IOException;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class ExternalAttribute implements Attribute {

    private final String name;
    private final String value;

    public ExternalAttribute(CharArray name, CharArray value) {
        this.name = name.toString();
        this.value = value.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        StringWriterTemplate.writeAttribute(writer, this.name, UnsafeString.getChars(value));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader, CharArray value) {

    }

}
