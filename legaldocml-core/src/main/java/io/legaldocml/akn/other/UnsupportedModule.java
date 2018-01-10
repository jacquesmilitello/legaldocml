package io.legaldocml.akn.other;

import io.legaldocml.akn.AknObject;
import io.legaldocml.io.Attribute;
import io.legaldocml.util.CharArray;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.util.Buffers;
import io.legaldocml.module.Module;

import java.io.IOException;
import java.util.function.Supplier;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class UnsupportedModule implements Module {

    private final String prefix;
    private final CharArray uri;

    public UnsupportedModule(CharArray prefix, CharArray uri) {
        this.prefix = prefix.toString();
        this.uri = uri;
    }


    @Override
    public CharArray namespace() {
        return this.uri;
    }

    @Override
    public void writeNamespace(XmlWriter writer) throws IOException {
        StringBuilder prefix = new StringBuilder()
                .append("xmlns:")
                .append(this.prefix);
        writer.writeNamespace(Buffers.address(prefix.toString()), prefix.length(), Buffers.address(uri.toString()), uri.length());
    }

    @Override
    public Supplier<Attribute> attributes(String name) {
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> Supplier<T> element(String localName, Class<T> clazz) {
        return () -> (T) new SimpleExternalElement(prefix, localName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<? extends AknObject> getAknClass(String localName) {
        // TODO....
        return null;
    }
}
