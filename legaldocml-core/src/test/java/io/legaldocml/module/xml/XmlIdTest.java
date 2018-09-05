package io.legaldocml.module.xml;

import io.legaldocml.io.CoreAttribute;
import io.legaldocml.module.xml.attribute.XmlId;
import org.junit.jupiter.api.Test;

import java.util.function.Supplier;

class XmlIdTest {

    @Test
    void test() {
        XmlModule module = new XmlModule();
        Supplier<CoreAttribute> supplier =  module.attribute("id");
        XmlId attribure = (XmlId) supplier.get();
    }

}
