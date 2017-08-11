package io.legaldocml.xpath.query;

import io.legaldocml.xpath.Xpath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public abstract class XpathQueryBuilder {

    /**
     * SLF4J Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(XpathQueryBuilder.class);

    protected final List<XpathQueryFunction> functions = new ArrayList<>();

    public void node(String text) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("node [{}]", text);
        }

        this.functions.add(new XpathQueryFunctionNode(text));

    }


    public abstract Xpath build();
}
