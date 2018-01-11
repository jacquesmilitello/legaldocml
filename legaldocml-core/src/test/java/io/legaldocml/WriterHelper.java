package io.legaldocml;

import io.legaldocml.io.Externalizable;
import io.legaldocml.module.akn.v3.XmlChannelWriterV3;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.channels.Channels;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public class WriterHelper {

    private WriterHelper() {
    }

    public static String write(Externalizable externalizable) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        XmlChannelWriterV3 writer = new XmlChannelWriterV3();
        writer.setChannel(Channels.newChannel(baos));
        try {
            externalizable.write(writer);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(baos.toByteArray());
    }

}
