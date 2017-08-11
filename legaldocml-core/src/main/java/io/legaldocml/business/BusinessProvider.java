package io.legaldocml.business;

import io.legaldocml.akn.DocumentType;
import io.legaldocml.business.builder.BusinessBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class BusinessProvider {

    /**
     * SLF4J Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(BusinessProvider.class);

    protected static final BusinessProvider INSTANCE;

    static {
        ServiceLoader<BusinessProvider> serviceLoader = ServiceLoader.load(BusinessProvider.class, Thread.currentThread().getContextClassLoader());

        Iterator<BusinessProvider> iterator = serviceLoader.iterator();

        INSTANCE = iterator.next();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("BusinessProvider impl -> [{}]", INSTANCE);
        }
    }

    public static AknIdentifier newAknIdentifier(String work, String expressionPart, String manifestationPart, String separator) {
        return INSTANCE.buildAknIdentifier(work,expressionPart,manifestationPart, separator);
    }

    public static AknIdentifier newAknIdentifier(String work, String expression, String manifestation) {
        return INSTANCE.buildAknIdentifier(work,expression,manifestation);
    }

    public static AknIdentifier newAknIdentifierTransient() {
        return INSTANCE.buildAknIdentifierTransient();
    }

    public static <T extends DocumentType> BusinessBuilder<T> newBusinessBuilder(String provider, Class<T> documentType) {
        return INSTANCE.newInstance(documentType);
    }

    protected abstract AknIdentifier buildAknIdentifier(String work, String expression, String manifestation);

    protected abstract AknIdentifier buildAknIdentifier(String work, String expressionPart, String manifestationPart, String separator);

    protected abstract AknIdentifier buildAknIdentifierTransient();

    protected abstract <T extends DocumentType> BusinessBuilder<T> newInstance(Class<T> documentType);

}
