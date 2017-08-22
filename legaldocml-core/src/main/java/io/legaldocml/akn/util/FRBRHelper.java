package io.legaldocml.akn.util;

import io.legaldocml.akn.element.FRBRlanguage;
import io.legaldocml.iso.Language;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class FRBRHelper {

    private FRBRHelper() {
    }

    public static FRBRlanguage newFRBRlanguage(Language language) {
        FRBRlanguage frbr = new FRBRlanguage();
        frbr.setLanguage(language.getCode());
        return frbr;
    }

}
