package io.legaldocml.xliff.attribute;

import io.legaldocml.io.XmlWriter;
import io.legaldocml.model.Language;
import io.legaldocml.xliff.element.XliffObject;

import java.io.IOException;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface SrcLang extends XliffObject {

    Language getSrcLang();

    void setSrcLang(Language srcLang);

    /**
     * {@inheritDoc}
     */
    @Override
    default void write(XmlWriter writer) throws IOException {

    }

}