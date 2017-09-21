package io.legaldocml.module.xml;

import io.legaldocml.akn.AkomaNtosoContext;
import io.legaldocml.util.CharArray;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.model.Language;
import io.legaldocml.model.ModelProvider;
import io.legaldocml.module.xml.attribute.XmlLang;

import java.io.IOException;

import static io.legaldocml.unsafe.UnsafeString.getChars;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
final class XmlLangImpl implements XmlLang {

    public static final String ATTRIBUTE = "xml:lang";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ATTRIBUTE);

    private Language language;

    /**
     * {@inheritDoc}
     */
    @Override
    public Language getXmlLang() {
        return language;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setXmlLang(Language xmlLang) {
        this.language = xmlLang;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        if (this.language != null) {
            writer.writeAttribute(ADDRESS, 8, getChars(this.language.getCode()));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader, CharArray value) {
        ModelProvider provider = reader.<AkomaNtosoContext>getContext().getModelProvider();
        this.language = provider.language(value.toString());
    }
    
}
