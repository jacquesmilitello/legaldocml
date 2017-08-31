package io.legaldocml.business.impl;

import io.legaldocml.business.AknIdentifier;
import io.legaldocml.business.BusinessProvider;
import io.legaldocml.business.builder.BusinessBuilder;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class DefaultBusinessServiceLoader extends BusinessProvider {


    @Override
    public String name() {
        return "default";
    }

    @Override
    public AknIdentifier newAknIdentifier(String work, String expression, String manifestation) {
        return new DefaultAknIdentifier(work, expression, manifestation, "/");
    }

    @Override
    public AknIdentifier extractAknIdentifier(String work, String expression, String manifestation) {
        return new DefaultAknIdentifier(work, expression.substring(work.length() +1), manifestation.substring(expression.length() + 1), "/");
    }

    @Override
    public AknIdentifier newAknIdentifierTransient() {
        return new DefaultAknIdentifier("0", "0", "0", "/");
    }

    @SuppressWarnings("unchecked")
    @Override
    public <E extends BusinessBuilder> E newBuilder(String name) {
        return (E) new DefaultBusinessBuilder(name);
    }

}
