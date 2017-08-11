package io.legaldocml.xpath.engine;

import io.legaldocml.akn.AkomaNtoso;
import io.legaldocml.akn.DocumentType;
import io.legaldocml.xpath.Xpath;
import io.legaldocml.xpath.XpathResult;
import io.legaldocml.xpath.XpathResultNode;

public class AbsoluteEngine implements Xpath {

    @Override
    public <T extends DocumentType> XpathResult execute(AkomaNtoso<T> akomaNtoso) {

        return new XpathResultNode(akomaNtoso.getDocumentType());
    }
}
