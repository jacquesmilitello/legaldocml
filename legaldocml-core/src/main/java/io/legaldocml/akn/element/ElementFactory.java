package io.legaldocml.akn.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;

import java.util.function.Supplier;

import static io.legaldocml.akn.element.Groups.blockElements;
import static io.legaldocml.akn.element.Groups.convertSuper;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class ElementFactory {

    private ElementFactory() {
    }

    private static final ImmutableMap<String, Supplier<AknObject>> AKNS;

    static {
        ImmutableMap.Builder<String, Supplier<AknObject>> builder = ImmutableMap.builder();
        builder.putAll(convertSuper(blockElements()));
        AKNS = builder.build();
    }



    @SuppressWarnings("unchecked")
    public static <T extends AknObject> T instantiate(String name) {
        return (T) AKNS.get(name).get();

    }
}
