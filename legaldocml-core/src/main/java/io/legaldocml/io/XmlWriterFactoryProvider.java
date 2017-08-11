package io.legaldocml.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class XmlWriterFactoryProvider {

    /**
     * SLF4J Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(XmlWriterFactoryProvider.class);

    private static final XmlWriterFactoryProvider INSTANCE;

    static {
        ServiceLoader<XmlWriterFactoryProvider> serviceLoader = ServiceLoader.load(XmlWriterFactoryProvider.class, Thread.currentThread().getContextClassLoader());
        Iterator<XmlWriterFactoryProvider> iterator = serviceLoader.iterator();

        INSTANCE = iterator.next();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("XmlWriterFactoryProvider implementation -> [{}]", INSTANCE.getClass());
        }
    }

    public static XmlWriterFactory xmlWriterFactory(int size) {
        return INSTANCE.createXmlWriterFactory(size);
    }

    protected abstract XmlWriterFactory createXmlWriterFactory(int size);

    public static void checkInit() {

    }
}
