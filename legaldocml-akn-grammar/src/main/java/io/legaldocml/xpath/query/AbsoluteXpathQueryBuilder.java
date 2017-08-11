package io.legaldocml.xpath.query;

import io.legaldocml.xpath.Xpath;
import io.legaldocml.xpath.engine.AbsoluteEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.legaldocml.akn.AkomaNtoso.ELEMENT;

public final class AbsoluteXpathQueryBuilder extends XpathQueryBuilder {

    /**
     * SLF4J Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AbsoluteXpathQueryBuilder.class);

    @Override
    public void node(String text) {

        if (this.functions.size() == 0 && !ELEMENT.equals(text)) {
            throw new RuntimeException("test");
        }

        super.node(text);
    }

    @Override
    public Xpath build() {

        for (int i = 1 ; i < functions.size(); i++) {





        }


        return new AbsoluteEngine();
    }
}
