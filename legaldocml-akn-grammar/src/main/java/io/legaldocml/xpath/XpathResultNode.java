package io.legaldocml.xpath;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.DocumentType;

public final class XpathResultNode implements XpathResult<AknObject> {

    private final AknObject aknObject;

    public XpathResultNode(AknObject aknObject) {
        this.aknObject = aknObject;
    }

    @Override
    public XpathResultType type() {
        return XpathResultType.NODE;
    }

    @Override
    public AknObject get() {
        return aknObject;
    }
}
