package io.legaldocml.business.builder.element;

import io.legaldocml.akn.DocumentType;
import io.legaldocml.akn.element.InlineReqReqType;
import io.legaldocml.business.builder.BusinessBuilder;
import io.legaldocml.business.builder.BusinessBuilderAkomaNtosoContext;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public class InlineReqReqTypeBuilder<T extends InlineReqReqType> extends InlineCMContainerBuilder<T, InlineReqReqTypeBuilder<T>> {

    public InlineReqReqTypeBuilder(BusinessBuilder<? extends DocumentType, ? extends BusinessBuilderAkomaNtosoContext> builder, T container) {
        super(builder, container);
    }

}
