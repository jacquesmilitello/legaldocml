package io.legaldocml.xliff.element;

import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

public final class StringMixedContent implements MixedContent {

    private final char[] content;

    public StringMixedContent(char[] content) {
        this.content = content;
    }

    @Override
    public void write(XmlWriter writer) throws IOException {

    }

    @Override
    public void read(XmlReader reader) {

    }
}
