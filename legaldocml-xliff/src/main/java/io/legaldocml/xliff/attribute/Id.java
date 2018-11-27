package io.legaldocml.xliff.attribute;

import io.legaldocml.io.XmlWriter;
import io.legaldocml.xliff.element.XliffAttributes;
import io.legaldocml.xliff.element.XliffObject;

import java.io.IOException;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface Id extends XliffObject {

    String getId();

    void setId(String id);

    /**
     * {@inheritDoc}
     */
    @Override
    default void write(XmlWriter writer) throws IOException {
        XliffAttributes.write(writer, this);
    }

}