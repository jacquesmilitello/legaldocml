package io.legaldocml.business.builder.support;

import io.legaldocml.akn.AknObject;
import io.legaldocml.business.builder.BusinessPartBuilder;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface SupportBuilder<T extends AknObject> extends BusinessPartBuilder<T> {

    T parent();

}
