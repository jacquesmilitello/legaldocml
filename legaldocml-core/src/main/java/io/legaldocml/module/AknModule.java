package io.legaldocml.module;

import io.legaldocml.akn.AkomaNtosoContext;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface AknModule extends Module {

    int getVersion();

    AkomaNtosoContext newAkomaNtosoContext();

}
