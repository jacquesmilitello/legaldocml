package io.legaldocml.business.builder.element;

import io.legaldocml.akn.DocumentType;
import io.legaldocml.akn.element.InlineType;
import io.legaldocml.business.builder.BusinessBuilder;
import io.legaldocml.business.builder.BusinessBuilderAkomaNtosoContext;
import io.legaldocml.business.builder.support.EmbeddedTextSupport;
import io.legaldocml.business.builder.support.PlaceholderSupport;
import io.legaldocml.business.builder.support.SpanSupport;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public class InlineTypeBuilder<T extends InlineType> extends InlineCMContainerBuilder<T, InlineTypeBuilder<T>>
        implements SpanSupport<T>, EmbeddedTextSupport<T>,PlaceholderSupport<T> {

    public InlineTypeBuilder(BusinessBuilder<? extends DocumentType, ? extends BusinessBuilderAkomaNtosoContext> builder, T container) {
        super(builder, container);
    }

}