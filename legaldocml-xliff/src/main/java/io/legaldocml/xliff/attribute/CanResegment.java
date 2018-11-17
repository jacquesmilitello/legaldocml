package io.legaldocml.xliff.attribute;

import io.legaldocml.io.XmlWriter;
import io.legaldocml.xliff.element.XliffObject;
import io.legaldocml.xliff.type.YesNo;

import java.io.IOException;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface CanResegment extends XliffObject {

    YesNo getCanResegment();

    void setCanResegment(YesNo canResegment);

    /**
     * {@inheritDoc}
     */
    @Override
    default void write(XmlWriter writer) throws IOException {

    }

}