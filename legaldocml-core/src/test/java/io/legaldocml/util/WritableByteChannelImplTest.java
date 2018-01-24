package io.legaldocml.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ClosedChannelException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.legaldocml.akn.AkomaNtoso;
import io.legaldocml.akn.element.Portion;
import io.legaldocml.io.XmlProvider;
import io.legaldocml.io.XmlWriterFactoryProvider;
import io.legaldocml.test.PathForTest;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public class WritableByteChannelImplTest {

    @Test
    public void testSimpleWrite() throws IOException {
        AkomaNtoso<Portion> akn = XmlProvider.readerFactory().read(PathForTest.path("/xml/v3/us_Title9-Chap3-eng.xml"));
        WritableByteChannelImpl wbc = new  WritableByteChannelImpl(256,256);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        XmlWriterFactoryProvider.xmlWriterFactory(1).write(wbc, akn);
        XmlWriterFactoryProvider.xmlWriterFactory(1).write(Channels.newChannel(baos), akn);

        Assertions.assertArrayEquals(baos.toByteArray(), wbc.toByteArray());
    }

    @Test
    public void testClosedChannelException() throws IOException {
        WritableByteChannelImpl wbc = new  WritableByteChannelImpl(256,256);
        wbc.close();
        Assertions.assertThrows(ClosedChannelException.class, () -> wbc.write(ByteBuffer.allocate(2)));
    }
}
