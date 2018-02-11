package io.legaldocml.io.impl;

import io.legaldocml.test.LoggerInstancePostProcessor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;

import static io.legaldocml.io.impl.XmlChannelReaderHelper.doTest;
import static io.legaldocml.io.impl.XmlChannelReaderHelper.path;

@ExtendWith(LoggerInstancePostProcessor.class)
class XmlChannelReaderBigTextTest {

    @Test
    void test001() throws IOException {
        doTest(path("/xml/bigText.xml"), reader -> {
            reader.nextStartOrEndElement();
            reader.next();
        });
    }


}
