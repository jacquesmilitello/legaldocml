package io.legaldocml.schematron.model;

import io.legaldocml.io.XmlReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
     * CoreAttribute for {@link io.legaldocml.schematron.model.Schema}
     * CoreAttribute for {@link io.legaldocml.schematron.model.Pattern}
     */
    public static final String ID = "id";

    /**
     * CoreAttribute for {@link io.legaldocml.schematron.model.Rule}
     */
    public static final String CONTEXT = "context";

    /**
     * CoreAttribute for {@link io.legaldocml.schematron.model.Assert}
     */
    public static final String TEST = "test";

    /**
     * CoreAttribute for {@link io.legaldocml.schematron.model.attribute.Linkable}
     */
    public static final String ROLE = "role";

    /**
     * CoreAttribute for {@link io.legaldocml.schematron.model.attribute.Linkable}
     */
    public static final String SUBJECT = "subject";

    /**
     * CoreAttribute for {@link io.legaldocml.schematron.model.attribute.Rich}
     */
    public static final String ICON = "icon";

    /**
     * CoreAttribute for {@link io.legaldocml.schematron.model.attribute.Rich}
     */
    public static final String SEE = "see";

    /**
     * CoreAttribute for {@link io.legaldocml.schematron.model.attribute.Rich}
     */
    public static final String FPI = "fpi";

    /**
     * CoreAttribute for {@link io.legaldocml.schematron.model.Let}
     */
    public static final String NAME = "name";

    /**
     * CoreAttribute for {@link io.legaldocml.schematron.model.Let}
     */
    public static final String VALUE = "value";

    /**
     * CoreAttribute for {@link io.legaldocml.schematron.model.ValueOf}
     */
    public static final String SELECT = "select";

    /**
     * CoreAttribute for {@link io.legaldocml.schematron.model.Name}
     */
    public static final String PATH = "path";

    static void read(XmlReader reader, SchObject schObject) {
        reader.forEach(schObject, (channelReader, object, name, value, prefixNS) -> {

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("set ({},{},{},{})", object, name, value, prefixNS);
            }

           // AknAttributeGetterSetter bic = object.attributes().get(name.toString());

           // if (bic != null) {
           //     bic.accept(object, value);
           // } else {
           //     throw new RuntimeException(schObject.getClass().getSimpleName() + " -> CoreAttribute [" + name + "] not supported ! [" + value + "]");
           // }


        });
    }
}
