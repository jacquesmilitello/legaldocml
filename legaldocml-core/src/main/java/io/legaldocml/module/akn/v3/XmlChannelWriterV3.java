package io.legaldocml.module.akn.v3;

import io.legaldocml.io.impl.XmlChannelWriter;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class XmlChannelWriterV3 extends XmlChannelWriter {

    @Override
    public int getVersion() {
        return 3;
    }
}
