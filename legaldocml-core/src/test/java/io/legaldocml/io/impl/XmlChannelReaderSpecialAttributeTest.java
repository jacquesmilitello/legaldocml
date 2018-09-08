package io.legaldocml.io.impl;

import io.legaldocml.akn.element.Object;
import io.legaldocml.io.AttributeConsumer;
import io.legaldocml.test.LoggerInstancePostProcessor;
import io.legaldocml.util.CharArray;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

import static io.legaldocml.io.impl.XmlChannelReaderHelper.doTest;
import static io.legaldocml.io.impl.XmlChannelReaderHelper.path;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(LoggerInstancePostProcessor.class)
class XmlChannelReaderSpecialAttributeTest {

    @Test
    void test001Element() throws IOException {
        doTest(path("/xml/specialAttribute.xml"), reader -> {
            reader.nextStartOrEndElement();
            AtomicBoolean atomicBoolean = new AtomicBoolean(false);
            reader.forEach(new Object(), new AttributeConsumer<Object>() {
                @Override
                public void set(XmlChannelReader reader, Object object, CharArray name, CharArray value, int prefixNS) {
                    atomicBoolean.set(true);
                    assertEquals("style", name.toString());
                    assertEquals("{TOC \\t \"PageHeading;1\"}", value.toString());
                }
            });
            assertTrue(atomicBoolean.get());
        });
    }

}
