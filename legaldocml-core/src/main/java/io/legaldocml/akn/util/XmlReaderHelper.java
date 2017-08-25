package io.legaldocml.akn.util;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.AknReadException;
import io.legaldocml.akn.AkomaNtoso;
import io.legaldocml.akn.DocumentType;
import io.legaldocml.akn.HasMixedContent;
import io.legaldocml.akn.MandatoryElementException;
import io.legaldocml.akn.element.StringInlineCM;
import io.legaldocml.akn.other.UnsupportedModule;
import io.legaldocml.io.CharArray;
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
import java.util.function.BiConsumer;
import java.util.function.Supplier;

import static io.legaldocml.akn.AknReadException.*;

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

        NamespaceConsumer namespaceConsumer = new NamespaceConsumer();
        reader.getNamespaces().forEach(namespaceConsumer);

        AknModule aknModule = namespaceConsumer.getAknModules();

        if (aknModule == null) {
           throw new AknReadException(Type.AKN_MODULE_NOT_FOUND,reader, null);
        }

        AkomaNtoso<T> akomaNtoso = new AkomaNtoso<>(aknModule.newAkomaNtosoContext());
        reader.setContext(akomaNtoso.getContext());

        for (Module module : namespaceConsumer.getModules()) {
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
                onStartElement(reader, list, map, qName);
            }
            if (reader.getEventType() == XMLStreamConstants.END_ELEMENT && qName.equals(reader.getQName())) {
                break;
            }

        }
    }

    public static <T extends AknObject> void read(XmlReader reader, AknList<T> list, ImmutableMap<String, Supplier<T>> map, QName parent) {
        while (true) {

            if (reader.getEventType() == XMLStreamConstants.START_ELEMENT) {
                onStartElement(reader, list, map, parent);
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
                onStartElement(reader, list, map, qName);
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

                onStartElement(reader, list, map, parent);
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

    private static <T extends AknObject> void onStartElement(XmlReader reader, AknList<T> list, ImmutableMap<String, Supplier<T>> map, QName parent) {
        Supplier<T> supplier = map.get(reader.getQName().getLocalName());
        if (supplier == null) {
            throw new AknReadException(Type.MISSING_ELEMENT, reader, parent);
        }
        T ako = supplier.get();
        ako.read(reader);
        list.add(ako);
    }

    private static final class NamespaceConsumer implements BiConsumer<CharArray, CharArray> {

        private final List<Module> modules = new ArrayList<>(4);

        /**
         * {@inheritDoc}
         */
        @Override
        public void accept(CharArray prefix, CharArray ns) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("namespace : prefix [{}] -> value [{}]", prefix, ns);
            }

            if (Strings.isEmpty(prefix) && Strings.isEmpty(ns)) {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("default namespace xmlns empty -> skip");
                }
                return;
            }

            Module mod = Modules.get(ns);
            if (mod == null) {

                LOGGER.info("Unsupported module for prefix [{}] and uri [{}]", prefix, ns);
                mod = new UnsupportedModule(prefix, ns);

            }
            modules.add(mod);
        }

        List<Module> getModules() {
            return modules;
        }

        AknModule getAknModules() {
            AknModule aknModule = null;
            for (Module module : this.modules) {
                if (module instanceof AknModule) {
                    if (aknModule != null) {
                        throw new RuntimeException();
                    }
                    aknModule = (AknModule) module;
                }
            }
            return aknModule;
        }
    }

}
