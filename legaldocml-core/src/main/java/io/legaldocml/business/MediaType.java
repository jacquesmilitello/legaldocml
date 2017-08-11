package io.legaldocml.business;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class MediaType {

    public static final MediaType LEGALDOCML = new MediaType("application", "akn+xml");

    private final String type;
    private final String subType;

    private static final Map<String, MediaType> ALL;

    static {
        ALL = ImmutableMap.<String, MediaType>builder()
                .put(LEGALDOCML.toString(), LEGALDOCML)
                .build();
    }

    private MediaType(String type, String subType) {
        this.type = type;
        this.subType = subType;
    }

    public String getType() {
        return type;
    }

    public String getSubType() {
        return subType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(type).append('/').append(subType);
        return builder.toString();
    }

    public static MediaType valueOf(String value) {

        MediaType mediaType = ALL.get(value);

        if (mediaType != null) {
            return mediaType;
        }

        String[] values = value.split("/");

        if (values.length != 2 ) {
            throw new RuntimeException("");
        }

        return new MediaType(values[0], values[1]);
    }
}