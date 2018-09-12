package io.legaldocml.akn.type;

import io.legaldocml.io.XmlWriter;

import java.io.IOException;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface ListReferenceRef {

    void write(XmlWriter writer) throws IOException;

    int size();

    ReferenceRef get(int i);

    void add(ReferenceRef referenceRef);
}
