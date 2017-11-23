package io.legaldocml.io.impl;

import io.legaldocml.io.Namespaces;
import io.legaldocml.test.LoggerInstancePostProcessor;
import io.legaldocml.util.CharArrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;

import static io.legaldocml.io.impl.XmlChannelReaderHelper.doTest;
import static io.legaldocml.io.impl.XmlChannelReaderHelper.path;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(LoggerInstancePostProcessor.class)
public class XmlChannelReaderNameSpaceTest {

    @Test
    public void test001() throws IOException {
        doTest(path("/xml/namespace-001.xml"), reader -> {
            reader.nextStartOrEndElement();

            // default namespace
            assertEquals("urn:test01", reader.getNamespaces().getDefaultNamespaceUri().toString());
            assertEquals(1, reader.getNamespaces().count());

            // go to first elem => new namespace
            reader.nextStartOrEndElement();
            assertEquals(2, reader.getNamespaces().count());

            // end element ==> ns exists ==> =2
            reader.nextStartOrEndElement();
            assertEquals(2, reader.getNamespaces().count());

            reader.next();

            assertEquals(1, reader.getNamespaces().count());

            // go to second elem => new namespace
            reader.nextStartOrEndElement();
            assertEquals(2, reader.getNamespaces().count());

            // go to end of second element
            reader.nextStartOrEndElement();
            assertEquals(2, reader.getNamespaces().count());

            reader.next();
            assertEquals(1, reader.getNamespaces().count());
        });
    }

    @Test
    public void test002() throws IOException {
        Assertions.assertThrows(XmlChannelReaderException.class, () ->
        doTest(path("/xml/namespace-002.xml"), XmlChannelReader::nextStartOrEndElement));
    }

    @Test
    public void test003() throws IOException {
        doTest(path("/xml/namespace-003.xml"), reader -> {
            reader.nextStartOrEndElement();
            Namespaces ns = reader.getNamespaces();

            assertEquals(4, reader.getNamespaces().count());
            assertEquals("urn:test01", ns.getDefaultNamespaceUri().toString());
            assertEquals("urn:test02", ns.get(CharArrays.immutable("test02")).toString());
            assertEquals("urn:test03", ns.get(CharArrays.immutable("test03")).toString());
            assertEquals("urn:test04", ns.get(CharArrays.immutable("test04")).toString());
        });
    }
}
