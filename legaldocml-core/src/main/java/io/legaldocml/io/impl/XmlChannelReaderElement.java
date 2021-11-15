package io.legaldocml.io.impl;

import io.legaldocml.akn.AknObject;
import io.legaldocml.io.XmlReaderContext;
import io.legaldocml.module.AknModule;
import io.legaldocml.module.akn.v3.AkomaNtosoModuleV3;
import io.legaldocml.util.CharArray;
import io.legaldocml.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class XmlChannelReaderElement {

    /**
     * SLF4J Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(XmlChannelReaderElement.class);

    private XmlChannelReaderElement() {
    }

    public static <T extends AknObject> void read(T akn, byte[] bytes) {
        XmlChannelReader reader = createXmlChannelReader(bytes);

        if (!Strings.isEmpty(reader.getQName().getPrefix())) {
            throw new XmlChannelReaderElementException("Root element with Prefix [" + reader.getQName().getPrefix() + "] -> use method with AknModule");
        }

        if (!akn.name().equals(reader.getQName().getLocalName())) {
            throw new XmlChannelReaderElementException("Expected [" + akn.name() + "], found [" + reader.getQName() + "]");
        }

        read(reader, akn, bytes);
    }


    public static <T extends AknObject> void read(CharArray namespace, T akn, byte[] bytes) {
        XmlChannelReader reader = createXmlChannelReader(bytes);

        if (Strings.isEmpty(reader.getQName().getPrefix())) {
            if (!akn.name().equals(reader.getQName().getLocalName())) {
                throw new XmlChannelReaderElementException("Expected [" + akn.name() + "], found [" + reader.getQName() + "]");
            }
        }

        read(reader, akn, bytes);
    }

    private static <T extends AknObject> void read(XmlChannelReader reader, T akn, byte[] bytes) {

        reader.setContext(new XmlReaderContext() {
            /**
             * {@inheritDoc}
             */
            @Override
            public void update(String name, AknObject akn) {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("update({},{})", name, akn);
                }
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public AknModule getAknModule() {
                return AkomaNtosoModuleV3.INSTANCE;
            }
        });

        akn.read(reader);
    }

    private static XmlChannelReader createXmlChannelReader(byte[] bytes) {
        XmlChannelReader reader = new XmlChannelReader();
        // noinspection RedundantCast
        reader.setBuffer((MappedByteBuffer) ((java.nio.Buffer) ByteBuffer.allocateDirect(bytes.length).put(bytes)).flip());
        reader.nextStartOrEndElement();
        return reader;
    }

    public static class XmlChannelReaderElementException extends RuntimeException {

        XmlChannelReaderElementException(String message) {
            super(message);
        }
    }
}
