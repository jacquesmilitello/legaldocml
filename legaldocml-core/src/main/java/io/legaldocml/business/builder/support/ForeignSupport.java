package io.legaldocml.business.builder.support;

import io.legaldocml.akn.container.BlockElementsContainer;
import io.legaldocml.akn.element.Foreign;
import io.legaldocml.business.builder.BusinessPartBuilder;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface ForeignSupport<T extends BlockElementsContainer> extends SupportBuilder<T> {

    default <T extends BusinessPartBuilder> T foreign(String businessPartBuilderName) {
        Foreign foreign = new Foreign();
        getParent().add(foreign);
        return getBusinessBuilder().newPartBuilder(foreign, businessPartBuilderName);
    }

}