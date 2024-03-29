package io.legaldocml.business.builder.element;

import io.legaldocml.akn.DocumentType;
import io.legaldocml.akn.element.Toc;
import io.legaldocml.akn.element.TocItem;
import io.legaldocml.akn.type.Uri;
import io.legaldocml.business.builder.AbstractBusinessPartBuilder;
import io.legaldocml.business.builder.BusinessBuilder;
import io.legaldocml.business.builder.BusinessBuilderAkomaNtosoContext;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class TocBuilder extends AbstractBusinessPartBuilder<Toc> {

    public TocBuilder(BusinessBuilder<? extends DocumentType, ? extends BusinessBuilderAkomaNtosoContext> businessBuilder, Toc parent) {
        super(businessBuilder, parent);
    }

    public InlineTypeBuilder<TocItem> item(Uri href, String level) {
        TocItem tocItem = new TocItem();
        tocItem.setHref(href);
        tocItem.setLevel(level);
        parent().add(tocItem);
        return new InlineTypeBuilder<>(businessBuilder(), tocItem);
    }

}
