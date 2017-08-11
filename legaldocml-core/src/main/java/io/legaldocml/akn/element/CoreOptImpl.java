package io.legaldocml.akn.element;

import io.legaldocml.akn.attribute.CoreOpt;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.util.XmlWriterHelper.writeAlt;
import static io.legaldocml.akn.util.XmlWriterHelper.writeEnactment;
import static io.legaldocml.akn.util.XmlWriterHelper.writeHTMLattrs;
import static io.legaldocml.akn.util.XmlWriterHelper.writeIdOpt;
import static io.legaldocml.akn.util.XmlWriterHelper.writeRefers;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class CoreOptImpl extends AbstractCore implements CoreOpt {

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        super.write(writer);
        writeIdOpt(writer, this);
        writeRefers(writer, this);
        writeEnactment(writer, this);
        writeHTMLattrs(writer, this);
        writeAlt(writer, this);
    }
}