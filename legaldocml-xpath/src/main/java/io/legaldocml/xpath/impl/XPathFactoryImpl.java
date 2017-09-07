package io.legaldocml.xpath.impl;

import io.legaldocml.xpath.XPath;
import io.legaldocml.xpath.XPathFactory;

public final  class XPathFactoryImpl extends XPathFactory {

    public static final XPathFactory INSTANCE = new XPathFactoryImpl();

    @Override
    public XPath newXPath() {
        return new XPathImpl();
    }
}
