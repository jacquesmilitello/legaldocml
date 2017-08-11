package io.legaldocml.archive;

import io.legaldocml.business.AknIdentifier;
import io.legaldocml.business.MediaType;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface MetaResource {

    AknIdentifier getAknIdentifier();

    String getName();

    MediaType getMediaType();

}
