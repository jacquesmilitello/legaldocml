package io.legaldocml.io.impl;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import static io.legaldocml.io.impl.XmlChannelReaderHelper.doTest;
import static io.legaldocml.io.impl.XmlChannelReaderHelper.path;

public class XmlChannelReaderNameSpaceTest {


    @Test
    public void test001() throws IOException {
        doTest(path("/xml/namespace-001.xml"), reader -> {
            reader.nextStartOrEndElement();

            // default namespace
            Assert.assertEquals("urn:test01", reader.getNamespaces().getDefaultNamespaceUri().toString());
            Assert.assertEquals(1, reader.getNamespaces().count());

            // go to first elem => new namespace
            reader.nextStartOrEndElement();
            Assert.assertEquals(2, reader.getNamespaces().count());

            // end element ==> ns exists ==> =2
            reader.nextStartOrEndElement();
            Assert.assertEquals(2, reader.getNamespaces().count());

            reader.next();

            Assert.assertEquals(1, reader.getNamespaces().count());

            // go to second elem => new namespace
            reader.nextStartOrEndElement();
            Assert.assertEquals(2, reader.getNamespaces().count());

            // go to end of second element
            reader.nextStartOrEndElement();
            Assert.assertEquals(2, reader.getNamespaces().count());

            reader.next();
            Assert.assertEquals(1, reader.getNamespaces().count());
        });
    }

}
