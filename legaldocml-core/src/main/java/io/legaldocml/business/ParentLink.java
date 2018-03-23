package io.legaldocml.business;

import io.legaldocml.akn.AknObject;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface ParentLink {

    <T extends AknObject> T getParent(AknObject child);

}
