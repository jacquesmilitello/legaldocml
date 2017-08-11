package io.legaldocml.archive;

import io.legaldocml.business.AknIdentifier;

import java.util.stream.Stream;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface Meta {

    Stream<MetaResource> stream();

    boolean exists(AknIdentifier identifier);

}
