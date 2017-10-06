package io.legaldocml.business.builder;

import io.legaldocml.akn.element.InlineType;
import io.legaldocml.business.builder.support.RefSupport;
import io.legaldocml.business.builder.support.SpanSupport;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public class InlineTypeBuilder<T extends InlineType> extends InlineCMContainerBuilder<T, InlineTypeBuilder<T>>
        implements SpanSupport<T>, RefSupport<T> {

    public InlineTypeBuilder(BusinessBuilder builder, T container) {
        super(builder, container);
    }

}


