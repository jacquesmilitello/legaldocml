package io.legaldocml.io;

import io.legaldocml.akn.AknObject;
import io.legaldocml.module.AknModule;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface XmlReaderContext {

    void update(String name, AknObject akn);

    AknModule getAknModule();

}
