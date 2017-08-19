package io.legaldocml.module.akn.v2;

import io.legaldocml.akn.AkomaNtosoContext;
import io.legaldocml.module.AknModule;
import io.legaldocml.module.Modules;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public class AkomaNtosoContextV2 extends AkomaNtosoContext {

    public AkomaNtosoContextV2() {
        super((AknModule) Modules.get(AkomaNtosoModuleV2.NAMESPACE));
    }

}
