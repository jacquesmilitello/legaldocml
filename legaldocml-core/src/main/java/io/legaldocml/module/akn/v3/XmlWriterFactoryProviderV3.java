package io.legaldocml.module.akn.v3;

import io.legaldocml.io.XmlWriterFactory;
import io.legaldocml.io.XmlWriterFactoryProvider;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class XmlWriterFactoryProviderV3 extends XmlWriterFactoryProvider {

    @Override
    protected XmlWriterFactory createXmlWriterFactory(int size) {
        return new DefaultXmlWriterFactoryV3(size);
    }

}
