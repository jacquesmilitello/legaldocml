package io.legaldocml.business.builder.support;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.container.BlockElementsContainer;
import io.legaldocml.akn.element.AnyOtherTypeElement;
import io.legaldocml.akn.element.Foreign;
import io.legaldocml.business.BusinessProvider;
import io.legaldocml.business.builder.BusinessPartBuilder;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface ForeignSupport<T extends BlockElementsContainer<E>, E extends AknObject> extends SupportBuilder<T> {

    default <U extends BusinessPartBuilder<Z>, Z extends AnyOtherTypeElement> U foreign(String provider, String businessPartBuilder) {
        Foreign foreign = new Foreign();
        parent().add(foreign);
        businessBuilder().getContext().push(parent(), foreign);
        return BusinessProvider.businessProvider(provider)
                .newPartBuilder(businessBuilder(), foreign, businessPartBuilder);
    }

}