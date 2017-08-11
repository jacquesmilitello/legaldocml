package io.legaldocml.io.impl;

import io.legaldocml.io.XmlReaderFactory;
import io.legaldocml.io.XmlReaderFactoryProvider;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class XmlReaderFactoryProviderImpl extends XmlReaderFactoryProvider {

    /**
     * {@inheritDoc}
     */
    @Override
    protected XmlReaderFactory createXmlReaderFactory(int size) {
        return new XmlReaderFactoryImpl(size);
    }

}
