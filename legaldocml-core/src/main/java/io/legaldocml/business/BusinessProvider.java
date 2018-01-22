package io.legaldocml.business;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.DocumentType;
import io.legaldocml.business.builder.BusinessBuilder;
import io.legaldocml.business.builder.BusinessPartBuilder;
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

    /**
     * All providers.
     */
    private static final ImmutableMap<String, BusinessProvider> PROVIDERS;

    static {
        ServiceLoader<BusinessProvider> serviceLoader = ServiceLoader.load(BusinessProvider.class, Thread.currentThread().getContextClassLoader());
        Iterator<BusinessProvider> iterator = serviceLoader.iterator();
        ImmutableMap.Builder<String, BusinessProvider> builder = ImmutableMap.builder();
        while (iterator.hasNext()) {
            BusinessProvider provider = iterator.next();
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("BusinessProvider [{}] -> [{}]", provider.name(), provider);
            }
            builder.put(provider.name(), provider);
        }
        PROVIDERS = builder.build();
    }

    public abstract String name();

    public static BusinessProvider businessProvider(String provider) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Business Provider [{}]", provider);
        }

        BusinessProvider businessProvider = PROVIDERS.get(provider);

        if (businessProvider == null) {
            throw new BusinessException("Provider [" + provider + "] not found");
        } else {
            return businessProvider;
        }
    }

    public abstract AknIdentifier newAknIdentifier(String work, String expression, String manifestation);

    public abstract AknIdentifier extractAknIdentifier(String work, String expression, String manifestation);

    public abstract AknIdentifier newAknIdentifierTransient();

    public abstract <E extends BusinessBuilder<T>, T extends DocumentType> E newBuilder(String name);

    public abstract <E extends BusinessPartBuilder<Z>, Z extends AknObject> E newPartBuilder(BusinessBuilder<? extends DocumentType> businessBuilder, AknObject parent, String name);

}
