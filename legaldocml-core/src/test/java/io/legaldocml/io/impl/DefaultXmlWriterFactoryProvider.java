package io.legaldocml.io.impl;

import io.legaldocml.io.XmlWriterFactory;
import io.legaldocml.io.XmlWriterFactoryProvider;

public class DefaultXmlWriterFactoryProvider extends XmlWriterFactoryProvider {
    @Override
    protected XmlWriterFactory createXmlWriterFactory(int size) {
        return new DefaultXmlWriterFactory(4, new DefaultXmlWriterFactory.XmlChannelWriterPoolObject() {
            @Override
            public XmlChannelWriter newInstance() {
                return new XmlChannelWriter() {
                    @Override
                    public int getVersion() {
                        return 3;
                    }
                };
            }
        }) {

        };
    }
}
