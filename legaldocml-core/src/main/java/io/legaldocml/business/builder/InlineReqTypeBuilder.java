package io.legaldocml.business.builder;

import io.legaldocml.akn.element.InlineReqType;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public class InlineReqTypeBuilder<T extends InlineReqType> extends InlineCMContainerBuilder<T, InlineReqTypeBuilder<T>> {

    public InlineReqTypeBuilder(BusinessBuilder builder, T container) {
        super(builder,container);
    }

}
