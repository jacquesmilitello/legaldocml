package io.legaldocml.akn.util;

import io.legaldocml.akn.element.FRBRlanguage;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class FRBRHelper {

    private FRBRHelper() {
    }

    public static FRBRlanguage newFRBRlanguage(String code) {
        FRBRlanguage frbr = new FRBRlanguage();
        frbr.setLanguage(code);
        return frbr;
    }

}
