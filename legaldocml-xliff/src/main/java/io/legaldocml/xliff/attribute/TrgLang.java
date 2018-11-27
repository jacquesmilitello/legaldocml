package io.legaldocml.xliff.attribute;

import io.legaldocml.io.XmlWriter;
import io.legaldocml.model.Language;
import io.legaldocml.xliff.element.XliffObject;

import java.io.IOException;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface TrgLang extends XliffObject {

    Language getTrgLang();

    void setTrgLang(Language trgLang);

    /**
     * {@inheritDoc}
     */
    @Override
    default void write(XmlWriter writer) throws IOException {

    }

}