package io.legaldocml.akn.other;

import io.legaldocml.akn.element.AnyOtherTypeElement;
import io.legaldocml.util.CharArray;
import io.legaldocml.io.QName;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.legaldocml.util.StringWriterTemplate.writeAttribute;
import static io.legaldocml.util.StringWriterTemplate.writeElement;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class ExternalElementWithNS implements AnyOtherTypeElement {

    /**
     * SLF4J Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ExternalElementWithNS.class);

    private final QName qName;
    private final CharArray namespace;

    private List<ExternalAttribute> attributes = new ArrayList<>(2);

    public ExternalElementWithNS(QName qName, CharArray namespace) {
        this.qName = qName;
        this.namespace = namespace;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writeElement(writer, this.qName.toString(), w -> {

            try {
                // write namespace.
                writeAttribute(w, "xmlns:".concat(qName.getPrefix()), namespace.value());
                // write other attributes
                if (this.attributes != null) {
                    for (ExternalAttribute attribute : this.attributes) {
                        attribute.write(writer);
                    }
                }
            } catch (IOException e) {
                LOGGER.error("Failed to write attribute [{}]", qName);
            }

        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {
        reader.forEach(this, (object, name, value, prefixNS) -> {
            LOGGER.debug("read attributes [{}]->[{}]->[{}]", name, value, prefixNS);

            if (prefixNS == 0) {
                this.attributes.add(new ExternalAttribute(name, value));
            } else {
                throw new UnsupportedOperationException();
            }
        });

        reader.nextStartOrEndElement();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return this.qName.toString();
    }
}
