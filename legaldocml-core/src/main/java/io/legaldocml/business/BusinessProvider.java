package io.legaldocml.business;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.DocumentType;
import io.legaldocml.business.builder.BusinessBuilder;
import io.legaldocml.business.builder.BusinessPartBuilder;
import io.legaldocml.model.ModelProvider;
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

    public abstract String name();

    public abstract ModelProvider modelProvider();

    public abstract <T extends AknIdentifier> T newAknIdentifier(String work, String expressionPart, String manifestationPart);

    public abstract <T extends AknIdentifier> T extractAknIdentifier(String work, String expression, String manifestation);

    public abstract <T extends AknIdentifier> T newAknIdentifierTransient();

    public abstract <E extends BusinessBuilder<T>, T extends DocumentType> E newBuilder(String name);

    public abstract <E extends BusinessPartBuilder<Z>, Z extends AknObject> E newPartBuilder(BusinessBuilder<? extends DocumentType> businessBuilder, AknObject parent, String name);

}
