package io.legaldocml.module;

import io.legaldocml.akn.AkomaNtosoContext;
import io.legaldocml.model.ModelProvider;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface AknModule extends Module {

    int getVersion();

    AkomaNtosoContext newAkomaNtosoContext();

    ModelProvider getModelProvider();
}
