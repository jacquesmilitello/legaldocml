package io.legaldocml.business.builder.element;

import io.legaldocml.akn.element.InlineReqReqType;
import io.legaldocml.business.builder.BusinessBuilder;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public class InlineReqReqTypeBuilder<T extends InlineReqReqType> extends InlineCMContainerBuilder<T, InlineReqReqTypeBuilder<T>> {

    public InlineReqReqTypeBuilder(BusinessBuilder builder, T container) {
        super(builder, container);
    }

}
