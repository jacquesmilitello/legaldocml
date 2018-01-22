package io.legaldocml.business.builder.element;

import io.legaldocml.akn.DocumentType;
import io.legaldocml.akn.element.InlineReqType;
import io.legaldocml.business.builder.BusinessBuilder;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public class InlineReqTypeBuilder<T extends InlineReqType> extends InlineCMContainerBuilder<T, InlineReqTypeBuilder<T>> {

    public InlineReqTypeBuilder(BusinessBuilder<? extends DocumentType> builder, T container) {
        super(builder, container);
    }

}
