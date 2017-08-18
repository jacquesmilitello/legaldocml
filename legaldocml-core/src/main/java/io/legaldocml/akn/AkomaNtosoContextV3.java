package io.legaldocml.akn;

import io.legaldocml.module.akn.v3.AkomaNtosoV3Module;

public final class AkomaNtosoContextV3 extends AkomaNtosoContext {

    public AkomaNtosoContextV3(){
        this.setAkoXmlModule(new AkomaNtosoV3Module());
        add(this.getAkoXmlModule());
    }

}
