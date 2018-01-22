package io.legaldocml.io.impl;

import static io.legaldocml.io.impl.XmlChannelReaderHelper.doTest;
import static io.legaldocml.io.impl.XmlChannelReaderHelper.path;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import javax.xml.stream.XMLStreamConstants;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import io.legaldocml.test.LoggerInstancePostProcessor;

@ExtendWith(LoggerInstancePostProcessor.class)
public class XmlChannelReaderDtdTest {

    @Test
    public void test001() throws IOException {
        doTest(path("/xml/dtd-001.xml"), reader -> {
        	assertEquals(XMLStreamConstants.START_DOCUMENT,reader.getEventType());
        	assertEquals(XMLStreamConstants.DTD,reader.next());
        	// TODO => handle dtd.
        	assertEquals(XMLStreamConstants.START_ELEMENT,reader.next());
        	assertEquals(XMLStreamConstants.CHARACTERS,reader.next());
        	assertEquals("hello",reader.getText().toString());
        	assertEquals(XMLStreamConstants.END_ELEMENT,reader.next());
        	assertEquals(XMLStreamConstants.END_DOCUMENT,reader.next());
        });
    }

   
}
