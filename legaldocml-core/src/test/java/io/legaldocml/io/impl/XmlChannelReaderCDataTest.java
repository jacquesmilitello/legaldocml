package io.legaldocml.io.impl;

import io.legaldocml.test.LoggerInstancePostProcessor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;

import static io.legaldocml.io.impl.XmlChannelReaderHelper.doTest;
import static io.legaldocml.io.impl.XmlChannelReaderHelper.path;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@ExtendWith(LoggerInstancePostProcessor.class)
public class XmlChannelReaderCDataTest {

    @Test
    public void test001Element() throws IOException {
        doTest(path("/xml/cdata-001.xml"), reader -> {
            reader.nextStartOrEndElement();
            assertEquals("test", reader.getQName().getLocalName());
            reader.next();
            assertEquals("hello", reader.getText().toString());
            reader.next();
            assertEquals("test", reader.getQName().getLocalName());
        });
    }

    @Test
    public void test002Element() throws IOException {
        doTest(path("/xml/cdata-002.xml"), reader -> {
            reader.nextStartOrEndElement();
            assertEquals("test", reader.getQName().getLocalName());
            reader.next(); //-> CHARACTERS
            reader.next(); //-> START_ELEMENT
            assertEquals("el", reader.getQName().getLocalName());
            reader.next(); //-> CHARACTERS
            assertEquals("hello-001", reader.getText().toString());
            reader.next(); //-> END_ELEMENT
            assertEquals("el", reader.getQName().getLocalName());
            reader.next(); //-> CHARACTERS
            reader.next(); //-> START_ELEMENT
            assertEquals("el", reader.getQName().getLocalName());
            reader.next(); //-> CHARACTERS
            assertEquals("hello-002", reader.getText().toString());
            reader.next(); //-> END_ELEMENT
            assertEquals("el", reader.getQName().getLocalName());
            reader.next(); //-> END_ELEMENT
            assertEquals("test", reader.getQName().getLocalName());
        });
    }

    @Test
    public void test003BadEndCdata() throws IOException {
        doTest(path("/xml/cdata-003.xml"), reader -> {
            reader.nextStartOrEndElement();
            assertEquals("test", reader.getQName().getLocalName());
            try {
                reader.next();
                fail("");
            } catch (XmlChannelReaderException cause) {
                assertEquals(XmlChannelReaderException.Type.PREMATURE_END_OF_FILE, cause.getType());

                cause.printStackTrace();
            }
        });
    }

    @Test
    public void test004Element() throws IOException {
        doTest(path("/xml/cdata-004.xml"), reader -> {
            reader.nextStartOrEndElement();
            assertEquals("test", reader.getQName().getLocalName());
            reader.next(); //-> CHARACTERS
            reader.next(); //-> START_ELEMENT
            assertEquals("el", reader.getQName().getLocalName());
            reader.next(); //-> CHARACTERS
            assertEquals("hello-001]test", reader.getText().toString());
            reader.next(); //-> END_ELEMENT
            assertEquals("el", reader.getQName().getLocalName());
            reader.next(); //-> CHARACTERS
            reader.next(); //-> START_ELEMENT
            assertEquals("el", reader.getQName().getLocalName());
            reader.next(); //-> CHARACTERS
            assertEquals("hello-002]]test", reader.getText().toString());
            reader.next(); //-> END_ELEMENT
            assertEquals("el", reader.getQName().getLocalName());
            reader.next(); //-> END_ELEMENT
            assertEquals("test", reader.getQName().getLocalName());
        });
    }
}
