package io.legaldocml.io.impl;

import io.legaldocml.test.LoggerInstancePostProcessor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;

import static io.legaldocml.io.impl.XmlChannelReaderHelper.doTest;
import static io.legaldocml.io.impl.XmlChannelReaderHelper.path;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(LoggerInstancePostProcessor.class)
class XmlChannelReaderTest {

    @Test
    void testQName() throws IOException {
        doTest(path("/xml/cdata-002.xml"), reader -> {
            XmlChannelReaderException ex = assertThrows(XmlChannelReaderException.class, reader::getQName);
            Assertions.assertEquals(XmlChannelReaderException.Type.INVALID_EVENT_TYPE_QNAME, ex.getType());
        });
    }

    @Test
    void testText() throws IOException {
        doTest(path("/xml/cdata-002.xml"), reader -> {
            XmlChannelReaderException ex = assertThrows(XmlChannelReaderException.class, reader::getText);
            Assertions.assertEquals(XmlChannelReaderException.Type.INVALID_EVENT_TYPE_TEXT, ex.getType());
        });
    }

    @Test
    void testPI() throws IOException {
        doTest(path("/xml/cdata-002.xml"), reader -> {
            XmlChannelReaderException ex = assertThrows(XmlChannelReaderException.class, reader::getPIData);
            Assertions.assertEquals(XmlChannelReaderException.Type.INVALID_EVENT_TYPE_PI, ex.getType());
        });
    }
}
