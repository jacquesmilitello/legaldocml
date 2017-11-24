package io.legaldocml.business.util;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.AkomaNtoso;
import io.legaldocml.akn.DocumentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class AknReferences {

    private static final Logger LOGGER = LoggerFactory.getLogger(AknReferences.class);

    private AknReferences() {
    }

    public static <T extends DocumentType> void apply(AkomaNtoso<T> akn, AknObject object, AknReference... refs) {
        if (refs != null) {
            for (AknReference ref : refs) {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("accept [{}] to [{}]", ref, object);
                }
                ref.accept(object, akn);
            }
        }
    }
}
