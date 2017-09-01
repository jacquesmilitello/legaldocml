package io.legaldocml.business.builder.support;

import io.legaldocml.business.builder.BusinessBuilder;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface SupportBuilder<T> {

    T getParent();

    BusinessBuilder getBusinessBuilder();

}
