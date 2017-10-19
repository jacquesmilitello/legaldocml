package io.legaldocml.akn;

import io.legaldocml.akn.element.Preamble;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface HasPreamble {

    Preamble getPreamble();

    void setPreamble(Preamble preamble);

}
