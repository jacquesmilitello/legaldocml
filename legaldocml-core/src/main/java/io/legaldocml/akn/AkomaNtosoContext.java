package io.legaldocml.akn;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.attribute.Id;
import io.legaldocml.akn.attribute.Src;
import io.legaldocml.akn.type.ManifestationURI;
import io.legaldocml.akn.type.NoWhiteSpace;
import io.legaldocml.business.BusinessProvider;
import io.legaldocml.io.XmlReaderContext;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.module.AknModule;
import io.legaldocml.module.Module;
import io.legaldocml.unsafe.UnsafeString;
import io.legaldocml.util.CharArray;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class AkomaNtosoContext implements XmlReaderContext {

    private static final ImmutableMap<String, BiConsumer<AkomaNtosoContext, AknObject>> REFS;

    static {
        REFS = ImmutableMap.<String, BiConsumer<AkomaNtosoContext, AknObject>>builder()
                .put(AknAttributes.EID, (context, aknObject) -> context.eids.put(((Id) aknObject).getEid(), (Id) aknObject))
                .put(AknAttributes.SRC, (context, aknObject) -> context.srcs.put(((Src) aknObject).getSrc(), (Src) aknObject))
                .build();
    }

    private final HashMap<NoWhiteSpace, Id> eids = new HashMap<>();
    private final HashMap<ManifestationURI, Src> srcs = new HashMap<>();


    /**
     * Holder of all namespaces define for this "akomantoso".
     */
    private final Map<CharArray, Module> modules = new HashMap<>(4);

    private AknModule aknModule;

    protected AkomaNtosoContext() {
    }

    public AknModule getAknModule() {
        return aknModule;
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
        if (module instanceof AknModule) {
            if (this.aknModule != null) {
                throw new AknReadException(AknReadException.Type.TWO_AKN_MODULES, null, this.aknModule, module);
            } else {
                this.aknModule = (AknModule) module;
            }
        }
        this.modules.put(module.namespace(), module);
    }

    public void update(String name, AknObject akn) {
        BiConsumer<AkomaNtosoContext, AknObject> consumer = REFS.get(name);
        if (consumer != null) {
            consumer.accept(this, akn);
        }
    }

    public Id getId(String id) {
        return this.eids.get(NoWhiteSpace.valueOf(UnsafeString.getChars(id)));
    }

    public Id getId(NoWhiteSpace id) {
        return this.eids.get(id);
    }

    public Iterator<Id> getIds() {
        return this.eids.values().iterator();
    }

    public abstract BusinessProvider businessProvider();
}
