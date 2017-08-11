package io.legaldocml.io.impl;

import io.legaldocml.io.XmlProvider;
import io.legaldocml.io.XmlReaderFactory;
import io.legaldocml.io.XmlReaderFactoryProvider;
import io.legaldocml.io.XmlWriterFactory;
import io.legaldocml.io.XmlWriterFactoryProvider;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class DefaultXmlProvider extends XmlProvider {

    private final XmlWriterFactory writerFactory = XmlWriterFactoryProvider.xmlWriterFactory(16);

    private final XmlReaderFactory readerFactory = XmlReaderFactoryProvider.newXmlReaderFactory(16);

    @Override
    protected XmlWriterFactory getWriterFactory() {
        return writerFactory;
    }

    @Override
    protected XmlReaderFactory getReaderFactory() {
        return readerFactory;
    }
}
