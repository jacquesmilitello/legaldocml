package io.legaldocml.xpath;

import io.legaldocml.akn.AkomaNtoso;
import io.legaldocml.akn.DocumentType;

import java.util.Optional;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface XPathExpression {

    <T extends DocumentType> Optional<Object> evaluate(AkomaNtoso<T> akomaNtoso);

}
