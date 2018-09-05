package io.legaldocml.module;

import io.legaldocml.akn.AknObject;
import io.legaldocml.io.CoreAttribute;
import io.legaldocml.util.CharArray;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;
import java.util.function.Supplier;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface Module {

    CharArray namespace();

    void writeNamespace(XmlWriter writer) throws IOException;

    Supplier<CoreAttribute> attribute(String name);

    <T extends AknObject> Supplier<T> element(String localName);

}
