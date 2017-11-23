package io.legaldocml.io.impl;

import io.legaldocml.io.ProcessingInstruction;
import io.legaldocml.test.LoggerInstancePostProcessor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.xml.stream.XMLStreamConstants;
import java.io.IOException;

import static io.legaldocml.io.impl.XmlChannelReaderHelper.doTest;
import static io.legaldocml.io.impl.XmlChannelReaderHelper.path;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(LoggerInstancePostProcessor.class)
public class XmlChannelReaderPiTest {


    @Test
    public void test001Prolog() throws IOException {
        doTest(path("/xml/pi-001.xml"), reader -> {
            reader.nextStartOrEndElement();
            ProcessingInstruction instruction = reader.getProlog();
            Assertions.assertNotNull(reader.getProlog());
            assertEquals("xml", instruction.getTarget());
            assertEquals("version=\"1.0\" encoding=\"UTF-8\"", instruction.getInstruction());
        });
    }

    @Test
    public void test002Header() throws IOException {

        doTest(path("/xml/pi-002.xml"), reader -> {
            while (reader.next() != XMLStreamConstants.PROCESSING_INSTRUCTION) ;
            assertEquals("target", reader.getPIData().getTarget());
            assertNull(reader.getPIData().getInstruction());
            assertNull(reader.getProlog());

            reader.next(); //-> CHARACTERS
            reader.next(); //-> PI
            assertEquals("target", reader.getPIData().getTarget());
            assertEquals("instruction", reader.getPIData().getInstruction());
            assertNull(reader.getProlog());

            reader.next(); //-> CHARACTERS
            reader.next(); //-> PI
            assertEquals("target", reader.getPIData().getTarget());
            assertEquals("instruction instruction2", reader.getPIData().getInstruction());
            assertNull(reader.getProlog());
        });

    }

    @Test
    public void test003PrologAndHeader() throws IOException {

        doTest(path("/xml/pi-003.xml"), reader -> {
            while (reader.next() != XMLStreamConstants.PROCESSING_INSTRUCTION) ;
            assertEquals("target", reader.getPIData().getTarget());
            assertNull(reader.getPIData().getInstruction());

            reader.next(); //-> CHARACTERS
            reader.next(); //-> PI
            assertEquals("target", reader.getPIData().getTarget());
            assertEquals("instruction", reader.getPIData().getInstruction());

            reader.next(); //-> CHARACTERS
            reader.next(); //-> PI
            assertEquals("target", reader.getPIData().getTarget());
            assertEquals("instruction instruction2", reader.getPIData().getInstruction());

            ProcessingInstruction instruction = reader.getProlog();
            assertEquals("xml", instruction.getTarget());
            assertEquals("version=\"1.0\" encoding=\"UTF-8\"", instruction.getInstruction());
        });

    }

    @Test
    public void test004PiElement() throws IOException {

        doTest(path("/xml/pi-004.xml"), reader -> {
            while (reader.next() != XMLStreamConstants.PROCESSING_INSTRUCTION) ;
            assertEquals("target", reader.getPIData().getTarget());
            assertNull(reader.getPIData().getInstruction());

            reader.next(); //-> CHARACTERS
            reader.next(); //-> PI
            assertEquals("target", reader.getPIData().getTarget());
            assertEquals("instruction", reader.getPIData().getInstruction());

            reader.next(); //-> CHARACTERS
            reader.next(); //-> PI
            assertEquals("target", reader.getPIData().getTarget());
            assertEquals("instruction instruction2", reader.getPIData().getInstruction());

            reader.next(); //-> CHARACTERS
            reader.next(); //-> START_ELEMENT
            assertEquals("elem01", reader.getQName().getLocalName());

        });

    }


}
