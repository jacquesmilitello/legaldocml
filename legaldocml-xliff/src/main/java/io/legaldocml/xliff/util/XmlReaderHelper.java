package io.legaldocml.xliff.util;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.io.QName;
import io.legaldocml.io.XmlReader;
import io.legaldocml.xliff.element.MixedContent;
import io.legaldocml.xliff.element.StringMixedContent;

import javax.xml.stream.XMLStreamConstants;
import java.util.function.Supplier;

public class XmlReaderHelper {

    public static void read(XmlReader reader, XliffList<MixedContent> data, ImmutableMap<String, Supplier<MixedContent>> elements) {
        MixedContent mixedContent;

        QName qName = reader.getQName();
        int eventType;

        while (true) {
            eventType = reader.next();
            if (eventType == XMLStreamConstants.START_ELEMENT) {
                Supplier<MixedContent> supplier = elements.get(reader.getQName().getLocalName());
                if (supplier == null) {
                    throw new RuntimeException(qName + " --> [" + reader.getQName() + "]");
                }
                mixedContent = supplier.get();
                mixedContent.read(reader);
                data.add(mixedContent);
                continue;
            }
            if (eventType == XMLStreamConstants.CHARACTERS) {
                char[] content = reader.getText().value();
                if (content != null && content.length > 0) {
                    data.add(new StringMixedContent(content));
                }
                continue;
            }
            if (eventType == XMLStreamConstants.PROCESSING_INSTRUCTION) {
                // TODO
                continue;
            }
            if (eventType == XMLStreamConstants.END_ELEMENT && qName.equals(reader.getQName())) {
                break;
            }
        }
    }
}
