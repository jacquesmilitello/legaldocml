package io.legaldocml.module.akn.v3;

import io.legaldocml.akn.AkomaNtosoContext;
import io.legaldocml.module.AknModule;
import io.legaldocml.module.Modules;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public class AkomaNtosoContextV3 extends AkomaNtosoContext {

    public AkomaNtosoContextV3(){
        super((AknModule) Modules.get(AkomaNtosoModuleV3.NAMESPACE));
    }

}
