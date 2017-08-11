package io.legaldocml.business.impl;

import io.legaldocml.akn.DocumentType;
import io.legaldocml.business.builder.BusinessBuilder;
import io.legaldocml.business.builder.MetaBuilder;

public class DefaultBusinessBuilder<T extends DocumentType> extends BusinessBuilder<T> {

    public DefaultBusinessBuilder(Class<T> clazz) {
        super(clazz);
    }

    @Override
    protected T newDocumenyType() {
        try {
            return getDocumentTypeClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException();
        }
    }

    @Override
    protected MetaBuilder<T> newMetaBuilder(BusinessBuilder<T> businessBuilder) {
        return new MetaBuilder<T>(businessBuilder) {
        };
    }
}
