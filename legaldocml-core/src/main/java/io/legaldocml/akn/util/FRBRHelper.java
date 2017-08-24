package io.legaldocml.akn.util;

import io.legaldocml.akn.element.FRBRauthor;
import io.legaldocml.akn.element.FRBRlanguage;
import io.legaldocml.util.Uri;

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

    public static FRBRauthor newFRBRauthor(Uri href) {
        FRBRauthor frbr = new FRBRauthor();
        frbr.setHref(href);
        return frbr;
    }

}
