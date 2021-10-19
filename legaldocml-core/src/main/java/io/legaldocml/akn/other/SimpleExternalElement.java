package io.legaldocml.akn.other;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.element.AnyOtherTypeElement;
import io.legaldocml.akn.element.StringInlineCM;
import io.legaldocml.akn.util.AknList;
import io.legaldocml.io.QName;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.util.StringWriterTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.stream.XMLStreamConstants;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class SimpleExternalElement implements AnyOtherTypeElement {

    /**
     * SLF4J Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleExternalElement.class);

    private final String prefix;
    private final String localName;

    private final AknList<StringInlineCM> data = new AknList<>(new StringInlineCM[2]);
    private final List<ExternalAttribute> attributes = new ArrayList<>(2);

    private AknObject parent;

    public SimpleExternalElement(String prefix, String localName) {
        this.prefix = prefix;
        this.localName = localName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        StringBuilder builder = new StringBuilder(prefix).append(':').append(localName);
        StringWriterTemplate.writeElement(writer, builder.toString(), w -> {
            if (this.attributes != null) {
                for (ExternalAttribute attribute : this.attributes) {
                    try {
                        attribute.write(writer);
                    } catch (IOException cause) {
                        LOGGER.error("Failed to write external attribute [" + attribute + "]", cause);
                    }
                }
            }

            try {
                this.data.write(writer);
            } catch (IOException cause) {
                LOGGER.error("Failed to write content [" + this.data + "]", cause);
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {
        QName qname = reader.getQName();
        reader.forEach(this, (channelReader, object, name, value, prefixNS) -> {
            if (prefixNS == 0) {
                this.attributes.add(new ExternalAttribute(name, value));
            } else {
                throw new UnsupportedOperationException();
            }
        });

        int eventType;
        while (true) {
            eventType = reader.next();
            if (eventType == XMLStreamConstants.START_ELEMENT) {
                throw new UnsupportedOperationException("External tag inside external tag => create module");
            }
            if (eventType == XMLStreamConstants.CHARACTERS) {
                char[] content = reader.getText().value();
                if (content != null && content.length > 0) {
                    data.add(new StringInlineCM(content));
                }
                continue;
            }
            if (eventType == XMLStreamConstants.END_ELEMENT && qname.equals(reader.getQName())) {
                break;
            }
        }
    }

    @Override
    public String name() {
        return this.localName;
    }

    @SuppressWarnings("unchecked")
    public <T extends AknObject> T getParent() {
        return (T)parent;
    }

    public <T extends AknObject> void setParent(T parent) {
        this.parent = parent;
    }
}
