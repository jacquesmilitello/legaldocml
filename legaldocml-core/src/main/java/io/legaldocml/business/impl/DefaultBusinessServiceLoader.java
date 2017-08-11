package io.legaldocml.business.impl;

import io.legaldocml.akn.DocumentType;
import io.legaldocml.business.AknIdentifier;
import io.legaldocml.business.BusinessProvider;
import io.legaldocml.business.builder.BusinessBuilder;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class DefaultBusinessServiceLoader extends BusinessProvider {


    @Override
    public AknIdentifier buildAknIdentifier(String work, String expression, String manifestation) {
        return new DefaultAknIdentifier(work, expression.substring(work.length() + 1), manifestation.substring(expression.length() + 1), "/");
    }

    @Override
    protected AknIdentifier buildAknIdentifier(String work, String expressionPart, String manifestationPart, String separator) {
        return new DefaultAknIdentifier(work, expressionPart, manifestationPart, separator);
    }

    @Override
    protected AknIdentifier buildAknIdentifierTransient() {
        return new DefaultAknIdentifier("0", "0", "0", "/");
    }

    @Override
    protected <T extends DocumentType> BusinessBuilder<T> newInstance(Class<T> documentType) {
        return new DefaultBusinessBuilder<>(documentType);
    }

}
