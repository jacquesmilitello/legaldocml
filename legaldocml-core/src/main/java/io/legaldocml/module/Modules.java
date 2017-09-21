package io.legaldocml.module;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.util.CharArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ServiceLoader;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Modules {

    /**
     * SLF4J Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(Modules.class);

    private static final ImmutableMap<CharArray, Module> ALL_MODULES;

    static {
        ImmutableMap.Builder<CharArray, Module> builder = ImmutableMap.builder();

        ServiceLoader.load(Module.class).forEach(c -> {
            LOGGER.info("load Module [{}]", c);
            builder.put(c.namespace(), c);
        });
        ALL_MODULES = builder.build();
    }

    private Modules(){
    }

    public static Module get(CharArray key) {
        return ALL_MODULES.get(key);
    }

}
