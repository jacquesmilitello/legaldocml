package io.legaldocml.module.akn.v2;

import io.legaldocml.io.impl.XmlChannelWriter;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class XmlChannelWriterV2 extends XmlChannelWriter {
    @Override
    public int getVersion() {
        return 2;
    }
}
