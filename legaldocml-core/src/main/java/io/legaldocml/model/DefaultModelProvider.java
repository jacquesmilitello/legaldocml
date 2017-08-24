package io.legaldocml.model;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.iso.Iso3166;
import io.legaldocml.iso.Iso639;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class DefaultModelProvider implements ModelProvider {

    /**
     * Singleton instance.
     */
    public static final DefaultModelProvider INSTANCE = new DefaultModelProvider();

    private static final ImmutableMap<String, Iso639> ALL_LANGUAGES;
    private static final ImmutableMap<String, Iso3166> COUNTRY_ALL;

    // Initialize languages.
    static {
        Iso639[] values = Iso639.values();
        ImmutableMap.Builder<String, Iso639> builder = ImmutableMap.builder();
        for (Iso639 val : values) {
            if (val.getCode() != null) {
                builder.put(val.getCode(), val);
            }
            if (!val.getBibliographic().equals(val.getTerminology())) {
                builder.put(val.getBibliographic(), val);
            }
            builder.put(val.getTerminology(), val);
        }
        ALL_LANGUAGES = builder.build();
    }

    // Initialize countries.
    static {
        Iso3166[] values = Iso3166.values();
        ImmutableMap.Builder<String, Iso3166> builderAll = ImmutableMap.builder();
        ImmutableMap.Builder<String, Iso3166> builder2 = ImmutableMap.builder();
        ImmutableMap.Builder<String, Iso3166> builder3 = ImmutableMap.builder();
        for (Iso3166 val : values) {
            builderAll.put(val.getAlpha2(), val);
            builder2.put(val.getAlpha2(), val);
            builderAll.put(val.getAlpha3(), val);
            builder3.put(val.getAlpha3(), val);
        }
        COUNTRY_ALL = builderAll.build();
    }

    private DefaultModelProvider() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Country country(String code) {
        return COUNTRY_ALL.get(code);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Language language(String code) {
        return ALL_LANGUAGES.get(code);
    }

}
