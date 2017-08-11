package io.legaldocml.io.impl;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import static io.legaldocml.io.impl.XmlChannelReaderHelper.doTest;
import static io.legaldocml.io.impl.XmlChannelReaderHelper.path;

public class XmlChannelReaderCDataTest {

    @Test
    public void test001Element() throws IOException {
        doTest(path("/xml/cdata-001.xml"), reader -> {
            reader.nextStartOrEndElement();
            Assert.assertEquals("test", reader.getQName().getLocalName());
            reader.next();
            Assert.assertEquals("hello", reader.getText().toString());
            reader.next();
            Assert.assertEquals("test", reader.getQName().getLocalName());
        });
    }

    @Test
    public void test002Element() throws IOException {
        doTest(path("/xml/cdata-002.xml"), reader -> {
            reader.nextStartOrEndElement();
            Assert.assertEquals("test", reader.getQName().getLocalName());
            reader.next(); //-> CHARACTERS
            reader.next(); //-> START_ELEMENT
            Assert.assertEquals("el", reader.getQName().getLocalName());
            reader.next(); //-> CHARACTERS
            Assert.assertEquals("hello-001", reader.getText().toString());
            reader.next(); //-> END_ELEMENT
            Assert.assertEquals("el", reader.getQName().getLocalName());
            reader.next(); //-> CHARACTERS
            reader.next(); //-> START_ELEMENT
            Assert.assertEquals("el", reader.getQName().getLocalName());
            reader.next(); //-> CHARACTERS
            Assert.assertEquals("hello-002", reader.getText().toString());
            reader.next(); //-> END_ELEMENT
            Assert.assertEquals("el", reader.getQName().getLocalName());
            reader.next(); //-> END_ELEMENT
            Assert.assertEquals("test", reader.getQName().getLocalName());
        });
    }

    @Test
    public void test003BadEndCdata() throws IOException {
        doTest(path("/xml/cdata-003.xml"), reader -> {
            reader.nextStartOrEndElement();
            Assert.assertEquals("test", reader.getQName().getLocalName());
            try {
                reader.next();
                Assert.fail();
            } catch (XmlChannelReaderException cause) {
                Assert.assertEquals(XmlChannelReaderException.Type.PREMATURE_END_OF_FILE, cause.getType());

                cause.printStackTrace();
            }
        });
    }
}
