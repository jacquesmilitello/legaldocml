package io.legaldocml.akn.element;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.group.InlineCM;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.unsafe.UnsafeString.getChars;
import static io.legaldocml.unsafe.UnsafeString.valueOf;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class StringInlineCM implements InlineCM {

    /**
     * The content of this "String" text.
     */
    private final char[] chars;

    private AknObject parent;

    public StringInlineCM(String string) {
        if (string == null) {
            throw new IllegalArgumentException("String item must be not null !!!");
        }
        this.chars = getChars(string);
    }

    public StringInlineCM(char[] chars) {
        this.chars = chars;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return valueOf(this.chars);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        throw new IllegalStateException("StringInlineCM for [name()]");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.write(this.chars);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {
        throw new IllegalStateException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(AknVisitor visitor) {
        visitor.visit(this);
    }

    @SuppressWarnings("unchecked")
    public <T extends AknObject> T getParent() {
        return (T)parent;
    }

    public <T extends AknObject> void setParent(T parent) {
        this.parent = parent;
    }

}