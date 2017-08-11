package io.legaldocml.akn.element;

import io.legaldocml.akn.attribute.IdOpt;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.util.XmlWriterHelper.writeIdOpt;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class IdOptImpl extends AbstractId implements IdOpt {

    @Override
    public void write(XmlWriter writer) throws IOException {
        writeIdOpt(writer, this);
    }

}