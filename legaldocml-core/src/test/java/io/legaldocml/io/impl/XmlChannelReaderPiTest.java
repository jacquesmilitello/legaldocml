package io.legaldocml.io.impl;

import io.legaldocml.io.ProcessingInstruction;
import org.junit.Assert;
import org.junit.Test;

import javax.xml.stream.XMLStreamConstants;
import java.io.IOException;

import static io.legaldocml.io.impl.XmlChannelReaderHelper.doTest;
import static io.legaldocml.io.impl.XmlChannelReaderHelper.path;

public class XmlChannelReaderPiTest {


    @Test
    public void test001Prolog() throws IOException {
        doTest(path("/xml/pi-001.xml"), reader -> {
            reader.nextStartOrEndElement();
            ProcessingInstruction instruction = reader.getProlog();
            Assert.assertNotNull(reader.getProlog());
            Assert.assertEquals("xml", instruction.getTarget());
            Assert.assertEquals("version=\"1.0\" encoding=\"UTF-8\"", instruction.getInstruction());
        });
    }

    @Test
    public void test002Header() throws IOException {

        doTest(path("/xml/pi-002.xml"), reader -> {
            while (reader.next() != XMLStreamConstants.PROCESSING_INSTRUCTION) ;
            Assert.assertEquals("target", reader.getPIData().getTarget());
            Assert.assertNull(reader.getPIData().getInstruction());
            Assert.assertNull(reader.getProlog());

            reader.next(); //-> CHARACTERS
            reader.next(); //-> PI
            Assert.assertEquals("target", reader.getPIData().getTarget());
            Assert.assertEquals("instruction", reader.getPIData().getInstruction());
            Assert.assertNull(reader.getProlog());

            reader.next(); //-> CHARACTERS
            reader.next(); //-> PI
            Assert.assertEquals("target", reader.getPIData().getTarget());
            Assert.assertEquals("instruction instruction2", reader.getPIData().getInstruction());
            Assert.assertNull(reader.getProlog());
        });

    }

    @Test
    public void test003PrologAndHeader() throws IOException {

        doTest(path("/xml/pi-003.xml"), reader -> {
            while (reader.next() != XMLStreamConstants.PROCESSING_INSTRUCTION) ;
            Assert.assertEquals("target", reader.getPIData().getTarget());
            Assert.assertNull(reader.getPIData().getInstruction());

            reader.next(); //-> CHARACTERS
            reader.next(); //-> PI
            Assert.assertEquals("target", reader.getPIData().getTarget());
            Assert.assertEquals("instruction", reader.getPIData().getInstruction());

            reader.next(); //-> CHARACTERS
            reader.next(); //-> PI
            Assert.assertEquals("target", reader.getPIData().getTarget());
            Assert.assertEquals("instruction instruction2", reader.getPIData().getInstruction());

            ProcessingInstruction instruction = reader.getProlog();
            Assert.assertEquals("xml", instruction.getTarget());
            Assert.assertEquals("version=\"1.0\" encoding=\"UTF-8\"", instruction.getInstruction());
        });

    }

    @Test
    public void test004PiElement() throws IOException {

        doTest(path("/xml/pi-004.xml"), reader -> {
            while (reader.next() != XMLStreamConstants.PROCESSING_INSTRUCTION) ;
            Assert.assertEquals("target", reader.getPIData().getTarget());
            Assert.assertNull(reader.getPIData().getInstruction());

            reader.next(); //-> CHARACTERS
            reader.next(); //-> PI
            Assert.assertEquals("target", reader.getPIData().getTarget());
            Assert.assertEquals("instruction", reader.getPIData().getInstruction());

            reader.next(); //-> CHARACTERS
            reader.next(); //-> PI
            Assert.assertEquals("target", reader.getPIData().getTarget());
            Assert.assertEquals("instruction instruction2", reader.getPIData().getInstruction());

            reader.next(); //-> CHARACTERS
            reader.next(); //-> START_ELEMENT
            Assert.assertEquals("elem01", reader.getQName().getLocalName());

        });

    }


}
