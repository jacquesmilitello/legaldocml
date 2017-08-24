package io.legaldocml.akn;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.attribute.Id;
import io.legaldocml.akn.attribute.Src;
import io.legaldocml.akn.type.ManifestationURI;
import io.legaldocml.akn.type.NoWhiteSpace;
import io.legaldocml.io.CharArray;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.module.AknModule;
import io.legaldocml.module.Module;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class AkomaNtosoContext {

    /**
     * SLF4J Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AkomaNtosoContext.class);

    private static final ImmutableMap<String, BiConsumer<AkomaNtosoContext,AknObject>> REFS;

    static {
        REFS = ImmutableMap.<String, BiConsumer<AkomaNtosoContext, AknObject>>builder()
                .put(Id.ATTRIBUTE_EID, (context, aknObject) -> context.eids.put(((Id)aknObject).getEid(), (Id)aknObject))
                .put(Src.ATTRIBUTE_SRC, (context, aknObject) -> context.srcs.put(((Src)aknObject).getSrc(), (Src)aknObject))
                .build();
    }

    private final HashMap<NoWhiteSpace, Id> eids = new HashMap<>();
    private final HashMap<ManifestationURI, Src> srcs = new HashMap<>();


    /**
     * Holder of all namespaces define for this "akomantoso".
     */
    private final Map<CharArray, Module> modules = new HashMap<>(4);

    /**
     * Akn module for this context
     */
    private final AknModule aknModule;

    protected AkomaNtosoContext(AknModule aknModule) {
        this.aknModule = aknModule;
        add(aknModule);
    }

    public AknModule getAkoXmlModule() {
        return this.aknModule;
    }

    public Module getModule(CharArray array) {
        return this.modules.get(array);
    }

    void writeModules(XmlWriter writer) throws IOException {
        for (Module module : this.modules.values()) {
            module.writeNamespace(writer);
        }
    }

    public void add(Module module) {
        this.modules.put(module.namespace(), module);
    }

    @SuppressWarnings("unchecked")
    public void update(String name, AknObject akn) {
        BiConsumer consumer = REFS.get(name);

        if (consumer == null) {
            LOGGER.warn("no consumer for attribute [{}] -> skip" , name);
        } else {
            consumer.accept(this, akn);
        }
    }

    public Id getId(String id) {
        return this.eids.get(new NoWhiteSpace(id));
    }

    public Id getId(NoWhiteSpace id) {
        return this.eids.get(id);
    }

    public Iterator<Id> getIds() {
        return this.eids.values().iterator();
    }

}
