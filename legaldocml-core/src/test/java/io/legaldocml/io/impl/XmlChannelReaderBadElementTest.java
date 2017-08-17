package io.legaldocml.io.impl;

import io.legaldocml.akn.AkomaNtoso;
import io.legaldocml.akn.MandatoryElementException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import static io.legaldocml.io.impl.XmlChannelReaderHelper.doTest;
import static io.legaldocml.io.impl.XmlChannelReaderHelper.path;
import static org.junit.Assert.assertEquals;

public class XmlChannelReaderBadElementTest {

    @Test
    public void test001BadAkoElement() throws IOException {
        doTest(path("/xml/cdata-001.xml"), reader -> {
            reader.nextStartOrEndElement();
            AkomaNtoso akomaNtoso = new AkomaNtoso();
            try {
                akomaNtoso.read(reader);
                Assert.fail();
            } catch (MandatoryElementException cause) {
                assertEquals(AkomaNtoso.ELEMENT, cause.getExpected());
                assertEquals(new QNameImpl("test".toCharArray(),4,0), cause.getActual());
            }
        });
    }
}
