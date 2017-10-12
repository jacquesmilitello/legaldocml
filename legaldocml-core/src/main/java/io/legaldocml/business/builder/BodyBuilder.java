package io.legaldocml.business.builder;

import io.legaldocml.akn.element.Body;
import io.legaldocml.business.builder.group.HierElementsBuilder;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class BodyBuilder extends AbstractBusinessPartBuilder<Body> implements HierElementsBuilder<Body>{

    BodyBuilder(BusinessBuilder businessBuilder, Body body) {
        super(businessBuilder, body);
    }

}