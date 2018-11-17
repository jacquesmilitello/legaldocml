package io.legaldocml.xliff.attribute;

import io.legaldocml.io.XmlWriter;
import io.legaldocml.xliff.element.XliffObject;

import java.io.IOException;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface Version extends XliffObject {

    String getVersion();

    void setVersion(String version);

    /**
     * {@inheritDoc}
     */
    @Override
    default void write(XmlWriter writer) throws IOException {

    }

}