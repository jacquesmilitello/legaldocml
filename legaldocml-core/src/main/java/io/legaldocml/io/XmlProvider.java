package io.legaldocml.io;

import io.legaldocml.io.impl.DefaultXmlProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class XmlProvider {

    /**
     * SLF4J Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultXmlProvider.class);

    private static final XmlProvider XML_PROVIDER;

    static {

        XmlWriterFactoryProvider.checkInit();

        ServiceLoader<XmlProvider> serviceLoader = ServiceLoader.load(XmlProvider.class, Thread.currentThread().getContextClassLoader());
        Iterator<XmlProvider> iterator = serviceLoader.iterator();
        XmlProvider provider = null;

        while (iterator.hasNext()) {
            provider = iterator.next();

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("found XmlProvider impl -> [{}]", provider.getClass());
            }

            if (!(provider instanceof DefaultXmlProvider)) {
                // Specific provider, not the default -> use it.
                break;
            }
        }

        XML_PROVIDER = provider;

        LOGGER.info("XmlProvider : [{}]", provider);

    }

    protected abstract XmlWriterFactory getWriterFactory();

    protected abstract XmlReaderFactory getReaderFactory();

    public static XmlWriterFactory writerFactory(int version) {
        return XML_PROVIDER.getWriterFactory();
    }

    public static XmlReaderFactory readerFactory() {
        return XML_PROVIDER.getReaderFactory();
    }


}
