package io.legaldocml.business.builder.attribute;

import io.legaldocml.akn.attribute.Id;
import io.legaldocml.akn.type.NoWhiteSpace;
import io.legaldocml.business.builder.support.SupportBuilder;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface EIdSupport<T extends Id> extends SupportBuilder<T> {

    default void eId(NoWhiteSpace eId) {
        parent().setEid(eId);
    }

    default void eIdNumber() {
        businessBuilder().getProvider().eIdProvider().fill(businessBuilder().getContext(), parent());
    }

    default void eIdNumber(String number) {
        businessBuilder().getProvider().eIdProvider().fill(businessBuilder().getContext(), parent(), number);
    }

}