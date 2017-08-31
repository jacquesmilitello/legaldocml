package io.legaldocml.business.util;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.AkomaNtoso;
import io.legaldocml.akn.DocumentType;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class AknReferenceHelper {

    private AknReferenceHelper() {
    }

    public static <T extends DocumentType> void apply(AkomaNtoso<T> akn, AknObject object, AknReference... refs) {
        if (refs != null) {
            for (AknReference aknReferences : refs) {
                aknReferences.accept(object, akn);
            }
        }
    }
}
