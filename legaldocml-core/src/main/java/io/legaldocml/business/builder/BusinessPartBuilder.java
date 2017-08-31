package io.legaldocml.business.builder;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class BusinessPartBuilder {

    private final BusinessBuilder businessBuilder;

    protected BusinessPartBuilder(BusinessBuilder businessBuilder) {
        this.businessBuilder = businessBuilder;
    }

    protected final BusinessBuilder getBusinessBuilder() {
        return businessBuilder;
    }
}
