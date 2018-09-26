package io.legaldocml.module.xml;

import io.legaldocml.akn.AkomaNtoso;
import io.legaldocml.io.CoreAttribute;
import io.legaldocml.module.xml.attribute.XmlId;
import org.junit.jupiter.api.Test;

import java.util.function.Supplier;

import static io.legaldocml.ReaderHelper.read;

class XmlIdTest {

    @Test
    void test() {
        XmlModule module = new XmlModule();
        Supplier<CoreAttribute> supplier =  module.attribute("id");
        XmlId attribure = (XmlId) supplier.get();
    }

    @Test
    void testOther() throws Exception {
        AkomaNtoso<?> akn = read("/xml/v3/uk_pga_1998_29.xml");
    }

}
