package io.legaldocml.business.builder;

import io.legaldocml.akn.DocumentType;
import io.legaldocml.akn.element.Body;
import io.legaldocml.akn.element.BodyTypeElement;
import io.legaldocml.business.builder.group.HierElementsBuilder;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class BodyBuilder extends AbstractBusinessPartBuilder<Body> implements HierElementsBuilder<Body,BodyTypeElement>{

    BodyBuilder(BusinessBuilder<? extends DocumentType, ? extends BusinessBuilderAkomaNtosoContext> businessBuilder, Body body) {
        super(businessBuilder, body);
    }

}