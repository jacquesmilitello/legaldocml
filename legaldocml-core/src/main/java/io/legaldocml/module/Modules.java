package io.legaldocml.module;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.io.CharArray;
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

    private static final ImmutableMap<CharArray, Module> MODULES;
    private static final ImmutableMap<CharArray, Module> AKOS;

    static {
        ImmutableMap.Builder<CharArray, Module> builder = ImmutableMap.builder();
        ImmutableMap.Builder<CharArray, Module> builderAko = ImmutableMap.builder();

        ServiceLoader.load(Module.class).forEach(c -> {
            LOGGER.info("load Module [{}]", c);
            builder.put(c.namespace(), c);
            if (c instanceof AknModule) {
                builderAko.put(c.namespace(), c);
            }
        });
        MODULES = builder.build();
        AKOS = builderAko.build();
    }

    private Modules(){
    }

    public static Module get(CharArray key) {
        return MODULES.get(key);
    }

}
