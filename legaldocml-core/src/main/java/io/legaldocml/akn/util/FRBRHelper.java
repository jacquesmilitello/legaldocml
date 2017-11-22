package io.legaldocml.akn.util;

import io.legaldocml.akn.element.FRBRauthor;
import io.legaldocml.akn.element.FRBRlanguage;
import io.legaldocml.akn.element.FRBRuri;
import io.legaldocml.model.Language;
import io.legaldocml.akn.type.Uri;

import java.util.function.Function;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class FRBRHelper {

    private FRBRHelper() {
    }

    public static FRBRlanguage newFRBRlanguage(Language language) {
        FRBRlanguage frbr = new FRBRlanguage();
        frbr.setLanguage(language.getTerminology());
        return frbr;
    }

    public static FRBRlanguage newFRBRlanguage(Language language,  Function<Language, String> mapper) {
        FRBRlanguage frbr = new FRBRlanguage();
        frbr.setLanguage(mapper.apply(language));
        return frbr;
    }

    public static FRBRauthor newFRBRauthor(Uri href) {
        FRBRauthor frbr = new FRBRauthor();
        frbr.setHref(href);
        return frbr;
    }

    public static FRBRuri newFRBRuri(String value) {
        FRBRuri frbr = new FRBRuri();
        frbr.setValue(value);
        return frbr;
    }
}
