package io.legaldocml.module.akn.v3;

import io.legaldocml.io.XmlWriterFactory;
import io.legaldocml.io.XmlWriterFactoryProvider;

public class XmlWriterFactoryProviderV3 extends XmlWriterFactoryProvider {

    @Override
    protected XmlWriterFactory createXmlWriterFactory(int size) {
        return new DefaultXmlWriterFactoryV3(size);
    }

}
