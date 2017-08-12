package io.legaldocml.module.xml;

import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.CharArray;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.iso.Language;
import io.legaldocml.module.xml.attribute.XmlLang;

import java.io.IOException;

import static io.legaldocml.unsafe.UnsafeString.getChars;

final class XmlLangImpl implements XmlLang {

    public static final String ATTRIBUTE = "xml:lang";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ATTRIBUTE);

    private Language language;

    //XmlLangImpl(CharArray c) {
    //    this.language = Language.valueOf(c.toString());
    //}

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
        this.language = language;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        if (this.language == null) {

        } else {
            writer.writeAttribute(ADDRESS, 8, getChars(this.language.getCode()));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader, CharArray value) {
        this.language = Language.valueOf(value.toString());
    }
    
}
