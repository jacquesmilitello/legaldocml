package io.legaldocml.business.guid;

import io.legaldocml.akn.type.NoWhiteSpace;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
@FunctionalInterface
public interface GuidGenerator {

    NoWhiteSpace generate();

}