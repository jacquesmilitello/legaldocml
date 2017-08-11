package io.legaldocml.util;

import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.XmlWriter;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public class XmlWriterVisitor implements AknVisitor {

    private final XmlWriter writer;

    public XmlWriterVisitor(XmlWriter writer) {
        this.writer = writer;
    }

}
