package io.legaldocml.akn.util;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.element.StringInlineCM;
import io.legaldocml.akn.HasMixedContent;
import io.legaldocml.io.QName;
import io.legaldocml.io.XmlReader;

import javax.xml.stream.XMLStreamConstants;
import java.util.function.Supplier;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class XmlReaderHelper {

    private XmlReaderHelper() {
    }

    public static <T extends AknObject> void read(XmlReader reader, AknList<T> list, ImmutableMap<String, Supplier<T>> map) {
        QName qName = reader.getQName();
        int eventType;
        while (true) {
            if (reader.getEventType() == XMLStreamConstants.END_DOCUMENT) {
                return;
            }

            eventType = reader.next();
            if (eventType == XMLStreamConstants.START_ELEMENT) {
                Supplier<T> supplier = map.get(reader.getQName().getLocalName());
                if (supplier == null) {
                    throw new RuntimeException("Missing Element [" + reader.getQName() + "] in [" + qName + "]");
                }
                T ako = supplier.get();
                ako.read(reader);
                list.add(ako);
            }
            if (reader.getEventType() == XMLStreamConstants.END_ELEMENT && qName.equals(reader.getQName())) {
                break;
            }

        }
    }

    public static <T extends AknObject> void read(XmlReader reader, AknList<T> list, ImmutableMap<String, Supplier<T>> map, QName parent) {
        while (true) {

            if (reader.getEventType() == XMLStreamConstants.START_ELEMENT) {
                Supplier<T> supplier = map.get(reader.getQName().getLocalName());
                if (supplier == null) {
                    throw new RuntimeException("Missing [" + reader.getQName() + "] in [" + parent + "]");
                }
                T ako = supplier.get();
                ako.read(reader);
                list.add(ako);
                reader.nextStartOrEndElement();
                continue;
            }

            if (reader.getEventType() == XMLStreamConstants.END_ELEMENT && parent.equals(reader.getQName())) {
                break;
            }

            if (reader.getEventType() == XMLStreamConstants.END_DOCUMENT) {
                return;
            }

            if (reader.getEventType() != XMLStreamConstants.START_ELEMENT) {
                reader.next();
            }
        }
    }

    public static <T extends HasMixedContent> void readWithCharacters(XmlReader reader, AknList<T> list, ImmutableMap<String, Supplier<T>> map) {
        QName qName = reader.getQName();
        int eventType;
        while (true) {
            if (reader.getEventType() == XMLStreamConstants.END_DOCUMENT) {
                return;
            }

            eventType = reader.next();
            if (eventType == XMLStreamConstants.START_ELEMENT) {
                Supplier<T> supplier = map.get(reader.getQName().getLocalName());
                if (supplier == null) {
                    throw new RuntimeException("Missing [" + reader.getQName() + "] in [" + qName + "]");
                }
                T ako = supplier.get();
                ako.read(reader);
                list.add(ako);
            }

            if (reader.getEventType() == XMLStreamConstants.CHARACTERS) {
                char[] content = reader.getText().value();
                if (content != null) {
                    list.add((T) new StringInlineCM(content));
                }
                continue;
            }

            if (reader.getEventType() == XMLStreamConstants.END_ELEMENT && qName.equals(reader.getQName())) {
                break;
            }

        }
    }

    public static <T extends AknObject> void read(XmlReader reader, AknList<T> list, ImmutableMap<String, Supplier<T>> map, QName parent, String otherLocalName) {

        int depth = reader.getDepth() - 1;

        while (true) {
            if (reader.getEventType() == XMLStreamConstants.START_ELEMENT) {

                // This test is because some time (example Hierarchy) element has a brother (wrap in the example).
                if (reader.getQName().equalsLocalName(otherLocalName)) {
                    return;
                }

                Supplier<T> supplier = map.get(reader.getQName().getLocalName());
                if (supplier == null) {
                    throw new RuntimeException("Missing [" + reader.getQName() + "] in [" + parent + "]");
                }
                T ako = supplier.get();
                ako.read(reader);
                list.add(ako);
                //continue;
            }

            if (reader.getEventType() == XMLStreamConstants.END_DOCUMENT) {
                return;
            }

            if (reader.getEventType() == XMLStreamConstants.END_ELEMENT && depth == reader.getDepth() && parent.equals(reader.getQName())) {
                return;
            }

            if (reader.getEventType() == XMLStreamConstants.END_DOCUMENT) {
                return;
            }

            if (reader.getEventType() != XMLStreamConstants.START_ELEMENT) {
                reader.next();
            }
        }
    }
}
