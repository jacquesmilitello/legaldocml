package io.legaldocml.io.impl;

import io.legaldocml.akn.MandatoryElementException;
import io.legaldocml.akn.util.XmlReaderHelper;
import io.legaldocml.test.LoggerInstancePostProcessor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.AKOMANTOSO;
import static io.legaldocml.io.impl.XmlChannelReaderHelper.doTest;
import static io.legaldocml.io.impl.XmlChannelReaderHelper.path;

@ExtendWith(LoggerInstancePostProcessor.class)
public class XmlChannelReaderBadElementTest {

    @Test
    public void test001BadAkoElement() throws IOException {
        doTest(path("/xml/cdata-001.xml"), reader -> {
            reader.nextStartOrEndElement();
            try {
                XmlReaderHelper.createAkomaNtoso(reader);
                Assertions.fail("");
            } catch (MandatoryElementException cause) {
                Assertions.assertEquals(AKOMANTOSO, cause.getExpected());
                Assertions.assertEquals(new QNameImpl("test".toCharArray(),4,0), cause.getActual());
            }
        });
    }
}
