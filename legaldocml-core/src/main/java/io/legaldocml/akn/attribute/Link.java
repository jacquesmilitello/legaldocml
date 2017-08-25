package io.legaldocml.akn.attribute;

import io.legaldocml.akn.AknObject;
import io.legaldocml.util.Uri;

/**
 * Common interface for Link.
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface Link extends AknObject {

    Uri getHref();

    void setHref(Uri href);
    
}