package io.legaldocml.business.guid;

import io.legaldocml.akn.type.NoWhiteSpace;

import java.util.UUID;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class GuidGenerators {

    private GuidGenerators() {
    }

    public static GuidGenerator uuid() {
       return () -> NoWhiteSpace.valueOf(UUID.randomUUID().toString());
    }

    public static GuidGenerator timestamp() {
        return new TimestampGuidGenerator();
    }

}
