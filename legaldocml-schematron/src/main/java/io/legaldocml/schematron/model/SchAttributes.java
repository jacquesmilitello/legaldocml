package io.legaldocml.schematron.model;

import io.legaldocml.util.CharArray;
import io.legaldocml.io.Externalizable;
import io.legaldocml.io.XmlReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.BiConsumer;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
final class SchAttributes {

    /**
     * SLF4J.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SchAttributes.class);

    private SchAttributes() {
    }

    /**
     * Attribute for {@link io.legaldocml.schematron.model.Schema}
     * Attribute for {@link io.legaldocml.schematron.model.Pattern}
     */
    public static final String ID = "id";

    /**
     * Attribute for {@link io.legaldocml.schematron.model.Rule}
     */
    public static final String CONTEXT = "context";

    /**
     * Attribute for {@link io.legaldocml.schematron.model.Assert}
     */
    public static final String TEST = "test";

    /**
     * Attribute for {@link io.legaldocml.schematron.model.attribute.Linkable}
     */
    public static final String ROLE = "role";

    /**
     * Attribute for {@link io.legaldocml.schematron.model.attribute.Linkable}
     */
    public static final String SUBJECT = "subject";

    /**
     * Attribute for {@link io.legaldocml.schematron.model.attribute.Rich}
     */
    public static final String ICON = "icon";

    /**
     * Attribute for {@link io.legaldocml.schematron.model.attribute.Rich}
     */
    public static final String SEE = "see";

    /**
     * Attribute for {@link io.legaldocml.schematron.model.attribute.Rich}
     */
    public static final String FPI = "fpi";

    /**
     * Attribute for {@link io.legaldocml.schematron.model.Let}
     */
    public static final String NAME = "name";

    /**
     * Attribute for {@link io.legaldocml.schematron.model.Let}
     */
    public static final String VALUE = "value";

    /**
     * Attribute for {@link io.legaldocml.schematron.model.ValueOf}
     */
    public static final String SELECT = "select";

    /**
     * Attribute for {@link io.legaldocml.schematron.model.Name}
     */
    public static final String PATH = "path";

    static void read(XmlReader reader, SchObject schObject) {
        reader.forEach(schObject, (object, name, value, prefixNS) -> {

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("set ({},{},{},{})", object, name, value, prefixNS);
            }

            BiConsumer<Externalizable, CharArray> bic = object.attributes().get(name.toString());

            if (bic != null) {
                bic.accept(object, value);
            } else {
                throw new RuntimeException(schObject.getClass().getSimpleName() + " -> Attribute [" + name + "] not supported ! [" + value + "]");
            }


        });
    }
}
