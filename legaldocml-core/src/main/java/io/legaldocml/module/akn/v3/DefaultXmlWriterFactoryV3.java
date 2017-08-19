package io.legaldocml.module.akn.v3;

import io.legaldocml.io.impl.DefaultXmlWriterFactory;
import io.legaldocml.io.impl.XmlChannelWriter;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
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
