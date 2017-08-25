package io.legaldocml.io.impl;

import io.legaldocml.io.CharArrays;
import io.legaldocml.io.Namespaces;
import io.legaldocml.test.SonarJUnit4ClassRunner;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import java.io.IOException;

import static io.legaldocml.io.impl.XmlChannelReaderHelper.doTest;
import static io.legaldocml.io.impl.XmlChannelReaderHelper.path;

@RunWith(SonarJUnit4ClassRunner.class)
public class XmlChannelReaderNameSpaceTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

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

    @Test
    public void test002() throws IOException {
        thrown.expect(XmlChannelReaderException.class);
        thrown.expectMessage("NAMESPACE_ERROR");
        doTest(path("/xml/namespace-002.xml"), XmlChannelReader::nextStartOrEndElement);
    }

    @Test
    public void test003() throws IOException {
        doTest(path("/xml/namespace-003.xml"), reader -> {
            reader.nextStartOrEndElement();
            Namespaces ns = reader.getNamespaces();

            Assert.assertEquals(4, reader.getNamespaces().count());
            Assert.assertEquals("urn:test01", ns.getDefaultNamespaceUri().toString());
            Assert.assertEquals("urn:test02", ns.get(CharArrays.constant("test02")).toString());
            Assert.assertEquals("urn:test03", ns.get(CharArrays.constant("test03")).toString());
            Assert.assertEquals("urn:test04", ns.get(CharArrays.constant("test04")).toString());
        });
    }
}
