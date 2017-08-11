package io.legaldocml.akn.v3;

import io.legaldocml.io.impl.DefaultXmlWriterFactory;
import io.legaldocml.io.impl.XmlChannelWriter;

public final class DefaultXmlWriterFactoryV3 extends DefaultXmlWriterFactory {

    public DefaultXmlWriterFactoryV3(int size) {
        super(size, new XmlChannelWriterPoolObject() {
            @Override
            public XmlChannelWriter newInstance() {
                return new XmlChannelWriterV3();
            }
        });
    }

    public DefaultXmlWriterFactoryV3() {
        super(new XmlChannelWriterPoolObject() {
            @Override
            public XmlChannelWriter newInstance() {
                return new XmlChannelWriterV3();
            }
        });
    }

}
