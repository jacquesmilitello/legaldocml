package io.legaldocml.xliff.element;

import io.legaldocml.io.XmlReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
final class XliffAttributes {

    /**
     * SLF4J.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(XliffAttributes.class);

    private XliffAttributes() {
    }

   /*
            *       <xs:attribute ref="xml:space" use="optional" default="default"/>
    */


    /**
     * CoreAttribute for {@link io.legaldocml.xliff.element.Xliff}
     */
    public static final String VERSION = "version";

    /**
     * CoreAttribute for {@link io.legaldocml.xliff.element.Xliff}
     */
    public static final String SRC_LANG = "srcLang";

    /**
     * CoreAttribute for {@link io.legaldocml.xliff.element.Xliff}
     */
    public static final String TRG_LANG = "trgLang";

    static void read(XmlReader reader, XliffObject xliffObject) {
        reader.forEach(xliffObject, (channelReader, object, name, value, prefixNS) -> {

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("set ({},{},{},{})", object, name, value, prefixNS);
            }

            //BiConsumer<Externalizable, CharArray> bic = object.attributes().get(name.toString());

            //if (bic != null) {
            //    bic.accept(object, value);
            //} else {
            //    throw new RuntimeException(xliffObject.getClass().getSimpleName() + " -> CoreAttribute [" + name + "] not supported ! [" + value + "]");
           // }


        });
    }
}
