package io.legaldocml.akn.util;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.AknReadException;
import io.legaldocml.akn.AkomaNtoso;
import io.legaldocml.akn.AkomaNtosoContext;
import io.legaldocml.akn.DocumentType;
import io.legaldocml.akn.HasMixedContent;
import io.legaldocml.akn.exception.WriterMandatoryElementException;
import io.legaldocml.akn.element.StringInlineCM;
import io.legaldocml.akn.other.UnsupportedModule;
import io.legaldocml.util.CharArray;
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

import static io.legaldocml.akn.AknElements.AKOMANTOSO;
import static io.legaldocml.akn.AknReadException.Type;

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


    public static <T extends DocumentType> AkomaNtoso<T> createAkomaNtoso(XmlReader reader, AkomaNtosoContext context) {
        if (!AKOMANTOSO.equals(reader.getQName().getLocalName())) {
            throw new WriterMandatoryElementException(null, AKOMANTOSO, reader);
        }

        NamespaceConsumer namespaceConsumer = new NamespaceConsumer();
        reader.getNamespaces().forEach(namespaceConsumer);

        AknModule aknModule = namespaceConsumer.getAknModules();

        if (aknModule == null) {
           throw new AknReadException(Type.AKN_MODULE_NOT_FOUND,reader);
        }

        AkomaNtoso<T> akomaNtoso = new AkomaNtoso<>(context);
        reader.setContext(akomaNtoso.getContext());

        for (Module module : namespaceConsumer.getModules()) {
            akomaNtoso.getContext().add(module);
        }

        if (akomaNtoso.getContext().getAknModule() == null) {
            throw new RuntimeException();
        }

        reader.nextStartOrEndElement();
        return akomaNtoso;
    }


    public static <T extends AknObject> void read(XmlReader reader, AknObject parent, AknList<T> list, ImmutableMap<String, Supplier<T>> map) {
        QName qName = reader.getQName();
        int eventType;
        while (true) {
            if (reader.getEventType() == XMLStreamConstants.END_DOCUMENT) {
                return;
            }
            eventType = reader.next();
            if (eventType == XMLStreamConstants.START_ELEMENT) {
                QName child = reader.getQName();
                onStartElement(reader, parent, list, map, qName);
                if (child.equals(qName)) {
                    continue;
                }
            }
            if (reader.getEventType() == XMLStreamConstants.END_ELEMENT && qName.equals(reader.getQName())) {
                break;
            }

        }
    }

    public static <T extends AknObject> void read(XmlReader reader,  AknObject parent, AknList<T> list, ImmutableMap<String, Supplier<T>> map, QName parentQname) {
        while (true) {

            if (reader.getEventType() == XMLStreamConstants.START_ELEMENT) {
                onStartElement(reader, parent, list, map, parentQname);
                reader.nextStartOrEndElement();
                continue;
            }

            if (reader.getEventType() == XMLStreamConstants.END_ELEMENT && parentQname.equals(reader.getQName())) {
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
    public static <T extends HasMixedContent> void readWithCharacters(XmlReader reader, AknObject parent, AknList<T> list, ImmutableMap<String, Supplier<T>> map) {
        QName qName = reader.getQName();
        int eventType;
        while (true) {
            if (reader.getEventType() == XMLStreamConstants.END_DOCUMENT) {
                return;
            }

            eventType = reader.next();
            if (eventType == XMLStreamConstants.START_ELEMENT) {
                onStartElement(reader, parent, list, map, qName);
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

    public static <T extends AknObject> void read(XmlReader reader, AknObject parent, AknList<T> list, ImmutableMap<String, Supplier<T>> map, QName parentQname, String otherLocalName) {

        int depth = reader.getDepth() - 1;

        while (true) {
            if (reader.getEventType() == XMLStreamConstants.START_ELEMENT) {

                // This test is because some time (example Hierarchy) element has a brother (wrap in the example).
                if (reader.getQName().equalsLocalName(otherLocalName)) {
                    return;
                }

                onStartElement(reader, parent, list, map, parentQname);
            }

            if (reader.getEventType() == XMLStreamConstants.END_DOCUMENT) {
                return;
            }

            if (reader.getEventType() == XMLStreamConstants.END_ELEMENT && depth == reader.getDepth() && parentQname.equals(reader.getQName())) {
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

    private static <T extends AknObject> void onStartElement(XmlReader reader, AknObject parent, AknList<T> list, ImmutableMap<String, Supplier<T>> map, QName parentQname) {
        Supplier<T> supplier = map.get(reader.getQName().getLocalName());
        if (supplier == null) {
            throw new AknReadException(Type.MISSING_ELEMENT, reader, parentQname);
        }
        T ako = supplier.get();
        ako.setParent(parent);
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
