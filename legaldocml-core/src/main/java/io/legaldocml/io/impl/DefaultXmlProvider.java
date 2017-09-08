package io.legaldocml.io.impl;

import io.legaldocml.io.XmlProvider;
import io.legaldocml.io.XmlReaderFactory;
import io.legaldocml.io.XmlReaderFactoryProvider;
import io.legaldocml.io.XmlWriterFactory;
import io.legaldocml.io.XmlWriterFactoryProvider;
import io.legaldocml.util.ToStringBuilder;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class DefaultXmlProvider extends XmlProvider {

    private final XmlWriterFactory writerFactory = XmlWriterFactoryProvider.xmlWriterFactory(16);

    private final XmlReaderFactory readerFactory = XmlReaderFactoryProvider.newXmlReaderFactory(16);

    /**
     * {@inheritDoc}
     */
    @Override
    protected XmlWriterFactory getWriterFactory() {
        return writerFactory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected XmlReaderFactory getReaderFactory() {
        return readerFactory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return new ToStringBuilder(false)
                .append("class",getClass().getSimpleName())
                .append("writerFactory.size",16)
                .append("readerFactory.size",16)
                .toString();
    }
}
