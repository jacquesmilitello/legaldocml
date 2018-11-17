package io.legaldocml.xliff.attribute;

import io.legaldocml.io.XmlWriter;
import io.legaldocml.xliff.element.XliffObject;

import java.io.IOException;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface Name extends XliffObject {

    String getName();

    void setName(String name);

    /**
     * {@inheritDoc}
     */
    @Override
    default void write(XmlWriter writer) throws IOException {

    }

}