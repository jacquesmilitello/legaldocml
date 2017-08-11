package io.legaldocml.xpath;

import io.legaldocml.akn.AkomaNtoso;
import io.legaldocml.akn.DocumentType;

import java.util.function.Function;

public interface Xpath {

    <T extends DocumentType> XpathResult execute(AkomaNtoso<T> akomaNtoso);

}
