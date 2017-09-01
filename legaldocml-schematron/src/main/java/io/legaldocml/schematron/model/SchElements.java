package io.legaldocml.schematron.model;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.util.ExternalizableList;
import io.legaldocml.io.QName;
import io.legaldocml.io.XmlReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.function.Supplier;

import static javax.xml.stream.XMLStreamConstants.CHARACTERS;
import static javax.xml.stream.XMLStreamConstants.END_ELEMENT;
import static javax.xml.stream.XMLStreamConstants.PROCESSING_INSTRUCTION;
import static javax.xml.stream.XMLStreamConstants.START_ELEMENT;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class SchElements {

    /**
     * SLF4J Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SchElements.class);

    private static final ImmutableMap<String, Supplier<SchMixedContent>> SCH_MIXED_CONTENT;

    static {
        SCH_MIXED_CONTENT = ImmutableMap.<String, Supplier<SchMixedContent>>builder()
                .put(SchElements.VALUE_OF, ValueOf::new)
                .put(SchElements.NAME, Name::new)
                .build();
    }

    private SchElements() {
    }

    /**
     * Element for {@link io.legaldocml.schematron.model.Schema}
     */
    public static final String SCHEMA = "schema";

    /**
     * Element for {@link io.legaldocml.schematron.model.Pattern}
     */
    public static final String PATTERN = "pattern";

    /**
     * Element for {@link io.legaldocml.schematron.model.Rule}
     */
    public static final String RULE = "rule";

    /**
     * Element for {@link io.legaldocml.schematron.model.Assert}
     */
    public static final String ASSERT = "assert";

    /**
     * Element for {@link io.legaldocml.schematron.model.Report}
     */
    public static final String REPORT = "report";

    /**
     * Element for {@link io.legaldocml.schematron.model.Let}
     */
    public static final String LET = "let";

    /**
     * Element for {@link io.legaldocml.schematron.model.Name}
     */
    public static final String NAME = "name";

    /**
     * Element for {@link io.legaldocml.schematron.model.ValueOf}
     */
    public static final String VALUE_OF = "value-of";


    public static <T extends SchObject> void read(XmlReader reader, ExternalizableList<T> list, ImmutableMap<String, Supplier<T>> map, QName parent) {
        while (true) {
            reader.next();

            switch (reader.getEventType()) {
                case START_ELEMENT:
                    Supplier<T> sup = map.get(reader.getQName().getLocalName());
                    if (sup == null) {
                        throw new SchReadException(SchReadException.Type.MISSING_ELEMENT, reader, reader.getQName(), parent);
                    }
                    T elem = sup.get();
                    elem.read(reader);
                    list.add(elem);
                    break;

                case PROCESSING_INSTRUCTION:

                    break;
                case END_ELEMENT:
                    if (parent.equals(reader.getQName())) {
                        return;
                    }
                    break;
            }
        }

    }

    public static void read(XmlReader reader, QName qName, List<SchMixedContent> list) {
        while (true) {
            reader.next();

            switch (reader.getEventType()) {
                case START_ELEMENT:
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug("START ELEMENT : [{}]", reader.getQName());
                    }

                    Supplier<SchMixedContent> supplier = SCH_MIXED_CONTENT.get(reader.getQName().getLocalName());

                    if (supplier == null) {
                        throw new SchReadException(SchReadException.Type.MISSING_ELEMENT, reader, reader.getQName(), qName);
                    }

                    SchMixedContent mixedContent = supplier.get();
                    mixedContent.read(reader);
                    list.add(mixedContent);
                    break;

                case CHARACTERS: {
                    char[] content = reader.getText().value();
                    if (content != null && content.length > 0) {
                        list.add(new Text(content));
                    }
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug("CHARACTERS : [{}]", reader.getText());
                    }
                    break;
                }
                case PROCESSING_INSTRUCTION:


                    break;
                case END_ELEMENT:
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug("END_ELEMENT : [{}]", reader.getQName());
                    }
                    if (qName.equals(reader.getQName())) {
                        return;
                    }

                    break;
            }
        }

    }
}
