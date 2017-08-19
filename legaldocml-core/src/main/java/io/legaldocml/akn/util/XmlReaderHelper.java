package io.legaldocml.akn.util;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.AkomaNtoso;
import io.legaldocml.akn.DocumentType;
import io.legaldocml.akn.HasMixedContent;
import io.legaldocml.akn.MandatoryElementException;
import io.legaldocml.akn.element.StringInlineCM;
import io.legaldocml.akn.other.UnsupportedModule;
import io.legaldocml.io.QName;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.impl.XmlChannelReader;
import io.legaldocml.module.AknModule;
import io.legaldocml.module.Module;
import io.legaldocml.module.Modules;
import io.legaldocml.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.stream.XMLStreamConstants;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class XmlReaderHelper {

    /**
     * SLJ4F
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(XmlChannelReader.class);

    private XmlReaderHelper() {
    }


    public static <T extends DocumentType> AkomaNtoso createAkomaNtoso(XmlReader reader) {


        if (!AkomaNtoso.ELEMENT.equals(reader.getQName().getLocalName())) {
            throw new MandatoryElementException(null, AkomaNtoso.ELEMENT, reader);
        }

        List<Module> modules = new ArrayList<>(4);

        reader.getNamespaces().forEach((p, n) -> {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("namespace : prefix [{}] -> value [{}]", p, n);
            }

            if (Strings.isEmpty(p) && Strings.isEmpty(n)) {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("default namespace xmlns empty -> skip");
                }
                return;
            }

            Module mod = Modules.get(n);
            if (mod == null) {

                LOGGER.info("Unsupported module for prefix [{}] and uri [{}]", p, n);
                mod = new UnsupportedModule(p, n);

            }
            modules.add(mod);
        });

        AknModule aknModule = null;
        for (Module module : modules) {
            if (module instanceof AknModule) {
                aknModule = (AknModule) module;
            }
        }

        if (aknModule == null) {
            throw new RuntimeException();
        }

        AkomaNtoso<T> akomaNtoso = new AkomaNtoso<>(aknModule.newAkomaNtosoContext());
        reader.setContext(akomaNtoso.getContext());

        for (Module module : modules) {
            if (module != aknModule) {
                akomaNtoso.getContext().add(module);
            }
        }

        reader.nextStartOrEndElement();
        return akomaNtoso;
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

    @SuppressWarnings("unchecked")
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
