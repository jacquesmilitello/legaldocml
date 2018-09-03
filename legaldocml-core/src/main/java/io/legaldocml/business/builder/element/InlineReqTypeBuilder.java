package io.legaldocml.business.builder.element;

import io.legaldocml.akn.DocumentType;
import io.legaldocml.akn.element.InlineReqType;
import io.legaldocml.business.builder.BusinessBuilder;
import io.legaldocml.business.builder.BusinessBuilderAkomaNtosoContext;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public class InlineReqTypeBuilder<T extends InlineReqType> extends InlineCMContainerBuilder<T, InlineReqTypeBuilder<T>> {

    public InlineReqTypeBuilder(BusinessBuilder<? extends DocumentType, ? extends BusinessBuilderAkomaNtosoContext> builder, T container) {
        super(builder, container);
    }

}
