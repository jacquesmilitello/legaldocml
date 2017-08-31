package io.legaldocml.schematron.model;

import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

public final class Text implements SchMixedContent {

    private char[] text;

    public Text(char[] text) {
        this.text = text;
    }


    @Override
    public void write(XmlWriter writer) throws IOException {

    }

    @Override
    public void read(XmlReader reader) {

    }
}
