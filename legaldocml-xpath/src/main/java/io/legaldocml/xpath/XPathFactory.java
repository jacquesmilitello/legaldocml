package io.legaldocml.xpath;

import io.legaldocml.xpath.impl.XPathFactoryImpl;

public abstract class XPathFactory {

    public static XPathFactory newInstance() {
        return XPathFactoryImpl.INSTANCE;
    }

    public abstract XPath newXPath();

}
