package io.legaldocml.akn.other;

import io.legaldocml.akn.element.AnyOtherTypeElement;
import io.legaldocml.akn.element.StringInlineCM;
import io.legaldocml.akn.util.AknList;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.CharArrays;
import io.legaldocml.io.QName;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;

import javax.xml.stream.XMLStreamConstants;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class SimpleExternalElement implements AnyOtherTypeElement {

    private final String prefix;
    private final String localName;

    private final AknList<StringInlineCM> data = new AknList<>(new StringInlineCM[2]);

    private List<ExternalAttribute> attributes = new ArrayList<>(2);

    public SimpleExternalElement(String prefix,String localName) {
        this.prefix = prefix;
        this.localName = localName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {

        StringBuilder builder = new StringBuilder(prefix).append(':').append(localName);

        MappedByteBuffer buffer = null;
        try {
            byte[] bytes = builder.toString().getBytes(StandardCharsets.US_ASCII);
            buffer = (MappedByteBuffer) ByteBuffer.allocateDirect(bytes.length);
            buffer.put(bytes);
            buffer.flip();
            long addr = ((sun.nio.ch.DirectBuffer) buffer).address();

            writer.writeStart(addr, bytes.length);
            if (this.attributes != null) {
                for (ExternalAttribute attribute : this.attributes) {
                    attribute.write(writer);
                }
            }

            this.data.write(writer);

            writer.writeEnd(addr, bytes.length);

        } finally {
            if (buffer != null) {
                Buffers.releaseDirectByteBuffer(buffer);
            }
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {
        QName qname = reader.getQName();
        reader.forEach(this, (object, name, value, prefixNS) -> {
            if (prefixNS == 0) {
                this.attributes.add(new ExternalAttribute(name, value, CharArrays.constant("")));
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

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return this.localName;
    }
}
