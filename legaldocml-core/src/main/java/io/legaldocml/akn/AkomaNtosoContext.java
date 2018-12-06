package io.legaldocml.akn;

import io.legaldocml.business.BusinessProvider;
import io.legaldocml.io.XmlReaderContext;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.module.AknModule;
import io.legaldocml.module.Module;
import io.legaldocml.util.CharArray;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class AkomaNtosoContext implements XmlReaderContext {

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

    public final Module getModule(CharArray array) {
        return this.modules.get(array);
    }

    void writeModules(XmlWriter writer) throws IOException {
        for (Module module : this.modules.values()) {
            module.writeNamespace(writer);
        }
    }

    public final void add(Module module) {
        if (module instanceof AknModule) {
            if (this.aknModule != null) {
                throw new AknReadException(AknReadException.Type.TWO_AKN_MODULES, null, this.aknModule, module);
            } else {
                this.aknModule = (AknModule) module;
            }
        }
        this.modules.put(module.namespace(), module);
    }

    public abstract BusinessProvider businessProvider();
}
