package io.legaldocml.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class XmlReaderFactoryProvider {

    /**
     * SLF4J Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(XmlReaderFactoryProvider.class);

    private static final XmlReaderFactoryProvider FACTORY_SERVICE_LOADER;

    static {
        ServiceLoader<XmlReaderFactoryProvider> serviceLoader = ServiceLoader.load(XmlReaderFactoryProvider.class, Thread.currentThread().getContextClassLoader());

        Iterator<XmlReaderFactoryProvider> iterator = serviceLoader.iterator();

        FACTORY_SERVICE_LOADER = iterator.next();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("XmlReaderFactoryProvider impl -> [{}]", FACTORY_SERVICE_LOADER);
        }
    }

    public static XmlReaderFactory newXmlReaderFactory(int size) {
        return FACTORY_SERVICE_LOADER.createXmlReaderFactory(size);
    }

    protected abstract XmlReaderFactory createXmlReaderFactory(int size);


}