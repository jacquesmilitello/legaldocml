package io.legaldocml.business.builder;

import io.legaldocml.akn.element.InlineType;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public class InlineTypeBuilder<T extends InlineType> extends InlineCMContainerBuilder<T,InlineTypeBuilder<T>> {

    public InlineTypeBuilder(T container) {
        super(container);
    }

}
